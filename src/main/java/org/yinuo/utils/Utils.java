package org.yinuo.utils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    private static Double xOffSet = 0.0;
    private static Double yOffSet = 0.0;

    @Getter
    @Setter
    private static Map<String, String> config = new HashMap<>();

    public static void Drag(final Node node) {
        node.setOnMousePressed(event -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });

        node.setOnMouseDragged(event -> {
            Stage stage = getWindows(node);
            stage.setX(event.getScreenX() - xOffSet);
            stage.setY(event.getScreenY() - yOffSet);
        });
    }

    public static Stage getWindows(Node node) {
        return (Stage)node.getScene().getWindow();
    }

    public static Parent loadFxml(String fxml) {
        try {
            return FXMLLoader.load(ClassLoader.getSystemResource(fxml));
        } catch (IOException e) {
            return null;
        }
    }

    public static void forward(Stage windows,String fxml,int width,int height){
        Parent parent = Utils.loadFxml(fxml);
        if(parent != null){
            Platform.runLater(()->{
                windows.setScene(new Scene(parent, width, height));
                windows.centerOnScreen();
            });
        }
    }

    public static void disableEventTarget(ActionEvent event, boolean disable){
        Node target = (Node) event.getTarget();
        Platform.runLater(()->target.setDisable(disable));
    }

    public static void dialog(Stage owner,String fxml) {
        Parent parent = Utils.loadFxml(fxml);
        Platform.runLater(()->{
            Stage windows = new Stage(StageStyle.UTILITY);
            windows.initOwner(owner);
            windows.initModality(Modality.WINDOW_MODAL);
            windows.setScene(new Scene(parent));
            windows.showAndWait();
        });
    }

    public static void showError(Stage stage,Throwable throwable) {
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setHeaderText(throwable.getMessage());
            alert.showAndWait();
        });
    }

    public static void showInfo(Stage stage,String message) {
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setHeaderText(message);
            alert.showAndWait();
        });
    }
}
