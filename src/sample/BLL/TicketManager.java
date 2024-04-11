package sample.BLL;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.printing.PDFPageable;
import sample.BE.Event;
import sample.DAL.TicketDAO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class TicketManager {

    private static final String PROP_FILE = "config/config.settings";
    private static List<File> files;

    private TicketDAO ticketDAO;

    public TicketManager() {
        ticketDAO = new TicketDAO();
    }

    public void createTicket(Event event, int amount, String email) throws IOException, PrinterException, MessagingException, BarcodeException, OutputException {
        for (int i = 0; i < amount; i++) {
            makePDF(event, i);
            // printPDF(); Do not uncomment as we do not want to actually print
        }
        sendMail(email, amount);
    }

    private void makePDF(Event event, int index) throws IOException, BarcodeException, OutputException {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setTitle("Ticket");

        Barcode barcode = BarcodeFactory.createCode128(event.getName() + index);
        barcode.setBarHeight(60);
        barcode.setBarHeight(2);

        System.out.println(barcode.getData());

        File imgFile = new File("resources/data/barcodes/barcode" + index + ".png");

        BarcodeImageHandler.savePNG(barcode, imgFile);

        PDImageXObject pdImage = PDImageXObject.createFromFile("resources/data/barcodes/barcode" + index + ".png", document);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        PDType0Font font = getFont(document);

        contentStream.beginText();
        contentStream.newLineAtOffset(30, 700);
        contentStream.setFont(font, 12);
        contentStream.showText(event.getName());
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(25, 650);
        contentStream.setFont(font, 12);
        contentStream.showText("Starting: " + event.getStartDate() + " " + event.getStartTime());
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(25, 600);
        contentStream.setFont(font, 12);
        contentStream.showText("Location: " + event.getLocation());
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(25, 550);
        contentStream.setFont(font, 12);
        contentStream.showText(event.getDescription());
        contentStream.endText();

        if (event.getEndDate() != null) {
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 500);
            contentStream.setFont(font, 12);
            contentStream.showText("Ending: " + event.getEndDate() + " " + event.getEndTime());
            contentStream.endText();
        }

        contentStream.drawImage(pdImage, 25, 200);

        contentStream.close();

        document.save(new File("resources/data/tickets/ticket" + index + ".pdf"));

        document.close();

    }

    private PDType0Font getFont(PDDocument pdDocument) throws IOException {
        PDType0Font font = PDType0Font.load(pdDocument, new File("resources/fonts/Anta-Regular.ttf"));
        return font;
    }

    // No usages because we don't actually want to print
    private void printPDF() throws IOException, PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        PDDocument document = Loader.loadPDF(new File("resources/data/tickets/ticket.pdf"));
        job.setPageable(new PDFPageable(document));
        job.print();
    }

    private void sendMail(String email, int amount) throws MessagingException, IOException {

        Properties informationProperties = new Properties();
        informationProperties.load(new FileInputStream((PROP_FILE)));

        String from = informationProperties.getProperty("From");
        String host = informationProperties.getProperty("Host");
        String port = informationProperties.getProperty("Port");
        String password = informationProperties.getProperty("Emailpassword");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.connection-timeout", "10000");
        props.put("mail.smtp.timeout", "10000");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setSubject("Event Ticket");
        message.setSentDate(new Date());
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

        MimeBodyPart content = new MimeBodyPart();
        content.setText("Amount of tickets: " + amount);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(content);
        files = getCurrentFiles();
        for (File file : files) {
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(file);
            multipart.addBodyPart(attachment);
            // maybe done
        }
        message.setContent(multipart);

        Transport.send(message);
        System.out.println("Email sent!");
    }

    private List<File> getCurrentFiles() {
        return ticketDAO.getFiles();
    }
}
