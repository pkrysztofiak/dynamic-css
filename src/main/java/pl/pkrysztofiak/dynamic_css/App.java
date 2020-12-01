package pl.pkrysztofiak.dynamic_css;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.PseudoClass;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        SimpleStringProperty activeColorProperty = new SimpleStringProperty("");
        SimpleStringProperty inactiveColorProperty = new SimpleStringProperty("");
        
        PseudoClass activePseudoClass = PseudoClass.getPseudoClass("active");
        
        Text text = new Text("Text");
        text.getStyleClass().add("annotation");
        
        StackPane panelPane = new StackPane(text);
        panelPane.getStyleClass().add("panel");

        Button activeButton = new Button("Active");
        Button inactiveButton = new Button("Inactive");
        Button activeToGreenButton = new Button("Active to green");
        Button inactiveToYellowButton = new Button("Inactive to yellow");
        HBox hBox = new HBox(8, activeButton, inactiveButton, activeToGreenButton, inactiveToYellowButton);
        
        VBox vBox = new VBox(panelPane, hBox);
        VBox.setVgrow(panelPane, Priority.ALWAYS);
        
        Scene scene = new Scene(vBox, 400, 400);
        String css =  getClass().getClassLoader().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        activeButton.setOnAction(event -> {
            panelPane.pseudoClassStateChanged(activePseudoClass, true);
        });
        
        inactiveButton.setOnAction(event -> {
            panelPane.pseudoClassStateChanged(activePseudoClass, false);
        });
        
        activeToGreenButton.setOnAction(event -> {
            activeColorProperty.set("-active-color: green;");
        });
        
        inactiveToYellowButton.setOnAction(event -> {
            inactiveColorProperty.set("-inactive-color: yellow;");
        });
        
        panelPane.styleProperty().bind(Bindings.concat(activeColorProperty, inactiveColorProperty));
    }
}