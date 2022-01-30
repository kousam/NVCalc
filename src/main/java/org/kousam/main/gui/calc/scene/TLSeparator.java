package org.kousam.main.gui.calc.scene;

import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;

public class TLSeparator extends Separator {

    public TLSeparator(){
        this.setMaxHeight(30);
        this.setId("tl_separator");
        this.setMinWidth(8.0);
        this.setOrientation(Orientation.VERTICAL);
        GridPane.setValignment(this, VPos.CENTER);
    }

    public TLSeparator(Double width){
        this.setMaxHeight(30);
        this.setId("tl_separator");
        this.setMinWidth(width);
        this.setOrientation(Orientation.VERTICAL);
        GridPane.setValignment(this, VPos.CENTER);
    }

    public TLSeparator(boolean transparent){
        this.setMaxHeight(30);

        if (transparent){
            this.setId("tl_separator_transparent");
        }else{
            this.setId("tl_separator");
        }

        this.setMinWidth(8.0);
        this.setOrientation(Orientation.VERTICAL);
        GridPane.setValignment(this, VPos.CENTER);
    }
}
