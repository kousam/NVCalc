package org.kousam.main.gui.base;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.kousam.main.gui.RootUtil;

public class SideBar extends _GridPane implements RootUtil {
    Button settings_button;
    Button home_button;

    int row_count;

    ImageView home_white_view;
    ImageView settings_white_view;

    Image home_white_img = new Image("images/home_white.png");
    Image settings_white_img = new Image("images/settings_white.png");

    public SideBar(RootUtil _root){
        super(_root);

        row_count = 0;

        this.setMinWidth(44.0);
        this.setMaxWidth(44.0);

        this.setId("darkGray_background");



        home_white_view = new ImageView(home_white_img);
        settings_white_view = new ImageView(settings_white_img);


        home_white_view.setFitHeight(28);
        home_white_view.setPreserveRatio(true);

        settings_white_view.setFitHeight(28);
        settings_white_view.setPreserveRatio(true);




        AnchorPane.setLeftAnchor(this, 0.0);
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);

    }

    private void addButton(Button button, boolean current){
        if(current){
            button.setId("sideBar_button_true");
        }else{
            button.setId("sideBar_button_false");
        }


        this.add(button, 0, row_count);

        row_count += 1;
    }

    public void addSettings(boolean current){
        settings_button = new Button();
        settings_button.setGraphic(settings_white_view);
        settings_button.setAlignment(Pos.CENTER);
        settings_button.setMinSize(40.0, 40.0);
        settings_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                settingsButtonAction();
            }
        });
        addButton(settings_button, current);
    }

    public void addHome(boolean current){
        home_button = new Button();
        home_button.setGraphic(home_white_view);
        home_button.setAlignment(Pos.CENTER);
        home_button.setMinSize(40.0, 40.0);
        home_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                homeButtonAction();
            }
        });


        addButton(home_button, current);
    }

    public void settingsButtonAction(){
        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                try {
                    getGUI().setScene("dragDrop");

                }catch(Exception e){
                    System.out.println("settingsButtonAction: " + e);
                }

                return null;
            }};
        task.run();
    }

    public void homeButtonAction(){
        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                try {
                    getGUI().setScene("calc");

                }catch(Exception e){
                    System.out.println(e);
                }

                return null;
            }};
        task.run();
    }

}
