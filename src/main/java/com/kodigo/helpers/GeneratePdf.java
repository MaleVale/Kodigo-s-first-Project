package com.kodigo.helpers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kodigo.interfaces.FileManager;
import com.kodigo.models.Product;
import com.kodigo.repository.CustomerManagement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Stream;

public class GeneratePdf implements FileManager {
    public String generateFile() throws IOException, DocumentException {
        String timeStamp = new
            SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(Calendar.getInstance().getTime());
        String filename = "src/main/resources/bill" +
                CustomerManagement.getCustomer().getName() +
                timeStamp + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(
            document, new FileOutputStream(filename)
        );

        int idPurchase = CustomerManagement.getCustomer().getPurchases().size() - 1;

        document.open();

        Font fontBold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font fontNormal = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

        Paragraph headerText = new Paragraph();
        addHeaderText(headerText);

        Paragraph customerData = new Paragraph();
        addCustomerData(customerData, fontNormal, fontBold);

        PdfPTable table = new PdfPTable(4);
        addTableHeader(table);
        addRows(table, idPurchase);

        Paragraph purchaseDetails = new Paragraph();
        addPurchaseDetails(purchaseDetails, idPurchase, fontNormal, fontBold);

        document.add(headerText);
        document.add(customerData);
        document.add(table);
        document.add(purchaseDetails);

        document.close();
        return filename;
    }

    private void addHeaderText(Paragraph headerText){
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        headerText.add(new Phrase("Thank you for your purchase!", fontTitulo));
        headerText.add(new Phrase(Chunk.NEWLINE));
        headerText.add(new Phrase(Chunk.NEWLINE));
        headerText.add(new Phrase("Down below are your selected items and the total.", fontTitulo));
        headerText.add(new Phrase(Chunk.NEWLINE));
        headerText.add(new Phrase(Chunk.NEWLINE));
        headerText.setAlignment(Element.ALIGN_CENTER);
    }

    private void addCustomerData(Paragraph customerData, Font fontNormal, Font fontBold){
        customerData.add(new Phrase("Customer: ", fontBold));
        customerData.add(new Phrase(CustomerManagement.getCustomer().getName(), fontNormal));

        customerData.add(Chunk.NEWLINE);

        customerData.add(new Phrase("Email: ", fontBold));
        customerData.add(new Phrase(CustomerManagement.getCustomer().getEmail(), fontNormal));

        customerData.add(Chunk.NEWLINE);

        customerData.add(new Phrase("Address: ", fontBold));
        customerData.add(new Phrase(CustomerManagement.getCustomer().getAddress(), fontNormal));

        customerData.add(Chunk.NEWLINE);
        customerData.add(new Phrase("Date: ", fontBold));
        customerData.add(new Phrase(
            new SimpleDateFormat("MM-dd-yyyy HH:mm").
                format(Calendar.getInstance().getTime()), fontNormal
        ));

        customerData.add(Chunk.NEWLINE);
        customerData.add(Chunk.NEWLINE);
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Shoes", "Units", "Price", "Amount")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, int id) {
        ArrayList<Product> customerProducts = CustomerManagement.getCustomer().getPurchases().get(CustomerManagement.getCustomer().getPurchases().size() - 1).getProducts();
        for (int i = 0; i < CustomerManagement.getCustomer().getPurchases().get(id).getProducts().size(); i++) {
            table.addCell(customerProducts.get(i).getName());
            table.addCell(String.valueOf(customerProducts.get(i).getStock()));
            table.addCell("$" + customerProducts.get(i).getPrice());
            table.addCell("$" + customerProducts.get(i).getAmount());
        }

    }

    private void addPurchaseDetails(Paragraph purchaseDetails, int id, Font fontNormal, Font fontBold){
        DecimalFormat df = new DecimalFormat("#.00");


        purchaseDetails.add(Chunk.NEWLINE);

        purchaseDetails.add(new Phrase("Subtotal: ", fontBold));
        purchaseDetails.add(
            new Phrase("$" + df.format(CustomerManagement.getCustomer().getPurchases().get(id).getSubTotal()), fontNormal)
        );

        purchaseDetails.add(Chunk.NEWLINE);

        purchaseDetails.add(new Phrase("Taxes: ", fontBold));
        purchaseDetails.add(
            new Phrase("$" + df.format(CustomerManagement.getCustomer().getPurchases().get(id).getTax()), fontNormal)
        );

        purchaseDetails.add(Chunk.NEWLINE);

        purchaseDetails.add(new Phrase("Total: ", fontBold));
        purchaseDetails.add(
            new Phrase("$" + df.format(CustomerManagement.getCustomer().getPurchases().get(id).getTotal()), fontNormal)
        );
    }



}
