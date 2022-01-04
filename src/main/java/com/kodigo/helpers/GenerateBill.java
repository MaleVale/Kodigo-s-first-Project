package com.kodigo.helpers;
import com.kodigo.models.Product;
import com.kodigo.models.Purchase;

import com.kodigo.repository.CustomerManagement;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenerateBill {

    public void generatePDF(CustomerManagement cm){
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
                String customer = "CUSTOMER: " + cm.getCustomer().getName();
                cont.showText(customer);

                cont.newLine();
                String email = "EMAIL: " + cm.getCustomer().getEmail();
                cont.showText(email);

                cont.newLine();
                String address = "ADDRESS: " + cm.getCustomer().getAddress();
                cont.showText(address);

                cont.newLine();
                String date = "DATE: " + new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
                cont.showText(date);

                cont.newLine();
                String subTitle = "PRODUCTS:";
                cont.showText(subTitle);

                cont.newLine();
                for (int i = 0; i < cm.getCustomer().getPurchases().get(cm.getCustomer().getPurchases().size()-1).getProducts().size(); i++) {
                    cont.showText((i+1)+cm.getCustomer().getPurchases().get(cm.getCustomer().getPurchases().size()-1).getProducts().get(i).cartToString());
                    cont.appendRawCommands("'\n");
                }
                cont.newLine();
                BigDecimal total = cm.getCustomer().getPurchases().get(0).getTotal();
                cont.showText("TOTAL : $" + total);

                cont.endText();
            }

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(Calendar.getInstance().getTime());
            doc.save("src/main/resources/bill "+ timeStamp +".pdf");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
