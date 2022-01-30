package org.kousam.main;

import org.kousam.main.exceptions.XSLXVerificationException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XLSXReader {

    public static Double[][][] read(String path){
        InputStream xlsx_file = null;

        boolean file_found = false;
        Double[][][] data_package = new Double[3][][];
        XSSFSheet al2_sheet = null;
        XSSFSheet al3_sheet = null;
        XSSFSheet data_sheet = null;


        try{
            xlsx_file = new FileInputStream(path);
            XSSFWorkbook  wb = new XSSFWorkbook(xlsx_file);
            al2_sheet = wb.getSheetAt(0);
            al3_sheet = wb.getSheetAt(1);
            data_sheet = wb.getSheetAt(2);

            file_found = true;

        }catch(IOException e){
            System.out.println(e);
        }



        if(file_found){
            Double[][] al2_data = getAL2Data(al2_sheet);
            Double[][] al3_data = getAL3Data(al3_sheet);
            Double[][] ac_data = getACData(data_sheet);

            data_package[0] = al2_data;
            data_package[1] = al3_data;
            data_package[2] = ac_data;

        }

        try{
            xlsx_file.close();
        }catch(Exception e){
            ;
        }


        return data_package;

    }

    private static Double getCellValue(XSSFSheet sheet, int x, int y){
        Double value;

        XSSFCell cell = sheet.getRow(y).getCell(x);

        value = cell.getNumericCellValue();
        value = Math.round(value * 100.0) /100.0;

        return value;
    }

    private static Double[][] getData(XSSFSheet sheet, int x1, int x2, int y1, int y2){
        int w = x2 - x1 + 1;
        int h = y2 - y1 + 1;

        Double[][] y_list = new Double[h][];

        int y_count = 0;
        while(y1 + y_count <= y2) {
            Double[] x_list = new Double[w];

            int x_count = 0;
            while (x2 - x_count >= x1) {
                int x = x2 - x_count;
                int y = y1 + y_count;

                Double value = getCellValue(sheet, x,y);


                x_list[x_count] = value;


                x_count += 1;
            }

            y_list[y_count] = x_list;

            y_count += 1;
        }

        return y_list;
    }



    private static Double[][] getAL2Data(XSSFSheet sheet){
        Double[][] data_package = getData(sheet, 1,8,5, 104);

        return data_package;
    }

    private static Double[][] getAL3Data(XSSFSheet sheet){
        Double[][] data_package = getData(sheet, 1,10,5,104);

        return data_package;
    }

    private static Double[][] getACData(XSSFSheet sheet){
        Double[][] data_package = new Double[3][2];

        Double[] pudot = new Double[2];
        pudot[0] = getCellValue(sheet, 1,6);

        Double[] jako = new Double[2];
        jako[0] = getCellValue(sheet, 1,8);
        jako[1] = getCellValue(sheet, 1,9);

        Double[] last = new Double[2];
        last[0] = getCellValue(sheet, 1,11);
        last[1] = getCellValue(sheet, 1,12);

        data_package[0] = pudot;
        data_package[1] = jako;
        data_package[2] = last;

        return data_package;

    }

    public void verifyDocument(String path) throws XSLXVerificationException, IOException {
        InputStream xlsx_file = null;
        XSSFWorkbook wb;

        try {
            xlsx_file = new FileInputStream(path);
            wb = new XSSFWorkbook(xlsx_file);
            xlsx_file.close();
        }catch(IOException e){
            throw new XSLXVerificationException("File not found");
        }catch(Exception e){
            xlsx_file.close();
            throw new XSLXVerificationException();

        }

        if (wb.getNumberOfSheets() != 3){
            xlsx_file.close();
            throw new XSLXVerificationException();

        }
        if(!wb.getSheetAt(0).getSheetName().equals("AL2")){
            xlsx_file.close();
            throw new XSLXVerificationException();
        }
        if(!wb.getSheetAt(1).getSheetName().equals("AL3")){
            xlsx_file.close();
            throw new XSLXVerificationException();
        }
        if(!wb.getSheetAt(2).getSheetName().equals("DATA")) {
            xlsx_file.close();
            throw new XSLXVerificationException();
        }
    }
}
