package org.kousam.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.kousam.main.gui.GUI;

public class MyFXApp extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{

        GUI gui = new GUI();

        gui.build(primaryStage);

        App app = new App(gui);

    }


    public static void main(String[] args){
        launch(args);
    }
}


