package org.kousam.main.gui;

import org.kousam.main.App;
import org.kousam.main.gui.base.SceneController;

public interface RootUtil {

    public GUI getGUI();

    public App getApp();

    public SceneController getController();

    public RootUtil getRoot();

}
