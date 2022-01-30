package org.kousam.main.gui.calc.scene;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base.NumberField;
import org.kousam.main.gui.base._GridPane;

public class _TablePane extends _GridPane {
    GridPane table_grid;

    GridPane distance_grid;
    GridPane volume_grid;
    GridPane calc_grid;
    GridPane price_grid;
    GridPane factor_grid;
    GridPane util_grid;

    GridPane separator_grid;

    TableBottomPane tableBottom_pane;



    int row_count;

    public _TablePane(RootUtil _root){
        super(_root);

        this.setId("table_pane");

        table_grid = new GridPane();

        ColumnConstraints dist_constraint = new ColumnConstraints();
        ColumnConstraints volume_constraint = new ColumnConstraints();
        ColumnConstraints calc_constraint = new ColumnConstraints();
        ColumnConstraints separator_constraint = new ColumnConstraints();
        ColumnConstraints factor_constraint = new ColumnConstraints();
        ColumnConstraints price_constraint = new ColumnConstraints();
        //ColumnConstraints util_constraint = new ColumnConstraints();



        dist_constraint.setPrefWidth(80);
        volume_constraint.setPrefWidth(80);
        separator_constraint.setPrefWidth(4);
        calc_constraint.setPrefWidth(80);
        factor_constraint.setPrefWidth(60);
        price_constraint.setPrefWidth(80);


        table_grid.getColumnConstraints().add(dist_constraint);
        table_grid.getColumnConstraints().add(volume_constraint);
        table_grid.getColumnConstraints().add(separator_constraint);
        table_grid.getColumnConstraints().add(calc_constraint);
        table_grid.getColumnConstraints().add(factor_constraint);
        table_grid.getColumnConstraints().add(price_constraint);


        distance_grid = new GridPane();
        volume_grid = new GridPane();
        calc_grid = new GridPane();
        factor_grid = new GridPane();
        price_grid = new GridPane();
        //util_grid = new GridPane();


        distance_grid.setVgap(4.0);
        volume_grid.setVgap(4.0);


        calc_grid.setVgap(4.0);
        factor_grid.setVgap(4.0);
        price_grid.setVgap(4.0);
        //util_grid.setVgap(4.0);

        separator_grid = new GridPane();



        row_count = 0;

        table_grid.setHgap(8.0);
        table_grid.setPadding(new Insets(8, 0, 8, 12));

        table_grid.add(distance_grid, 0, 0);
        table_grid.add(volume_grid, 1,0);

        table_grid.add(separator_grid, 2,0);

        table_grid.add(calc_grid, 3, 0);
        table_grid.add(factor_grid, 4, 0);
        table_grid.add(price_grid, 5, 0);
        //table_grid.add(util_grid, 6, 0);







        ScrollPane scroll_pane = new ScrollPane();
        scroll_pane.setContent(table_grid);
        scroll_pane.setMinWidth(454);//(486);
        scroll_pane.setPrefHeight(1080.0);
        scroll_pane.setId("scroll_pane");







        TLGridPane tableLabel_grid = new TLGridPane(this);

        tableBottom_pane = new TableBottomPane(this);

        this.add(tableLabel_grid, 0,0);
        this.add(scroll_pane, 0,1);
        this.add(tableBottom_pane, 0,2);

    }



    public void addTableObj(TableObj table_obj){
        NumberField distance_numberField = table_obj.getDistanceNumberField();
        NumberField volume_numberField = table_obj.getVolumeNumberField();
        NumberField fixedVolume_numberField = table_obj.getCalcVolumeNumberField();
        NumberField factor_numberField = table_obj.getFactorNumberField();
        NumberField price_numberField = table_obj.getPriceNumberField();

        Button delete_button = table_obj.getDeleteButton();

        RowConstraints dist_constraint = new RowConstraints();
        RowConstraints volume_constraint = new RowConstraints();
        RowConstraints calc_constraint = new RowConstraints();
        RowConstraints factor_constraint = new RowConstraints();
        RowConstraints price_constraint = new RowConstraints();
        //RowConstraints util_constraint = new RowConstraints();

        int cell_height = 28;

        dist_constraint.setMaxHeight(cell_height);
        dist_constraint.setMinHeight(cell_height);
        volume_constraint.setMaxHeight(cell_height);
        volume_constraint.setMinHeight(cell_height);
        calc_constraint.setMaxHeight(cell_height);
        calc_constraint.setMinHeight(cell_height);
        factor_constraint.setMaxHeight(cell_height);
        factor_constraint.setMinHeight(cell_height);
        price_constraint.setMaxHeight(cell_height);
        price_constraint.setMinHeight(cell_height);
        //util_constraint.setMaxHeight(cell_height);
        //util_constraint.setMinHeight(cell_height);


        distance_grid.getRowConstraints().add(dist_constraint);
        volume_grid.getRowConstraints().add(volume_constraint);
        calc_grid.getRowConstraints().add(calc_constraint);
        factor_grid.getRowConstraints().add(factor_constraint);
        price_grid.getRowConstraints().add(price_constraint);
        //util_grid.getRowConstraints().add(util_constraint);



        distance_grid.add(distance_numberField, 0, row_count);
        volume_grid.add(volume_numberField, 1, row_count);
        calc_grid.add(fixedVolume_numberField, 0, row_count);
        factor_grid.add(factor_numberField, 0, row_count);
        price_grid.add(price_numberField, 0, row_count);
        //util_grid.add(delete_button, 0,row_count);


        addSeparator();
        row_count += 1;

    }

    public void removeTableObj(TableObj table_obj){
        distance_grid.getChildren().remove(table_obj.getDistanceNumberField());
        volume_grid.getChildren().remove(table_obj.getVolumeNumberField());
        calc_grid.getChildren().remove(table_obj.getCalcVolumeNumberField());
        factor_grid.getChildren().remove(table_obj.getFactorNumberField());
        price_grid.getChildren().remove(table_obj.getPriceNumberField());
        //util_grid.getChildren().remove(table_obj.getDeleteButton());
    }


    public void setRowCount(int value){
        row_count = value;
    }

    public TableBottomPane getTableBottomPane(){
        return tableBottom_pane;
    }

    public void clearSeparators(){
        separator_grid.getChildren().clear();
        separator_grid.getRowConstraints().clear();
    }

    public void addSeparator(){
        Separator separator = new Separator();
        separator.setMinHeight(32.0);
        separator.setMaxHeight(32.0);
        separator.setId("table_separator");
        separator.setMinWidth(4.0);
        separator.setOrientation(Orientation.VERTICAL);
        GridPane.setValignment(separator, VPos.CENTER);

        RowConstraints constraint = new RowConstraints();
        constraint.setMaxHeight(32);
        constraint.setMinHeight(32);
        separator_grid.getRowConstraints().add(constraint);

        separator_grid.add(separator, 0, row_count);

    }

    public void clear(){
        distance_grid.getChildren().clear();
        volume_grid.getChildren().clear();
        calc_grid.getChildren().clear();
        factor_grid.getChildren().clear();
        price_grid.getChildren().clear();
        //util_grid.getChildren().clear();

        distance_grid.getRowConstraints().clear();
        volume_grid.getRowConstraints().clear();
        calc_grid.getRowConstraints().clear();
        factor_grid.getRowConstraints().clear();
        price_grid.getRowConstraints().clear();
        //util_grid.getRowConstraints().clear();
    }



}
