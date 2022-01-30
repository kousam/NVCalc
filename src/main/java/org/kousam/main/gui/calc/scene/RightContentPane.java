package org.kousam.main.gui.calc.scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base.SceneController;
import org.kousam.main.gui.base._GridPane;
import org.kousam.main.gui.calc.CalcController;

public class RightContentPane extends _GridPane {




    Label sumNum_label;


    public RightContentPane(RootUtil _root) {
        super(_root);

        //this.setPrefWidth(400);

        this.setHgap(0.0);
        this.setVgap(40.0);
        this.setPadding(new Insets(40, 0, 0, 0));




        TBGridPane topButton_grid = new TBGridPane(this);



        ACTablePane table_grid = new ACTablePane(this);


        BorderPane calc_border = new BorderPane();
        calc_border.setId("inner_obj");
        calc_border.setPadding(new Insets(20,30,20,30));
        calc_border.setMinSize(320,80);


        Button calculate_button = new Button("Laske");
        calculate_button.setId("calc_button");
        calculate_button.setMinSize(120, 40);
        GridPane.setHalignment(calculate_button, HPos.CENTER);
        GridPane.setValignment(calculate_button, VPos.BOTTOM);
        calculate_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneController controller = getController();
                ((CalcController) controller).calcButtonAction();
            }
        });


        calc_border.setCenter(calculate_button);






        GridPane sum_grid = new GridPane();
        sum_grid.setPadding(new Insets(10,10,10,10));

        Label sum_label = new Label("Summa");
        sum_label.setMinSize(100, 40);
        sum_label.setAlignment(Pos.CENTER);
        sum_label.setId("sum_label");

        sumNum_label = new Label("0.00 €");
        sumNum_label.setMinSize(180, 40);
        sumNum_label.setAlignment(Pos.BASELINE_RIGHT);
        sumNum_label.setId("sum_label");


        sum_grid.add(sum_label, 0, 0);
        sum_grid.add(sumNum_label, 1, 0);

        sum_grid.setId("inner_obj");


        this.add(topButton_grid, 0,0);
        this.add(table_grid, 0, 1);
        this.add(sum_grid, 0, 2);
        this.add(calc_border,0,3);


    }

    public void setPriceSum(String price_sum) {
        sumNum_label.setText(price_sum + " €");
    }


}

