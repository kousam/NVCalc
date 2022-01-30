package org.kousam.main.gui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.kousam.main.App;
import org.kousam.main.gui.base.SceneController;
import org.kousam.main.gui.calc.CalcController;
import org.kousam.main.gui.calc.scene.AddedCostObj;
import org.kousam.main.gui.calc.scene.TableObj;
import org.kousam.main.gui.dragDrop.DragDropController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUI{
    App app;

    int row_count;

    Map<String, SceneController> controller_map;
    SceneController curr_controller;

    Stage primaryStage;
    Scene scene;


    public void build(Stage _primaryStage) throws Exception{
        row_count = 0;
        controller_map = new HashMap<String, SceneController>();

        primaryStage = _primaryStage;
        primaryStage.setMinWidth(956);
        primaryStage.setMinHeight(600);
        primaryStage.setTitle("NVCalc");
        primaryStage.setOnCloseRequest(e -> Platform.exit());

        setScene("calc");

        primaryStage.show();

    }

    public SceneController getCurrController(){
        return curr_controller;
    }

    public void setScene(String title){

        if(getSceneController(title) != curr_controller || curr_controller == null){
            Pane pane = getScene(title);

            if (scene == null) {
                scene = new Scene(pane, 944, 600);
                scene.getStylesheets().add("styles.css");
                primaryStage.setScene(scene);

            } else{
                scene.setRoot(pane);
            }
        }


    }

    public Pane getScene(String title){
        SceneController scene_controller;
        Pane pane;

        scene_controller = getSceneController(title);
        curr_controller = scene_controller;

        pane = scene_controller.getScene();


        return pane;
    }

    public SceneController getSceneController(String title){
        SceneController scene_controller;

        if(controller_map.containsKey(title)){
            scene_controller = controller_map.get(title);
        } else{
            scene_controller = loadSceneController(title);
        }

        return scene_controller;
    }

    private SceneController loadSceneController(String title){
        SceneController scene_controller;
        switch(title){
            case "calc":
                scene_controller = new CalcController(this);
                break;
            case "dragDrop":
                scene_controller = new DragDropController(this);
                break;
            case "setTable":
                scene_controller = new CalcController(this);
                break;
            default:
                scene_controller = null;
                break;
        }


        if (scene_controller != null){
            controller_map.put(title, scene_controller);
        }

        return scene_controller;
    }



    public void setApp(App _app){
        app = _app;
    }

    public App getApp(){
        return app;
    }


    public void newTableObj(){
        SceneController controller = getSceneController("calc");
        ((CalcController) controller).newTableObj();

    }



    public ArrayList<TableObj> getTableObjList(){
        SceneController controller = getSceneController("calc");

        return ((CalcController) controller).getTableObjList();

    }

    public Map<String, AddedCostObj> getACObjMap(){
        SceneController controller = getSceneController("calc");

        return ((CalcController) controller).getACObjMap();
    }

    public void setPriceSum(String price_sum){
        SceneController controller = getSceneController("calc");

        ((CalcController) controller).setPriceSum(price_sum);
    }





}
