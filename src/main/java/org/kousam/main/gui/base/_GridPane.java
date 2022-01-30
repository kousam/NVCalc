package org.kousam.main.gui.base;

import javafx.scene.layout.GridPane;
import org.kousam.main.App;
import org.kousam.main.gui.GUI;
import org.kousam.main.gui.RootUtil;

abstract public class _GridPane extends GridPane implements RootUtil {
    RootUtil root;

    public _GridPane(RootUtil _root){
        root = _root;
    }

    public GUI getGUI(){
        return root.getGUI();
    }
    public App getApp() {
        return root.getApp();
    }
    public SceneController getController(){
        return root.getController();
    }
    public RootUtil getRoot(){
        return this;
    }
}
