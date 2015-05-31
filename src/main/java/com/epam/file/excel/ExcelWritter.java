package com.epam.file.excel;

import com.epam.entity.Enrollee;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.util.List;
import java.util.ResourceBundle;

public class ExcelWritter {
    ExcelHelper helper=new ExcelHelper();
    ResourceBundle resourceBundle=ResourceBundle.getBundle("app");

    public void write(List<Enrollee> enrollees){

        HSSFWorkbook worbook = helper.createWorbook();
        HSSFSheet sheet = helper.createSheet("Enrollee",worbook );
        helper.write(enrollees,sheet);
        helper.writeFile(worbook, resourceBundle.getString("pathExcel"));

    }
    public void update(List<Enrollee> enrollees){
        FileInputStream file = null;
        try {
            file = helper.readFile(resourceBundle.getString("pathExcel"));
            HSSFWorkbook worbook = helper.createWorbook(file);
            HSSFSheet sheet = helper.getSheet(worbook, 0);
            helper.update(enrollees,sheet);

            try {
                file.close();
            } catch (IOException e) {
                System.err.println(e);
            }
            helper.writeFile(worbook,resourceBundle.getString("pathExcel"));
        } catch (FileNotFoundException e) {
           this.write(enrollees);
        }


    }
    public void protect()  {



       FileInputStream file = null;
        BufferedInputStream bufferInput = null;
        POIFSFileSystem poiFileSystem = null;
        FileOutputStream fileOut = null;

        try {

            file = helper.readFile(resourceBundle.getString("pathExcel"));
            bufferInput = new BufferedInputStream(file);
            poiFileSystem = new POIFSFileSystem(bufferInput);

            Biff8EncryptionKey.setCurrentUserPassword("secret");
            HSSFWorkbook workbook = helper.createWorbook(poiFileSystem, true);
            HSSFSheet sheet = helper.getSheet(workbook,0);
            fileOut = new FileOutputStream(resourceBundle.getString("pathExcel"));
            workbook.writeProtectWorkbook(Biff8EncryptionKey.getCurrentUserPassword(), "");
            workbook.write(fileOut);



        } catch (Exception ex) {

            System.out.println(ex.getMessage());

        } finally {

            try {

                bufferInput.close();

            } catch (IOException ex) {

                System.out.println(ex.getMessage());

            }

            try {

                fileOut.close();

            } catch (IOException ex) {

                System.out.println(ex.getMessage());

            }
        }

    }
    public void readPassword()

    {
        NPOIFSFileSystem fs = null;
        try {
            fs = new NPOIFSFileSystem(new File(resourceBundle.getString("pathExcel")));
            EncryptionInfo info = new EncryptionInfo(fs);
            Decryptor d = Decryptor.getInstance(info);
            if (d.verifyPassword("secret")) {
                HSSFWorkbook wb = new HSSFWorkbook(d.getDataStream(fs));
            } else {
                // Password is wrong
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
