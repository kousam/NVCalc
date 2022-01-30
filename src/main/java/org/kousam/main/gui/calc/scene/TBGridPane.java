package org.kousam.main.gui.calc.scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.kousam.main.App;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base._GridPane;

public class TBGridPane extends _GridPane implements RootUtil {
    Button nuppi_button;
    Button perav_button;

    public TBGridPane(RootUtil _root){
        super(_root);

        this.setHgap(20.0);

        this.setMaxWidth(320);
        this.setMinHeight(80);



        this.setId("inner_obj");

        this.setPadding(new Insets(20,30,20,30));


        perav_button = new Button("Perävaunu");
        perav_button.setMinSize(120, 40);
        perav_button.setId("nuppiperav_button_false");
        perav_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                peravButtonAction();

            }
        });






        nuppi_button = new Button("Nuppi");
        nuppi_button.setMinSize(120, 40);
        nuppi_button.setId("nuppiperav_button_true");
        nuppi_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 nuppiButtonAction();

            }
        });


        this.add(nuppi_button, 0,0);
        this.add(perav_button, 1, 0);

        GridPane.setHalignment(this, HPos.CENTER);

    }


    private void nuppiButtonAction(){
        App app = getGUI().getApp();
        app.setDriveClass("AL2");
        nuppi_button.setId("nuppiperav_button_true");
        perav_button.setId("nuppiperav_button_false");

    }

    private void peravButtonAction(){
        App app = getGUI().getApp();
        app.setDriveClass("AL3");

        perav_button.setId("nuppiperav_button_true");
        nuppi_button.setId("nuppiperav_button_false");

    }
}
