package org.kousam.main.gui.calc.scene;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import org.kousam.main.gui.RootUtil;
import org.kousam.main.gui.base._GridPane;
import org.kousam.main.gui.calc.CalcController;

public class TableBottomPane extends _GridPane {
    Label volumeSum_label;
    Label priceSum_label;

    Button add_button;
    Button delete_button;


    public TableBottomPane(RootUtil _root){
        super(_root);

        //this.setPadding(new Insets(4, 4, 4, 4));
        //this.setHgap(4.0);

        this.setId("tableBottom_pane");



        ColumnConstraints col_0 = new ColumnConstraints();
        ColumnConstraints col_1 = new ColumnConstraints();
        ColumnConstraints col_2 = new ColumnConstraints();
        ColumnConstraints col_3 = new ColumnConstraints();

        col_0.setPrefWidth(176);
        col_1.setPrefWidth(120);
        col_2.setPrefWidth(80);
        col_3.setPrefWidth(80);

        this.getColumnConstraints().add(col_0);
        this.getColumnConstraints().add(col_1);
        this.getColumnConstraints().add(col_2);
        this.getColumnConstraints().add(col_3);


        volumeSum_label = new Label("0 L");
        volumeSum_label.setMinWidth(176);
        volumeSum_label.setAlignment(Pos.BASELINE_RIGHT);
        volumeSum_label.setId("tableBottom_label");

        add_button = new Button("Lisää");
        add_button.setMinSize(80,36);
        add_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addButtonAction();

            }
        });

        delete_button = new Button("Poista");
        delete_button.setPrefSize(80,36);
        delete_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteButtonAction();

            }
        });


        this.add(volumeSum_label, 0,0);
        this.add(delete_button, 2,0);
        this.add(add_button, 3, 0);

        //this.add(priceSum_label, 1, 0);

    }

    public void setVolumeSum(String _volume){
        volumeSum_label.setText(_volume + " L");
    }

    public void setPriceSum(String _price){
        priceSum_label.setText(_price + " €");
    }


    public void addButtonAction(){
        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                try {
                    getGUI().newTableObj();
                }catch(Exception e){
                    System.out.println(e);
                }

                return null;
            }};
        task.run();
    }

    public void deleteButtonAction(){
        Task<Void> task = new Task<Void>() {
            @Override protected Void call() throws Exception {
                try {
                    CalcController controller = (CalcController) getController();
                    controller.deleteFocusedTableObj();
                }catch(Exception e){
                    System.out.println(e);
                }

                return null;
            }};
        task.run();
    }

}
