package org.kousam.main.gui.calc.scene;

import javafx.scene.control.Label;
import org.kousam.main.gui.base.NumberField;

public class AddedCostObj{
    NumberField variable_numberField;
    NumberField factor_numberField;
    NumberField price_numberField;

    Label text_label;



    public AddedCostObj(String text){


        text_label = new Label(text);
        text_label.setMinWidth(40.0);
        text_label.setMinHeight(20.0);


        variable_numberField = new NumberField();
        factor_numberField = new NumberField();
        price_numberField = new NumberField();





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

    public void setVariable(String variable){
        variable_numberField.setText(variable);
    }
    public void setFactor(String factor){
        factor_numberField.setText(factor);
    }
    public void setPrice(String price){
        price_numberField.setText(price);
    }

    public Double getVariable(){
        return getTextFieldValue(variable_numberField);
    }
    public Double getFactor(){
        return getTextFieldValue(factor_numberField);
    }
    public Double getPrice(){
        return getTextFieldValue(price_numberField);
    }

    public NumberField getVariableNumberField(){
        return variable_numberField;
    }
    public NumberField getFactorNumberField(){
        return factor_numberField;
    }
    public NumberField getPriceNumberField(){
        return price_numberField;
    }

    public Label getTextLabel(){
        return text_label;
    }

}
