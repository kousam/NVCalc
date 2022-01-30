package org.kousam.main.gui.calc.scene;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base.NumberField;
import org.kousam.main.gui.base._Obj;
import org.kousam.main.gui.calc.CalcController;

import java.util.ArrayList;

public class TableObj extends _Obj {

    NumberField distance_numberField;
    NumberField volume_numberField;
    NumberField calcVolume_numberField;
    NumberField factor_numberField;
    NumberField price_numberField;

    Button delete_button;

    ArrayList<Node> node_list;

    Image delete_true_img = new Image("images/delete_true.png");

    ImageView delete_false_view;

    boolean isMinObj;

    public TableObj(RootUtil _root){
        super(_root);

        node_list = new ArrayList<Node>();

        delete_false_view = new ImageView(delete_true_img);
        delete_false_view.setFitHeight(10);
        delete_false_view.setPreserveRatio(true);


        distance_numberField = new TableNumberField(this);
        volume_numberField = new TableNumberField(this);
        calcVolume_numberField = new TableNumberField(this);
        factor_numberField = new TableNumberField(this);
        price_numberField = new TableNumberField(this);

        distance_numberField.setId("tableObj_normal");
        volume_numberField.setId("tableObj_normal");
        calcVolume_numberField.setId("tableObj_normal");
        factor_numberField.setId("tableObj_normal");
        price_numberField.setId("tableObj_normal");

        distance_numberField.setPrefWidth(80);
        volume_numberField.setPrefWidth(80);
        calcVolume_numberField.setPrefWidth(80);
        factor_numberField.setPrefWidth(60);
        price_numberField.setPrefWidth(80);

        price_numberField.setEditable(false);
        price_numberField.setText("");

        delete_button = new Button();
        delete_button.setId("delete_button");
        delete_button.setGraphic(delete_false_view);
        delete_button.setAlignment(Pos.CENTER);
        delete_button.setMaxWidth(16.0);
        delete_button.setMaxHeight(16.0);
        delete_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteButtonAction();

            }
        });



        node_list.add(distance_numberField);
        node_list.add(volume_numberField);
        node_list.add(calcVolume_numberField);
        node_list.add(factor_numberField);
        node_list.add(price_numberField);
        node_list.add(delete_button);

        isMinObj = false;

    }

    public Double getTextFieldValue(NumberField tf){
        Double value;
        try {
            value = Double.parseDouble(tf.getText());
        } catch (Exception e) {
            value = null;
        }

        return value;
    }

    public Double getDistance(){
        return getTextFieldValue(distance_numberField);
    }
    public Double getVolume(){
        return getTextFieldValue(volume_numberField);
    }
    public Double getCalcVolume(){
        return getTextFieldValue(calcVolume_numberField);
    }
    public Double getFactor(){
        return getTextFieldValue(factor_numberField);
    }
    public Double getPrice(){
        return getTextFieldValue(price_numberField);
    }


    public void setCalcVolume(String text){
        calcVolume_numberField.setText(text);
    }

    public void setFactor(String text){
        factor_numberField.setText(text);
    }

    public void setPrice(String text){
        price_numberField.setText(text);
    }

    public NumberField getDistanceNumberField(){
        return distance_numberField;
    }
    public NumberField getVolumeNumberField(){
        return volume_numberField;
    }
    public NumberField getCalcVolumeNumberField(){
        return calcVolume_numberField;
    }
    public NumberField getFactorNumberField(){
        return factor_numberField;
    }
    public NumberField getPriceNumberField(){
        return price_numberField;
    }

    public Button getDeleteButton(){
        return delete_button;
    }


    public void deleteButtonAction(){
        TableObj table_obj = this;

        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                try {
                    CalcController controller;
                    controller = (CalcController) getController();
                    controller.deleteTableObj(table_obj);

                }catch(Exception e){
                    System.out.println(e);
                }

                return null;
            }};
        task.run();
    }

    public void setMinObj(boolean value){
        if(value){
            calcVolume_numberField.setId("tableObj_bold");
        }else{
            calcVolume_numberField.setId("tableObj_normal");
        }
    }


    public void clear(){
        calcVolume_numberField.setText("");
        factor_numberField.setText("");
        price_numberField.setText("");
    }


}
