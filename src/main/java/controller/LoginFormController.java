package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFormController {

    public JFXTextField txtLEmail;
    public JFXPasswordField txtPassword;

    public void btnLoginAction(ActionEvent actionEvent) throws IOException {

        String key ="#345891AG";

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        String SQL ="SELECT * FROM users WHERE email="+"'"+txtLEmail.getText()+"'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(SQL);

            if(resultSet.next()){
               User user= new User(
                       resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                        );

               if(basicTextEncryptor.decrypt(user.getPassword()).equals(txtPassword.getText())){
                   System.out.println(user);
                   Stage stage = new Stage();
                   stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboardform.fxml"))));
                   stage.show();
               }else{
                   new Alert(Alert.AlertType.ERROR,"Invalid Password").show();
               }
            }else{
                new Alert(Alert.AlertType.ERROR,"User Not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void hyperRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/registerform.fxml"))));
        stage.show();
    }
}
