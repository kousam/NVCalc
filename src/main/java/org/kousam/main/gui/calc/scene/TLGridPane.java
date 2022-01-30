package org.kousam.main.gui.calc.scene;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base._GridPane;

public class TLGridPane extends _GridPane implements RootUtil {


    public TLGridPane(RootUtil _root){
        super(_root);


        this.setId("teal_background");

        //this.setHgap(4.0);
        this.setMinWidth(448);
        this.setMinHeight(40);


        this.setPadding(new Insets(0, 0, 0, 13));



        Label distance_label = new Label("km");
        Label volume_label = new Label("Litrat");
        Label fixedVolume_label = new Label("Lask. litrat");
        Label factor_label = new Label("Kerroin");
        Label price_label = new Label("Hinta");

        distance_label.setPrefWidth(80.0);
        volume_label.setPrefWidth(80.0);
        fixedVolume_label.setPrefWidth(80.0);
        factor_label.setPrefWidth(60.0);
        price_label.setPrefWidth(80.0);

        distance_label.setPrefHeight(40.0);
        volume_label.setPrefHeight(40.0);
        fixedVolume_label.setPrefHeight(40.0);
        factor_label.setPrefHeight(40.0);
        price_label.setPrefHeight(40.0);



        distance_label.setId("table_label");
        volume_label.setId("table_label");
        fixedVolume_label.setId("table_label");
        factor_label.setId("table_label");
        price_label.setId("table_label");




        distance_label.setAlignment(Pos.CENTER);
        volume_label.setAlignment(Pos.CENTER);
        fixedVolume_label.setAlignment(Pos.CENTER);
        factor_label.setAlignment(Pos.CENTER);
        price_label.setAlignment(Pos.CENTER);


        GridPane.setValignment(distance_label, VPos.CENTER);
        GridPane.setValignment(volume_label, VPos.CENTER);
        GridPane.setValignment(fixedVolume_label, VPos.CENTER);
        GridPane.setValignment(factor_label, VPos.CENTER);
        GridPane.setValignment(price_label, VPos.CENTER);







        this.add(distance_label, 0,0);
        this.add(new TLSeparator(), 1,0);
        this.add(volume_label,2,0);
        this.add(new TLSeparator(22.0), 3,0);
        this.add(fixedVolume_label,4,0);
        this.add(new TLSeparator(), 5,0);
        this.add(factor_label,6,0);
        this.add(new TLSeparator(), 7,0);
        this.add(price_label,8,0);
        this.add(new TLSeparator(true), 9,0);
        //this.add(add_button, 10, 0);




    }


    public void addButtonAction(){
        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                try {
                    getGUI().newTableObj();
                }catch(Exception e){
                    System.out.println(e);
                }

                return null;
            }};
        task.run();
    }

}
