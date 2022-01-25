package com.kodigo.helpers;

import com.kodigo.interfaces.FileManager;
import com.kodigo.models.Product;
import com.kodigo.repository.CustomerManagement;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GenerateExcel implements FileManager {
    public String generateFile() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Persons");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 2000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 3000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Shoes");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Units");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Price");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Amount");
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        int idPurchase = CustomerManagement.getCustomer().getPurchases().size() - 1;
        ArrayList<Product> customerProducts = CustomerManagement.getCustomer().getPurchases().get(idPurchase).getProducts();

        Row row;
        Cell cell;
        int rowCount = 1;

        for (int i = 0; i < CustomerManagement.getCustomer().getPurchases().get(idPurchase).getProducts().size(); i++) {
            row = sheet.createRow(rowCount);
            cell = row.createCell(0);
            cell.setCellValue(customerProducts.get(i).getName());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(customerProducts.get(i).getStock());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("$"+customerProducts.get(i).getPrice());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue("$"+customerProducts.get(i).getAmount());
            cell.setCellStyle(style);

            rowCount++;
        }

        DecimalFormat df = new DecimalFormat("#.00");

        int rowAfterData = CustomerManagement.getCustomer().getPurchases().get(idPurchase).getProducts().size()+2;

        row = sheet.createRow(rowAfterData);

        cell = row.createCell(4);
        cell.setCellValue("Subtotal:");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("$"+df.format(CustomerManagement.getCustomer().getPurchases().get(idPurchase).getSubTotal()));
        cell.setCellStyle(style);

        row = sheet.createRow(rowAfterData + 1);

        cell = row.createCell(4);
        cell.setCellValue("Taxes:");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("$"+df.format(CustomerManagement.getCustomer().getPurchases().get(idPurchase).getTax()));
        cell.setCellStyle(style);

        row = sheet.createRow(rowAfterData + 2);

        cell = row.createCell(4);
        cell.setCellValue("Total:");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("$"+df.format(CustomerManagement.getCustomer().getPurchases().get(idPurchase).getTotal()));
        cell.setCellStyle(style);

        String timeStamp = new
                SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(Calendar.getInstance().getTime());
        String filename = "src/main/resources/bill" +
                CustomerManagement.getCustomer().getName() +
                timeStamp + ".xlsx";
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + filename;

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
        return filename;
    }


}
