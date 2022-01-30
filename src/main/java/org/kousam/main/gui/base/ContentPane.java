package org.kousam.main.gui.base;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import org.kousam.main.App;
import org.kousam.main.gui.GUI;
import org.kousam.main.gui.RootUtil;

abstract public class ContentPane extends _StackPane implements RootUtil {

    BorderPane border_pane;

    public ContentPane(RootUtil _root){
        super(_root);

        this.setId("content_pane");
        this.setPrefSize(1920,1080);

        border_pane = new BorderPane();
        this.setPrefSize(1920,1080);

        this.getChildren().add(border_pane);

    }

    public SideBar addSideBar(){
        SideBar sideBar = new SideBar(root);
        border_pane.setLeft(sideBar);

        return sideBar;
    }


    public void setContent(Node node){
        border_pane.setCenter(node);
    }

    public void addMessage(MessagePane message_pane){
        this.getChildren().add(message_pane);
    }

    public void removeMessage(MessagePane message_pane){
        this.getChildren().remove(message_pane);
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
        return root;
    }








}
