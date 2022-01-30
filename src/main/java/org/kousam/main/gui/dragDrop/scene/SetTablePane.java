package org.kousam.main.gui.dragDrop.scene;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base.ContentPane;
import org.kousam.main.gui.base.SideBar;

public class SetTablePane extends ContentPane implements RootUtil {
    RootUtil root;
    Button save_button;
    GridPane grid;

    public SetTablePane(RootUtil _root){
        super(_root);

        Label info_label = new Label("Pudota Excel-asiakirja ruutuun");
        info_label.setId("header_label");
        info_label.setAlignment(Pos.CENTER);
        info_label.setMinHeight(50);

        grid = new GridPane();
        grid.setId("inner_obj");
        grid.setMaxSize(400,510);
        grid.setPadding(new Insets(40,40,60,40));
        grid.setVgap(20);



        DragDropPane dragDrop_pane = new DragDropPane(this);

        GridPane.setHalignment(info_label, HPos.CENTER);
        GridPane.setHalignment(dragDrop_pane, HPos.CENTER);





        grid.add(info_label,0,0);
        grid.add(dragDrop_pane,0,1);



        SideBar sideBar = addSideBar();
        sideBar.addHome(false);
        sideBar.addSettings(true);

        this.setContent(grid);


    }



}
