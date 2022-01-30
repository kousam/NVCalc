package org.kousam.main.gui.base;

import javafx.scene.layout.Pane;
import org.kousam.main.App;
import org.kousam.main.gui.GUI;
import org.kousam.main.gui.RootUtil;

abstract public class SceneController implements RootUtil {
    GUI gui;
    ContentPane pane;

    public SceneController(GUI _gui){
        gui = _gui;
    }


    public void setPane(ContentPane _pane){
        pane = _pane;
    }

    public Pane getScene(){
        return pane;
    }

    public MessagePane setMessage(String message){
        MessagePane message_pane = new MessagePane(this);

        message_pane.setMessage(message);

        pane.addMessage(message_pane);

        return message_pane;
    }






    public void cancelMessage(MessagePane message_pane){
        pane.removeMessage(message_pane);
    }


    public GUI getGUI(){
        return gui;
    }
    public App getApp() {
        return gui.getApp();
    }
    public SceneController getController(){
        return this;
    }
    public RootUtil getRoot(){
        return null;
    }
}
