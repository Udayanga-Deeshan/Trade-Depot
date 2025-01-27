package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    public JFXComboBox cmbCustomerId;
    public JFXComboBox cmbItemCode;
    public TextField txtUnitPrice;
    public JFXTextField txtQty;
    public Label lblDate;
    public Label lblTime;
    public Label lblNetTotal;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtDiscription;
    public TextField txtStock;
    public TableView tbCart;
    public TableColumn colItemCode;
    public TableColumn colDiscription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDateAndTime();
    }

    private void setDateAndTime(){
        Date date =new Date();
        System.out.println(date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(date);
        System.out.println(formattedDate);

        lblDate.setText(formattedDate);

        //----------- set time --------------

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    LocalTime localTime = LocalTime.now();
                    lblTime.setText(localTime.getHour()+":"+localTime.getMinute()+":"+localTime.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void btnAddToCartAction(ActionEvent actionEvent) {

    }

    public void btnPlaceOrderAction(ActionEvent actionEvent) {
    }


}
