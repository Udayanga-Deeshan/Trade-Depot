package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormCOntroller {

    public AnchorPane loadFormContent;

    public void btnCustomerFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/customerform.fxml");

        assert resource !=null;

        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    public void btnItemFormOnAction(ActionEvent actionEvent) {
    }

    public void btnOrderFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/orderform.fxml");

        assert resource !=null;

        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    public void btnRegisterUserOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = this.getClass().getResource("/view/registerform.fxml");

        assert resource !=null;

        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }
}
