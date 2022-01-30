package org.kousam.main.gui.dragDrop;

import org.kousam.main.gui.GUI;
import org.kousam.main.gui.base.SceneController;
import org.kousam.main.gui.dragDrop.scene.DragDropPane;

public class DragDropController extends SceneController {
    DragDropPane setTable_pane;
    String xlsx_path;


    public DragDropController(GUI _gui){
        super(_gui);



        setTable_pane = new DragDropPane(this);
        setPane(setTable_pane);
    }




    public void saveXLSX(String path, String name){
        getApp().loadFromXLSL(path, name);
    }


    public void xlsxFileDropped(String path, String name){
        saveXLSX(path, name);
    }


}
