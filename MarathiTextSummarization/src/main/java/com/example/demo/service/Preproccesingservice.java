package com.example.demo.service;
import org.apache.poi.ss.usermodel.*;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Preproccesingservice {
	
	public String removestopwords(String words){
	    // Converting string of words to list<string>
	    List<String> wordlist = new ArrayList<String>();
	    String currword = "";
	    for (int i = 0; i < words.length(); i++) {
	        if (words.charAt(i) == ' ') {
	            wordlist.add(currword);
	            currword = "";
	        } else {
	            currword += words.charAt(i);
	        }
	    }
	    // Add the last word if it's not followed by a space
	    if (!currword.isEmpty()) {
	        wordlist.add(currword);
	    }

	    // Storing stopwords
	    List<String> stopwords = this.readStopwordsFromExcel("C:\\Users\\Hp\\OneDrive\\Desktop\\ooo\\Marathi Stopword.xlsx", "Sheet1");

	    // Removing stopwords
	    Iterator<String> iterator = wordlist.iterator();
	    while (iterator.hasNext()) {
	        String word = iterator.next();
	        if (stopwords.contains(word)) {
	            iterator.remove();
	        }
	    }

	    return wordlist.toString();
	}

	
         //tokenize words here by reading stopwords from csv file 
	public  List<String> readStopwordsFromExcel(String excelFilePath, String sheetName) {
        List<String> stopwords = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                int columnIndex = -1;
                Row firstRow = sheet.getRow(0);
                if (firstRow != null) {
                    // Find the column index with the name "stopwords"
                    for (Cell cell : firstRow) {
                        if ("Stopwords".equalsIgnoreCase(cell.getStringCellValue().trim())) {
                            columnIndex = cell.getColumnIndex();
                            break;
                        }
                    }
                    if (columnIndex != -1) {
                        // Read stopwords from the specified column
                        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                            Row row = sheet.getRow(i);
                            if (row != null) {
                                Cell cell = row.getCell(columnIndex);
                                if (cell != null && cell.getCellType() == CellType.STRING) {
                                    String stopword = cell.getStringCellValue().trim();
                                    stopwords.add(stopword);
                                }
                            }
                        }
                    } else {
                        System.out.println("Column with name 'stopwords' not found.");
                    }
                }
            } else {
                System.out.println("Sheet with name '" + sheetName + "' not found.");
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }

        return stopwords;
    }
}
