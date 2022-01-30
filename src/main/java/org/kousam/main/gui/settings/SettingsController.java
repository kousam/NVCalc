package org.kousam.main.gui.settings;

import org.kousam.main.gui.GUI;
import org.kousam.main.gui.base.SceneController;
import org.kousam.main.gui.dragDrop.scene.SetTablePane;

public class SettingsController extends SceneController {
    public SettingsController(GUI _gui){
        super(_gui);

        SetTablePane pane = new SetTablePane(this);
        setPane(pane);
    }
}
