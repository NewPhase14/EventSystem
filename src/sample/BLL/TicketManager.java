package sample.BLL;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.printing.PDFPageable;
import sample.BE.Event;
import sample.DAL.TicketDAO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class TicketManager {

    // private static final String PROP_FILE = "config/config.settings";
    private List<File> files;

    private TicketDAO ticketDAO;

    public TicketManager() {
        ticketDAO = new TicketDAO();
    }

    public void createTicket(Event event, int amount, String email) throws IOException, PrinterException, MessagingException {
        for (int i = 0; i < amount; i++) {
            makePDF(event, amount);
            // printPDF(); Do not uncomment as we do not want to actually print

        }
        sendMail(email, amount);
    }

    private void makePDF(Event event, int index) throws IOException {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setTitle("Ticket");

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

        contentStream.close();

        document.save(new File("resources/data/tickets/ticket" + index + ".pdf"));

        System.out.println("It worked");

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
        Properties properties = new Properties();
        properties.put("email", "emailname");
        Session session = Session.getDefaultInstance(properties, null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom("actualmail");
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Event Ticket");
        message.setSentDate(new Date());

        MimeBodyPart content = new MimeBodyPart();
        content.setText("Amount of tickets: " + amount);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(content);
        files = getCurrentFiles();
        for (File file : files) {
            MimeBodyPart attachment = new MimeBodyPart();
            // not done
        }


        Transport.send(message, "email", "password");

    }

    private List<File> getCurrentFiles() {
        return ticketDAO.getFiles();
    }

}
