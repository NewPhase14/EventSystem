package sample.BLL;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.printing.PDFPageable;
import sample.BE.Event;

import javax.mail.Session;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class TicketManager {

    public void createTicket(Event event, int amount) throws IOException, PrinterException {
        for (int i = 0; i < amount; i++) {
            makePDF(event);
            // printPDF(); Do not uncomment as we do not want to actually print
            // sendMail(); Same with this
        }
    }

    private void makePDF(Event event) throws IOException {

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

        document.save(new File("resources/data/tickets/ticket.pdf"));

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

    private void sendMail() {
        Properties properties = new Properties();
        Session session = Session.getDefaultInstance(properties, null);
    }

}
