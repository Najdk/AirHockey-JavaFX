package com.github.airhockey.controllers;

import com.github.airhockey.config.RootConfig;
import com.github.airhockey.websocket.client.GameClientEndpoint;
import com.github.airhockey.websocket.server.GameServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.websocket.DeploymentException;
import java.net.URI;
import java.net.URISyntaxException;

public class GameController extends Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        GameServer gameServer = context.getBean(GameServer.class);

        try {
            // Launch server
            gameServer.initServer();
            gameServer.launchServer();

            // Client init
            GameClientEndpoint clientEndpoint = null;
            try {
                clientEndpoint = new GameClientEndpoint(new URI("ws://127.0.0.1:8080/air-hockey"));
            } catch (URISyntaxException ex) {
            }
            // Launch game window
            launch(args);
        } catch (DeploymentException ex) {
            Alert deployExAl = new Alert(Alert.AlertType.ERROR);
            deployExAl.showAndWait();
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../../../../layout.fxml"));
            Scene scene = new Scene(root, 400, 600);

            stage.setTitle("Air hockey");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "FXML file was not found");
            alert.showAndWait();
        }
    }

}