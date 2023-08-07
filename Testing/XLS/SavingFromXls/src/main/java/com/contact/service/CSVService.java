package com.contact.service;

import com.contact.entity.Contact;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Arrays;

@Service
public class CSVService {

    String[] contactColumns = {"Contact Id", "Contact Name", "Number", "User Id", "status"};


//    public CSVPrinter writeCSVFile(Writer writer, Contact contact){
//        System.out.println("CSVService.writeCSVFile");
//        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
//        hssfWorkbook.
//    };

    public void importCSVFile(MultipartFile multipartFile) throws IOException {
        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(multipartFile.getInputStream(), "UTF-8"));
        Workbook workbook = WorkbookFactory.create(multipartFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);
        System.out.println(headerRow.getCell(1));
    }

}
