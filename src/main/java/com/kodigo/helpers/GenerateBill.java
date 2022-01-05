package com.kodigo.helpers;

import com.kodigo.repository.CustomerManagement;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenerateBill {

    public String generatePdf(CustomerManagement cm) {
        String filename = null;
        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {
                //Page configuration
                cont.beginText();
                cont.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 12);
                cont.setLeading(20.5f);
                cont.newLineAtOffset(50, 500);

                //The following lines writes on the PDF file
                String title = "<-¡THANK YOU FOR YOUR PURCHASE!->";
                cont.showText(title);

                cont.newLine();
                String line = "Down below are your selected items and the total";
                cont.showText(line);
                cont.appendRawCommands("'\n");

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
                String date = "DATE: " + new SimpleDateFormat("MM-dd-yyyy HH:mm").format(Calendar.getInstance().getTime());
                cont.showText(date);
                cont.appendRawCommands("'\n");

                cont.newLine();
                String subTitle = "PRODUCTS:";
                cont.showText(subTitle);

                cont.newLine();
                for (int i = 0; i < cm.getCustomer().getPurchases().get(cm.getCustomer().getPurchases().size() - 1).getProducts().size(); i++) {
                    cont.showText((i + 1) + cm.getCustomer().getPurchases().get(cm.getCustomer().getPurchases().size() - 1).getProducts().get(i).cartToString());
                    cont.appendRawCommands("'\n");
                }
                cont.newLine();
                DecimalFormat df = new DecimalFormat("#.00");
                BigDecimal total = cm.getCustomer().getPurchases().get(0).getTotal();
                BigDecimal tax = cm.getCustomer().getPurchases().get(0).getTax();
                BigDecimal subtotal = cm.getCustomer().getPurchases().get(0).getSubTotal();

                cont.showText("SUBTOTAL: $" + df.format(subtotal));
                cont.appendRawCommands("'\n");
                cont.showText("TAX $:" + df.format(tax));
                cont.appendRawCommands("'\n");
                cont.showText("TOTAL: $" + df.format(total));

                cont.endText();
            }

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(Calendar.getInstance().getTime());

            filename = "src/main/resources/bill" + cm.getCustomer().getName() + timeStamp + ".pdf";
            doc.save(filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }
}
