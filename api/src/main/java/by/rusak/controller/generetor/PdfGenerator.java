package by.rusak.controller.generetor;

import by.rusak.domain.Order;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {
    public void generateListOrd(List<Order> ordList, HttpServletResponse response) throws DocumentException, IOException {

        // Creating the Object of Document
        Document document = new Document(PageSize.A4);

        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        // Opening the created document to change it
        document.open();

        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);

        // Creating paragraph
        Paragraph paragraph1 = new Paragraph("List of the orders", fontTiltle);

        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in the document
        document.add(paragraph1);

        // Creating a table of the 4 columns
        PdfPTable table = new PdfPTable(8);

        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[] {1,1,1,3,3,2,3,3});
        table.setSpacingBefore(5);

        // Create Table Cells for the table header
        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding of the table cell
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);

        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        // Adding headings in the created table cell or  header
        // Adding Cell to table

        cell.setPhrase(new Phrase("id_order", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("id_user", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("id_car", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("rental_start_date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("rental_end_date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("order_price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("creation_date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("modification_date", font));
        table.addCell(cell);

        // Iterating the list of orders
        for (Order order: ordList) {
            table.addCell(String.valueOf(order.getId()));
            table.addCell(String.valueOf(order.getIdUser()));
            table.addCell(String.valueOf(order.getIdCar()));
            table.addCell(String.valueOf(order.getRentalStartDate()));
            table.addCell(String.valueOf(order.getRentalEndDate()));
            table.addCell(String.valueOf(order.getOrderPrice()));
            table.addCell(String.valueOf(order.getCreationDate()));
            table.addCell(String.valueOf(order.getModificationDate()));
        }

        // Adding the created table to the document
        document.add(table);

        // Closing the document
        document.close();

    }
}
