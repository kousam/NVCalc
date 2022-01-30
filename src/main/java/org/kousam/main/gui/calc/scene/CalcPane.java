package org.kousam.main.gui.calc.scene;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.kousam.main.gui.base.ContentPane;
import org.kousam.main.gui.base.SceneController;
import org.kousam.main.gui.base.SideBar;

public class CalcPane extends ContentPane {


    GridPane centered_grid;
    _TablePane table_pane;
    RightContentPane rightCont_pane;


    Label total_number_label;


    public CalcPane(SceneController _controller){
        super(_controller);

        //this.setId("white_background");

        // build table pane
        table_pane = new _TablePane(this);
        rightCont_pane = new RightContentPane(this);


        // build centered grid
        // this contains all of the above
        centered_grid = new GridPane();
        centered_grid.setMaxWidth(700);
        centered_grid.setHgap(50);
        centered_grid.setPadding(new Insets(0,40,0,0));




        //_ScrollPane scroll_pane = new _ScrollPane(table_pane);


        centered_grid.add(table_pane, 0,0);

        centered_grid.add(rightCont_pane, 1,0);


        setContent(centered_grid);

        SideBar sideBar = addSideBar();
        sideBar.addHome(true);
        sideBar.addSettings(false);




    }


    public _TablePane getTablePane(){
        return table_pane;
    }
    public RightContentPane getRightContPane(){
        return rightCont_pane;
    }






}
