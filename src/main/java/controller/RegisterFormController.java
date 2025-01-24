package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.User;

import java.sql.*;

public class RegisterFormController {
    public JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

   


    public void btnRegisterOnAction(ActionEvent actionEvent) throws SQLException {
        String username = txtUserName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword =txtConfirmPassword.getText();

        if(password.equals(confirmPassword)){

            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE email =" + "'" + txtEmail.getText() + "'");
            if(!resultSet.next()){
                User user = new User(username, email, password);
                String SQL ="INSERT INTO users(username,email,password) VALUES(?,?,?)";
                PreparedStatement preparedStatement= connection.prepareStatement(SQL);
                preparedStatement.setString(1,user.getUserName());
                preparedStatement.setString(2,user.getEmail());
                preparedStatement.setString(3,user.getPassword());
                preparedStatement.executeUpdate();

                new Alert(Alert.AlertType.CONFIRMATION,"User added success").show();

                txtUserName.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
                txtConfirmPassword.setText("");

            }else {
                new Alert(Alert.AlertType.ERROR,"user already exists").show();
            }
            
        }else{
            new Alert(Alert.AlertType.ERROR,"password mismatch").show();
        }
    }


}
