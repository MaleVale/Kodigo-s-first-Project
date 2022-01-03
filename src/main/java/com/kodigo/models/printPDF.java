package com.kodigo.models;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class printPDF {

    public void print(int id, int date , int total, int taxes) {

        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {
                //Page configuration
                cont.beginText();
                cont.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD_ITALIC), 12);
                cont.setLeading(20.5f);
                cont.newLineAtOffset(50, 500);

                //The following lines writes on the PDF file
                String title = "<                                                 -¡THANK YOU FOR YOUR PURCHASE!-                                             >";
                cont.showText(title);

                cont.newLine();
                String line = "                                               Down below are your selected items and the total                       ";
                cont.showText(line);

                cont.newLine();
                String Date = "Date:";
                cont.showText(Date);

                cont.newLine();
                String products = "Products:";
                cont.showText(products);

                cont.newLine();
                String subTotal = "Subtotal";
                cont.showText(subTotal);

                cont.newLine();
                String Tax = "Taxes";
                cont.showText(Tax);

                cont.newLine();
                String Total = "Total";
                cont.showText(Total);

                cont.endText();
            }

            doc.save("src/main/resources/bill.pdf");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

