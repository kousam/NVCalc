package org.kousam.main.gui.dragDrop.scene;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base.ContentPane;
import org.kousam.main.gui.base.SideBar;
import org.kousam.main.gui.dragDrop.DragDropController;

import java.io.File;

public class DragDropPane extends ContentPane implements RootUtil {

    BorderPane grid;

    public DragDropPane(RootUtil _root){
        super(_root);



        Label info_label = new Label("Pudota Excel-asiakirja ruutuun");
        info_label.setId("header_label");
        info_label.setAlignment(Pos.CENTER);
        info_label.setMinHeight(50);

        grid = new BorderPane();
        grid.setMaxSize(500,300);
        grid.setPadding(new Insets(100,0,200,0));


        GridPane.setHalignment(info_label, HPos.CENTER);





        grid.setCenter(info_label);





        SideBar sideBar = addSideBar();
        sideBar.addHome(false);
        sideBar.addSettings(true);

        this.setContent(grid);



        this.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                mouseDragOver(event);
            }
        });

        this.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                mouseDragDropped(event);
            }
        });

        this.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(final DragEvent event) {
                ;
            }
        });






    }




    private boolean isAccepted(final Dragboard db){
        boolean value;

        value = db.getFiles().get(0).getName().toLowerCase().endsWith(".xlsx");

        return value;

    }

    private void mouseDragDropped(final DragEvent e) {
        final Dragboard db = e.getDragboard();
        boolean success = false;
        this.setId("content_pane");

        if (db.hasFiles() && isAccepted(db)) {
            success = true;
            // Only get the first file from the list
            final File file = db.getFiles().get(0);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    String path = file.getAbsolutePath();
                    String name = file.getName();

                    try {
                        DragDropController controller = (DragDropController) getController();
                        controller.xlsxFileDropped(path, name);

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            });
        }

        e.setDropCompleted(success);
        e.consume();
    }

    private  void mouseDragOver(final DragEvent e) {
        final Dragboard db = e.getDragboard();



        if (db.hasFiles()) {
            if (isAccepted(db)) {
                this.setId("dragDrop_pane_true");
                e.acceptTransferModes(TransferMode.COPY);
            }else{
                this.setId("dragDrop_pane_false");
                e.acceptTransferModes(TransferMode.COPY);
            }
        } else {

            e.consume();
        }
    }

}
