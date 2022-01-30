package org.kousam.main.gui.settings;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base.ContentPane;
import org.kousam.main.gui.base.NumberField;

public class PriceChangePane extends ContentPane {


    public PriceChangePane(RootUtil _root){
        super(_root);




        GridPane table_grid = new GridPane();
        table_grid.setVgap(30.0);

        GridPane pudot_grid = new GridPane();
        pudot_grid.setVgap(8.0);
        pudot_grid.setHgap(16.0);

        GridPane jako_grid = new GridPane();
        jako_grid.setVgap(8.0);
        jako_grid.setHgap(16.0);

        GridPane last_grid = new GridPane();
        last_grid.setVgap(8.0);
        last_grid.setHgap(16.0);

        Label pudot_label = new Label("Pudotuket");
        Label jako_label = new Label("Jakomaksu");
        Label last_label = new Label("Tuntityö");

        Label pudotAL2_label = new Label("AL2");
        Label pudotAL3_label = new Label("AL3");

        Label jakoAL2_label = new Label("AL2");
        Label jakoAL3_label = new Label("AL3");

        Label lastAL2_label = new Label("AL2");
        Label lastAL3_label = new Label("AL3");

        NumberField pudotAL2_numberField = new NumberField();
        NumberField pudotAL3_numberField = new NumberField();

        NumberField jakoAL2_numberField = new NumberField();
        NumberField jakoAL3_numberField = new NumberField();

        NumberField lastAL2_numberField = new NumberField();
        NumberField lastAL3_numberField = new NumberField();

        pudotAL2_numberField.setPrefWidth(80.0);
        pudotAL3_numberField.setPrefWidth(80.0);
        jakoAL2_numberField.setPrefWidth(80.0);
        jakoAL3_numberField.setPrefWidth(80.0);
        lastAL2_numberField.setPrefWidth(80.0);
        lastAL3_numberField.setPrefWidth(80.0);



        pudot_grid.add(pudot_label, 0,0,2,1);
        jako_grid.add(jako_label, 0,0,2,1);
        last_grid.add(last_label, 0,0,2,1);

        pudot_grid.setId("gray_background");
        jako_grid.setId("gray_background");
        last_grid.setId("gray_background");

        pudot_grid.setPadding(new Insets(20.0, 30.0, 20.0, 50.0));
        jako_grid.setPadding(new Insets(20.0, 30.0, 20.0, 50.0));
        last_grid.setPadding(new Insets(20.0, 30.0, 20.0, 50.0));

        pudot_grid.setMinWidth(240.0);
        jako_grid.setMinWidth(240.0);
        last_grid.setMinWidth(240.0);


        pudot_grid.add(pudotAL2_label, 0,1);
        pudot_grid.add(pudotAL3_label, 0,2);

        jako_grid.add(jakoAL2_label, 0,1);
        jako_grid.add(jakoAL3_label, 0,2);

        last_grid.add(lastAL2_label, 0,1);
        last_grid.add(lastAL3_label, 0,2);


        pudot_grid.add(pudotAL2_numberField, 1,1);
        pudot_grid.add(pudotAL3_numberField, 1,2);

        jako_grid.add(jakoAL2_numberField, 1,1);
        jako_grid.add(jakoAL3_numberField, 1,2);

        last_grid.add(lastAL2_numberField, 1,1);
        last_grid.add(lastAL3_numberField, 1,2);






        table_grid.setPadding(new Insets(40,0,0,0));

        table_grid.add(pudot_grid, 0,0);
        table_grid.add(jako_grid, 0,1);
        table_grid.add(last_grid, 0,2);

        table_grid.setMaxSize(240, 1080);


        this.setContent(table_grid);


        table_grid.setId("test");



    }




}
