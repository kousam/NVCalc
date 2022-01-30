package org.kousam.main.gui.base;


import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.kousam.main.gui.RootUtil;

public class MessagePane extends _BorderPane{
    BorderPane inner_pane;
    GridPane label_pane;

    Button ok_button;

    Label message_label;
    Label info_label;

    Double width = 420.0;
    Double height = 305.0;
    Double label_pane_height = height - 40;

    public MessagePane(RootUtil _root){
        super(_root);

        this.setId("message_pane");
        this.setPrefSize(1920, 1080);

        inner_pane = new BorderPane();
        inner_pane.setId("message_grid");
        inner_pane.setMaxSize(width, height);

        label_pane = new GridPane();
        label_pane.setMinSize(width, label_pane_height);
        label_pane.setPadding(new Insets(20,0,40,0));
        label_pane.setVgap(20);

        ok_button = new Button("OK");
        ok_button.setPrefSize(120,40);
        ok_button.setId("calc_button");
        setAlignment(ok_button, Pos.CENTER);
        ok_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                okButtonAction();
            }
        });

        inner_pane.setCenter(label_pane);

        this.setCenter(inner_pane);

        AnchorPane.setLeftAnchor(this, 0.0);
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);

    }

    public void setMessage(String text){
        if(message_label == null){
            message_label = new Label();
            message_label.setId("message_label");
            message_label.setAlignment(Pos.CENTER);
            message_label.setPrefWidth(width);
            message_label.setPrefHeight(label_pane_height);
            //GridPane.setValignment(message_label, VPos.CENTER);

            label_pane.add(message_label, 0, 0);
        }
        message_label.setText(text);
    }

    public void setInfo(String text){

        if(info_label == null){
            info_label = new Label();
            info_label.setId("info_label");
            info_label.setAlignment(Pos.CENTER);
            info_label.setPrefWidth(width);
            info_label.setMinHeight(60);
            GridPane.setValignment(info_label, VPos.CENTER);

            label_pane.add(info_label, 0, 1);
        }
        info_label.setText(text);
    }

    public void setTextColor(String color_hex){
        message_label.setStyle("-fx-text-fill: " + color_hex);
    }

    public void addCancelButton(){
        inner_pane.setBottom(ok_button);
    }



    public void okButtonAction(){
        MessagePane pane = this;

        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                try {
                    pane.cancel();
                }catch(Exception e){
                    System.out.println(e);
                }
                return null;
            }};
        task.run();
    }

    public void cancel(){
        SceneController controller = getController();
        controller.cancelMessage(this);
    }

    public Button getOkButton(){
        return ok_button;
    }

}
