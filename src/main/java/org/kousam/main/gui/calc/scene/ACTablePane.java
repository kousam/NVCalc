package org.kousam.main.gui.calc.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base._GridPane;
import org.kousam.main.gui.calc.CalcController;

public class ACTablePane extends _GridPane {

    AddedCostObj pudot_acObj;
    AddedCostObj jako_acObj;
    AddedCostObj last_acObj;

    GridPane variable_grid;
    GridPane factor_grid;
    GridPane price_grid;

    public ACTablePane(RootUtil _root){
        super(_root);


        this.setHgap(8.0);
        this.setId("inner_obj");
        this.setPadding(new Insets(14, 0, 20, 20));
        this.setMinWidth(320);

        GridPane label_grid = new GridPane();
        label_grid.setPadding(new Insets(0,0,8,40));
        label_grid.setHgap(8.0);

        Label variable_label = new Label("Arvo");
        Label factor_label = new Label("Kerroin");
        Label price_label = new Label("Hinta");

        variable_label.setAlignment(Pos.CENTER);
        factor_label.setAlignment(Pos.CENTER);
        price_label.setAlignment(Pos.CENTER);

        variable_label.setPrefWidth(80.0);
        factor_label.setPrefWidth(60.0);
        price_label.setPrefWidth(80.0);

        label_grid.add(variable_label, 0,0);
        label_grid.add(factor_label, 1,0);
        label_grid.add(price_label,2,0);





        variable_grid = new GridPane();
        factor_grid = new GridPane();
        price_grid = new GridPane();


        variable_grid.setVgap(4.0);
        factor_grid.setVgap(4.0);
        price_grid.setVgap(4.0);


        variable_grid.setPrefWidth(120.0);
        factor_grid.setPrefWidth(60.0);
        price_grid.setPrefWidth(80.0);


        pudot_acObj = ((CalcController) getController()).newACObj("Pudot");
        jako_acObj = ((CalcController) getController()).newACObj("Jako");
        last_acObj = ((CalcController) getController()).newACObj("Last");








        pudot_acObj.getFactorNumberField().setEditable(false);
        jako_acObj.getFactorNumberField().setEditable(false);
        last_acObj.getFactorNumberField().setEditable(false);


        variable_grid.add(pudot_acObj.getTextLabel(), 0, 0);
        variable_grid.add(jako_acObj.getTextLabel(), 0, 1);
        variable_grid.add(last_acObj.getTextLabel(), 0, 2);

        variable_grid.add(pudot_acObj.getVariableNumberField(), 1, 0);
        variable_grid.add(jako_acObj.getVariableNumberField(), 1, 1);
        variable_grid.add(last_acObj.getVariableNumberField(), 1, 2);

        factor_grid.add(pudot_acObj.getFactorNumberField(), 0, 0);
        factor_grid.add(jako_acObj.getFactorNumberField(), 0, 1);
        factor_grid.add(last_acObj.getFactorNumberField(), 0, 2);

        price_grid.add(pudot_acObj.getPriceNumberField(), 0, 0);
        price_grid.add(jako_acObj.getPriceNumberField(), 0, 1);
        price_grid.add(last_acObj.getPriceNumberField(), 0, 2);


        this.add(label_grid, 0,0, 3,1);
        this.add(variable_grid, 0, 1);
        this.add(factor_grid, 1, 1);
        this.add(price_grid, 2, 1);


    }

}
