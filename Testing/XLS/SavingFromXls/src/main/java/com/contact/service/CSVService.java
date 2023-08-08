package com.contact.service;

import com.contact.entity.Contact;
import com.contact.enums.ContactStatus;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CSVService {

    @Autowired
    private ContactService contactService;


//    public CSVPrinter writeCSVFile(Writer writer, Contact contact){
//        System.out.println("CSVService.writeCSVFile");
//        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
//        hssfWorkbook.
//    };

    public void importCSVFile(MultipartFile multipartFile, HttpServletResponse response) throws IOException {
        List<Contact> contacts = new ArrayList<>();
        List<String> headers  = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(multipartFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            headers.add(headerRow.getCell(i).toString());
        }
        Row row;
        for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
            row = sheet.getRow(i);
            Contact.ContactBuilder builder = Contact.builder();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                switch (headers.get(j)) {
//                    case "Contact Id" -> {
//                        Double numericCellValue = row.getCell(j).getNumericCellValue();
//                        builder.contactId(numericCellValue.longValue());
//                    }
                    case "Contact Name" -> builder.contactName(row.getCell(j).toString());
                    case "Number" -> {
                        row.getCell(j).setCellType(CellType.STRING);
                        builder.number(row.getCell(j).getStringCellValue());
                    }
                    case "User Id" -> {
                        Double numericCellValue = row.getCell(j).getNumericCellValue();
                        builder.userId(numericCellValue.longValue());
                    }
                    case "status" -> builder.status(ContactStatus.valueOf(row.getCell(j).toString()));
                }
            }
            contacts.add(builder.build());
        }
        List<Contact> unsavedContacts = this.contactService.addUserThroughExcelSheet(contacts);
        if(!Objects.isNull(unsavedContacts)) {
            int rowCount = 0;
            HSSFWorkbook hssfWorkbook =  new HSSFWorkbook();
            HSSFSheet hssfSheet = hssfWorkbook.createSheet();
            HSSFRow hssfRow;
            hssfRow = hssfSheet.createRow(rowCount);
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = hssfRow.createCell(i);
                cell.setCellValue(headers.get(i));
            }
            rowCount++;
            for (Contact unsavedContact:unsavedContacts) {
                row = hssfSheet.createRow(rowCount);
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.createCell(i);
                    switch (headers.get(i)) {
//                        case "Contact Id" -> cell.setCellValue(unsavedContact.getContactId());
                        case "Contact Name" -> cell.setCellValue(unsavedContact.getContactName());
                        case "Number" -> cell.setCellValue(unsavedContact.getNumber());
                        case "User Id" -> cell.setCellValue(unsavedContact.getUserId());
                        case "status" -> cell.setCellValue(unsavedContact.getStatus().toString());
                    }
                }
            }
//            FileOutputStream file = new FileOutputStream("Unsaved " + multipartFile.getOriginalFilename());
            response.setHeader("Content-Disposition","attachment; filename= Unsaved"+ multipartFile.getOriginalFilename() +".xlsx");
            ServletOutputStream outputStream = response.getOutputStream();
            hssfWorkbook.write(outputStream);
            outputStream.close();
        }
    }
}
