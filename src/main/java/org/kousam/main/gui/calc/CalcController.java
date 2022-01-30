package org.kousam.main.gui.calc;

import javafx.concurrent.Task;
import org.kousam.main.gui.GUI;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base.SceneController;
import org.kousam.main.gui.calc.scene.AddedCostObj;
import org.kousam.main.gui.calc.scene.CalcPane;
import org.kousam.main.gui.calc.scene.TableObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalcController extends SceneController implements RootUtil {
    CalcPane calc_pane;

    ArrayList<TableObj> tableObj_list;
    Map<String, AddedCostObj> acObj_map;

    TableObj focused_tableObj;

    public CalcController(GUI _gui){
        super(_gui);

        tableObj_list = new ArrayList<TableObj>();
        acObj_map = new HashMap<String, AddedCostObj>();


        calc_pane = new CalcPane(this);
        setPane(calc_pane);



    }

    public void setPriceSum(String total){
        calc_pane.getRightContPane().setPriceSum(total);
    }


    // ----------------- TABLE STUFF --------------------

    public void newTableObj(){
        TableObj table_obj = new TableObj(this);
        calc_pane.getTablePane().addTableObj(table_obj);
        tableObj_list.add(table_obj);
    }

    public void deleteTableObj(TableObj table_obj){
        calc_pane.getTablePane().removeTableObj(table_obj);
        tableObj_list.remove(table_obj);

        realignRows();
    }

    public void deleteFocusedTableObj(){
        TableObj tableObj;

        if(!tableObj_list.isEmpty()){
            if(focused_tableObj != null){
                tableObj = focused_tableObj;

            }else{
                tableObj = tableObj_list.get(tableObj_list.size() - 1);
            }

            deleteTableObj(tableObj);

            focused_tableObj = null;
        }

    }

    private void realignRows(){
        calc_pane.getTablePane().clear();


        calc_pane.getTablePane().setRowCount(0);
        calc_pane.getTablePane().clearSeparators();

        for (TableObj table_obj: tableObj_list) {
            calc_pane.getTablePane().addTableObj(table_obj);
        }

    }

    public ArrayList<TableObj> getTableObjList(){
        return tableObj_list;
    }


    // -----------------------Added Cost stuff ---------------------

    public AddedCostObj newACObj(String title){
        AddedCostObj acObj = new AddedCostObj(title);

        acObj_map.put(title, acObj);

        return acObj;
    }

    public Map<String, AddedCostObj> getACObjMap(){
        return acObj_map;
    }


    // --------------------Button Actions ----------------------------

    public void calcButtonAction(){
        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                try {
                    getApp().calculate();
                }catch(Exception e){
                    System.out.println(e);
                }
                return null;
            }};
        task.run();
    }

    public void setTableVolumeSum(Double _volume){
        Integer volume_int = _volume.intValue();
        String volume_str = volume_int.toString();
        calc_pane.getTablePane().getTableBottomPane().setVolumeSum(volume_str);
    }

    public void setTablePriceSum(Double _price){
        Integer price_int = _price.intValue();
        String price_str = price_int.toString();
        calc_pane.getTablePane().getTableBottomPane().setPriceSum(price_str);
    }

    public void setFocusedTableObj(TableObj tableObj){
        focused_tableObj = tableObj;
    }


}
