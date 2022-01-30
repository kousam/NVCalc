package org.kousam.main;

import org.kousam.main.exceptions.XSLXVerificationException;

import java.io.*;

public class DataManager {
    App app;
    String data_path = "data.ser";
    Double[][][] data;

    public DataManager(App _app){
        app = _app;
        data = null;
    }


    public Double[][] getAL2Table(){
        return data[0];
    }
    public Double getAL2Table(int x, int y){
        return data[0][y][x];
    }

    public Double[][] getAL3Table(){
        return data[1];
    }
    public Double getAL3Table(int x, int y){
        return data[1][y][x];
    }

    public Double getPudot(){
        return data[2][0][0];
    }

    public Double getAL2Jako(){
        return data[2][1][0];
    }
    public Double getAL3Jako(){
        return data[2][1][1];
    }
    public Double getAL2Last(){
        return data[2][2][0];
    }
    public Double getAL3Last(){
        return data[2][2][1];
    }



    public boolean loadFromSer(){
        boolean success = true;

        if(data == null){
            try {
                data = loadFromSer("data.ser");

            }catch(Exception e){
                System.out.println(e);
                success = false;
            }
        }

        return success;
    }

    private Double[][][] loadFromSer(String path) throws IOException, ClassNotFoundException {
        Double[][][] data_package = null;

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
        data_package = (Double[][][]) in.readObject();
        in.close();


        return data_package;
    }

    public void loadFromXLSX(String path) throws IOException, XSLXVerificationException {
        XLSXReader reader = new XLSXReader();



        reader.verifyDocument(path);


        Double[][][] data_package = reader.read(path);
        saveData(data_package);
    }

    public void saveData(Double[][][] data_package){
        saveFile(data_path, data_package);
        data = data_package;
    }


    private void saveFile(String path, Double[][][] data_package){

        try{
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(path)
            );

            out.writeObject(data_package);
            out.flush();
            out.close();
        }catch(IOException e){
            ;
        }
    }




}
