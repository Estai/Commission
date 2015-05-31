package com.epam.file.excel;

import com.epam.entity.Application;
import com.epam.entity.Enrollee;
import com.epam.manager.Helper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

public class ExcelHelper {

    public HSSFWorkbook createWorbook(){
        HSSFWorkbook workbook = new HSSFWorkbook();
        return workbook;

    }
    public HSSFWorkbook createWorbook(FileInputStream file){
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;

    }
    public HSSFWorkbook createWorbook(POIFSFileSystem poiFileSystem,boolean b ){
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(poiFileSystem,b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;

    }
    public HSSFSheet createSheet(String title,HSSFWorkbook workbook)
    {
        HSSFSheet sheet = workbook.createSheet(title);
        return sheet;
    }
    public HSSFSheet getSheet(HSSFWorkbook workbook,int numberSheet)
    {
        HSSFSheet sheet = workbook.getSheetAt(numberSheet);
        return sheet;
    }
    public void writeFile(HSSFWorkbook workbook,String path){
        try {
            FileOutputStream out =
                    new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(List<Enrollee> enrolees,HSSFSheet sheet){
        Map<String, Object[]> data = new HashMap<String, Object[]>();
        int i=1;
        for (Enrollee enrollee:  enrolees){
            data.put(String.valueOf(i), new Object[] {enrollee.getId(), enrollee.getLastName()+" "+enrollee.getFirstName()+" "+enrollee.getMiddleName(),enrollee.getAddress(),enrollee.getMobileNumber()});
       i++;
        }
        Set<String> keyset = data.keySet();
        int rownum = 3;
        for (String key : keyset) {
            System.out.println(key);
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof Date)
                    cell.setCellValue((Date)obj);
                else if(obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
    }
   public FileInputStream readFile(String path) throws FileNotFoundException {
       FileInputStream file = null;

           file = new FileInputStream(new File(path));

       return file;
   }

    public void update(List<Enrollee> enrollees,HSSFSheet sheet) {
        int i=4;
        Cell cell = null;
        for (Enrollee enrollee: enrollees){
            List<Application> applications = Helper.getInstance().getApplicationService().findByIdEnrollee(enrollee.getId());
            HSSFRow row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(String.valueOf(enrollee.getId()));
            cell = row.createCell(1);
            cell.setCellValue(enrollee.getLastName()+" "+enrollee.getFirstName()+" "+enrollee.getMiddleName());
            cell = row.createCell(2);
            cell.setCellValue(enrollee.getAddress());
            cell = row.createCell(3);
            cell.setCellValue(enrollee.getMobileNumber());
            int k=4;
            for (Application application: applications) {
                cell = row.createCell(k);
                cell.setCellValue(application.getGroupName());
                k++;
            }
            cell = row.createCell(8);
            cell.setCellValue(enrollee.getEducationalInstitution());
            i++;
        }
    }
}
