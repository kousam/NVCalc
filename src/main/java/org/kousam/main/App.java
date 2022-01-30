package org.kousam.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.kousam.main.gui.GUI;
import org.kousam.main.gui.base.MessagePane;
import org.kousam.main.gui.calc.CalcController;
import org.kousam.main.gui.calc.scene.AddedCostObj;
import org.kousam.main.gui.calc.scene.TableObj;

import java.util.ArrayList;
import java.util.Map;

public class App {
    GUI gui;
    DataManager data_mgr;

    String drive_class;


    Double[][] volume_ranges = {{Double.MIN_VALUE,2000.0},          //volume ranges never change
            {2000.0,3000.0},                                        //therefore hardcoded
            {3000.0,4500.0},                                        //im lazy
            {4500.0,6500.0},
            {6500.0,9000.0},
            {9000.0,12500.0},
            {12500.0,17000.0},
            {17000.0,23000.0},
            {23000.0,31000.0},
            {31000.0, Double.MAX_VALUE}

    };

    public App(GUI _gui){
        data_mgr = new DataManager(this);

        gui = _gui;
        gui.setApp(this);

        drive_class = "AL2";

        loadData();


    }



    public void loadData(){
        boolean success = data_mgr.loadFromSer();

        if(!success){
            String msg = "Tietojen lataus\nEpäonnistui";
            String info = "Hae tiedot Excel-Asiakirjasta";
            MessagePane msg_pane = gui.getCurrController().setMessage(msg);
            msg_pane.setInfo(info);
            msg_pane.addCancelButton();

            msg_pane.getOkButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    msg_pane.cancel();
                    gui.setScene("dragDrop");
                }
            });

        } else{
            loadACObjFactors();
        }




    }


    public void loadFromXLSL(String path, String name){
        String message = "";
        String info = "";

        try{
            data_mgr.loadFromXLSX(path);
            message = "Valmis!";
            info = "Tiedot hauettu Excel-asiakirjasta\n" + name;

            loadACObjFactors();

        }catch(Exception e){
            message = "Asiakirjan lataus\nEpäonnistui";
            info = e.getMessage();

        }

        MessagePane msg_pane = gui.getCurrController().setMessage(message);
        msg_pane.setInfo(info);
        msg_pane.addCancelButton();


    }

    private boolean validateTableObj(TableObj table_obj) {
        //this gets all valid table objects from table
        //a valid table object has values in distance_numberField and volume_numberField

        boolean valid = false;
        if (table_obj.getVolume() != null && table_obj.getDistance() != null) {
            valid = true;
        }
        return valid;
    }


    private String formatPrice(Double price){
        //this exist because when typecasting Double to String
        //zeros at the end dont show up
        //ex Double 34.50 -> 34.50€
        //its very scuffed plox no read


        Double cent_double = price % 1;                                     //get cents
        Double euro_double = price - cent_double;                           //get euros
        cent_double = cent_double * 100.0;

        cent_double = Double.valueOf(Math.round(cent_double));              //round cents (just in case)

        Integer cent_int = cent_double.intValue();                          //typecast to Integer
        String cent_str = cent_int.toString();                              //typecast to String

        if(cent_str.length() < 2){                                          //this is retarded
            cent_str = "0" + cent_str;
        }

        Integer euro_int = euro_double.intValue();                          //typecast to Integer
        String euro_str = euro_int.toString();                              //typecast to String

        String price_str = euro_str + "." + cent_str;                       //combine the two

        return price_str;
    }

    private void volumeCalculations(ArrayList<TableObj> valid_tableObj_list, Double volume_sum){


        Double min_volume;
        switch(drive_class){                                                                    //get the min volume
            case "AL2":                                                                         //according to drive_class
                min_volume = 14000.0;
                break;
            case "AL3":
                min_volume = 28000.0;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + drive_class);

        }
        if(!valid_tableObj_list.isEmpty()) {
            for (TableObj table_obj : valid_tableObj_list) {                                    //add all volumes to
                Integer volume_int = table_obj.getVolume().intValue();                          //corresponding calcVolume_numberFields
                table_obj.setCalcVolume(volume_int.toString());
                table_obj.setMinObj(false);
            }

            if (volume_sum < min_volume){                                                       // if volume less than min volume
                TableObj min_tableObj = getMinObj(valid_tableObj_list);                         // get minObject
                Double calc_volume = min_volume - (volume_sum - min_tableObj.getVolume());      // get new volume
                Integer calc_volume_int = calc_volume.intValue();
                min_tableObj.setCalcVolume(calc_volume_int.toString());                         //set new volume to calcVolume_numberField
                min_tableObj.setMinObj(true);
            }
        }
    }

    private Double getFactor(Double distance, Double volume){
        //this pulls all the factors from the data_package according to distance and calcVolume

        Double factor = null;
        int x = 0;
        int y = 0;

        for(int i = 0 ; i < volume_ranges.length -1; i++){             //iterate over volume_ranges
            Double[] range = volume_ranges[i];
            Double min = range[0];
            Double max = range[1];
            if(min <= volume && volume < max){                      //if volume in range set i as the x value
                x = i;
                break;
            }

            if(i == volume_ranges.length - 1){
                x = volume_ranges.length - 1;
            }

        }


        Double y_calc = (Math.round(distance / 10.0)) - 1.0;        // get index for y (distances step with 10km)
        y = y_calc.intValue();

        if(y < 0){                                                  // check if its in range of table
            y = 0;                                                  // min index = 0
        }else if(y > 99){                                           // max index = 100
            y = 99;
        }

        switch(drive_class){                                        //get factor
            case "AL2":
                factor = data_mgr.getAL2Table(x,y);
                break;
            case "AL3":
                factor = data_mgr.getAL3Table(x,y);
                break;
            default:
                factor = data_mgr.getAL2Table(x,y);
                break;
        }

        return factor;
    }

    private void loadFactors(ArrayList<TableObj> valid_tableObj_list){
        //this loads all factors and adds them to table

        for(TableObj table_obj : valid_tableObj_list){
            Double distance = table_obj.getDistance();
            Double CalcVolume = table_obj.getCalcVolume();

            Double factor_double = getFactor(distance, CalcVolume);
            table_obj.setFactor(factor_double.toString());
        }
    }

    private TableObj getMinObj(ArrayList<TableObj> tableObj_list){
        //gets the objects with smallest distance
        //and then from them the one with smallest volume

        TableObj min_tableObj = null;
        Double shortest_dist = Double.MAX_VALUE;
        Double smallest_volume = Double.MAX_VALUE;

        ArrayList<TableObj> min_candidates = new ArrayList<TableObj>();

        for (TableObj table_obj : tableObj_list){
            Double temp_dist = table_obj.getDistance();
            if(temp_dist < shortest_dist){
                shortest_dist = temp_dist;
            }
        }

        for (TableObj table_obj : tableObj_list){
            if(table_obj.getDistance().equals(shortest_dist)){
                min_candidates.add(table_obj);
            }
        }

        for (TableObj table_obj : min_candidates){
            Double temp_volume = table_obj.getVolume();
            if(temp_volume <= smallest_volume){
                smallest_volume = temp_volume;
                min_tableObj = table_obj;
            }
        }

        return min_tableObj;
    }

    private Double tablePriceCalculations(ArrayList<TableObj> valid_tableObj_list){
        //gets a volumes and factors of tableObj
        //multiplies them
        //adds to sum

        Double price_sum = 0.0;

        for(TableObj table_obj : valid_tableObj_list){
            Double calc_volume;
            Double factor;
            Double price;

            calc_volume = table_obj.getCalcVolume();
            if(calc_volume < 1000.0){                               // min volume = 1000 liters
                calc_volume = 1000.0;
            }

            factor = table_obj.getFactor();
            price = Math.round(calc_volume * factor) / 100.0;       // divides by 100 because factor = cents/liter

            price_sum += price;                                     // add to sum

            String price_str = formatPrice(price);                  // add price to table
            table_obj.setPrice(price_str);
        }

        return price_sum;
    }

    private Double acPriceCalculations(Map<String, AddedCostObj> acObj_map){
        //sums the added prices

        Double price_sum = 0.0;

        for (Map.Entry<String, AddedCostObj> entry: acObj_map.entrySet()){
            AddedCostObj acObj = entry.getValue();

            Double variable;
            Double factor;

            variable = acObj.getVariable();
            factor = acObj.getFactor();

            if(variable != null && factor != null){
                Double price = Math.round(variable * factor * 100.0) / 100.0;       // round to 2 decimals

                price_sum += price;                                                 //add to price sum

                String price_str = formatPrice(price);                              //add price to table
                acObj.setPrice(price_str);
            }
        }

        return price_sum;
    }


    public void calculate(){
        // main calculation method
        // this is the method that is called when calculate button is pressed
        CalcController controller = (CalcController) gui.getSceneController("calc");

        ArrayList<TableObj> tableObj_list = gui.getTableObjList();              //get table
        Map<String, AddedCostObj> acObj_map = gui.getACObjMap();                //get addedCost objects

        ArrayList<TableObj> valid_tableObj_list = new ArrayList<TableObj>();

        Double price_sum = 0.0;
        Double volume_sum = 0.0;

        for (TableObj table_obj : tableObj_list){                               // adds all valid tableObjects to list
            table_obj.clear();
            if(validateTableObj(table_obj)) {                                   // check if valid
                volume_sum += table_obj.getVolume();
                valid_tableObj_list.add(table_obj);
            }
        }

        controller.setTableVolumeSum(volume_sum);

        if (!valid_tableObj_list.isEmpty()) {                                   // do table calculations
            volumeCalculations(valid_tableObj_list, volume_sum);                // do volume calculations
            loadFactors(valid_tableObj_list);                                   // load factors
            price_sum += tablePriceCalculations(valid_tableObj_list);           // do price calculations and add to sum
        }

        //controller.setTablePriceSum(price_sum);

        price_sum += acPriceCalculations(acObj_map);                            // do added cost calculations and add to sum
        String price_sum_str = formatPrice(price_sum);

        gui.setPriceSum(price_sum_str);                                         //update priceSum_label in gui
    }



    public void setDriveClass(String _drive_class){
        drive_class = _drive_class;
        loadACObjFactors();


    }

    public void loadACObjFactors(){
        CalcController controller = (CalcController) gui.getSceneController("calc");

        String pudot_factor;
        String jako_factor;
        String last_factor;

        pudot_factor = data_mgr.getPudot().toString();

        switch(drive_class){
            case "AL2":
                jako_factor = data_mgr.getAL2Jako().toString();
                last_factor = data_mgr.getAL2Last().toString();
                break;
            case "AL3":
                jako_factor = data_mgr.getAL3Jako().toString();
                last_factor = data_mgr.getAL3Last().toString();
                break;
            default:
                jako_factor = data_mgr.getAL2Jako().toString();
                last_factor = data_mgr.getAL2Last().toString();
                break;
        }


        controller.getACObjMap().get("Pudot").setFactor(pudot_factor);
        controller.getACObjMap().get("Jako").setFactor(jako_factor);
        controller.getACObjMap().get("Last").setFactor(last_factor);
    }

}
