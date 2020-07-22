package org.yinuo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class StartPane extends BorderPane {

    public StartPane() {
        ImageView imageView = new ImageView(new Image("/img/logo.gif"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(600);
        imageView.setFitWidth(400);
        setCenter(imageView);
    }
}
