package ru.kpfu.itis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.exit;

@Component
public class ModalController implements Initializable {

    @Autowired
    ConfigurableApplicationContext springContext;

    @FXML
    private Label TitleLabel;

    @FXML
    private Button actionButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //нажали на единственную кнопочку на модальном окне
        actionButton.setOnAction(event -> {
            actionButton.getScene().getWindow().hide();
        });
    }
}
