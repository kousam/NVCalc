package org.kousam.main.gui.calc.scene;

import org.kousam.main.App;
import org.kousam.main.gui.GUI;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base.NumberField;
import org.kousam.main.gui.base.SceneController;
import org.kousam.main.gui.calc.CalcController;

public class TableNumberField extends NumberField {
    RootUtil root;

    public TableNumberField(RootUtil _root){
        super();

        root = _root;

        this.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                CalcController controller = (CalcController) getController();
                TableObj tableObj = (TableObj) getRoot();

                controller.setFocusedTableObj(tableObj);
            }
        });

    }

    public GUI getGUI(){
        return root.getGUI();
    }

    public App getApp(){
        return root.getApp();
    }

    public SceneController getController(){
        return root.getController();
    }

    public RootUtil getRoot(){
        return root;
    }

}
