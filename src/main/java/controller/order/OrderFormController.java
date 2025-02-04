package controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.customer.CustomerController;
import controller.item.ItemController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public JFXTextField txtOrderId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDiscription.setCellValueFactory(new  PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        setDateAndTime();
        loadCustomerIds();
        loadItemCodes();

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue!= null){
                System.out.println(newValue);
                searchCustomerData(newValue.toString());
            }

        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue !=null){
                searchItemData(newValue.toString());
            }
        });
    }

    private void loadItemCodes() {
        cmbItemCode.setItems(new ItemController().getItemCodes());
    }

    private void searchItemData(String code) {
        Item item = new ItemController().searchItem(code);

        txtDiscription.setText(item.getDescription());
        txtStock.setText(item.getStock().toString());
        txtUnitPrice.setText(item.getUnitPrice().toString());

    }

    private void setDateAndTime(){
        Date date =new Date();
        System.out.println(date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

    private void loadCustomerIds(){
        cmbCustomerId.setItems(new CustomerController().getCustomerIds());
    }

    private  void searchCustomerData(String customerID){
            Customer customer=new CustomerController().searchCustomer(customerID);
        System.out.println(customer);
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
    }



    ObservableList<CartTM> cartItems = FXCollections.observableArrayList();
    public void btnAddToCartAction(ActionEvent actionEvent) {
        String itemCode = cmbItemCode.getValue().toString();
        String description = txtDiscription.getText();
        Integer qtyOnHand = Integer.parseInt(txtQty.getText());
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        Double total = unitPrice * qtyOnHand;


        cartItems.add(new CartTM(itemCode,description,qtyOnHand,unitPrice,total));

        tbCart.setItems(cartItems);
        calcNetTotal();

        ;


    }

    private void calcNetTotal(){
        Double netTotal =0.0;
        for (CartTM cartTM: cartItems){
            netTotal+=cartTM.getTotalPrice();
        }
        lblNetTotal.setText(netTotal.toString());
    }

    public void btnPlaceOrderAction(ActionEvent actionEvent) {

        String orderId =txtOrderId.getText();
            String date=lblDate.getText();
            String customerId= cmbCustomerId.getValue().toString();

        List<OrderDetail> orderDetails = new ArrayList<>();
           cartItems.forEach(cartTM ->{
              orderDetails.add(
                      new OrderDetail(
                              orderId,
                              cartTM.getItemCode(),
                              cartTM.getQtyOnHand(),
                              cartTM.getUnitPrice()
                      )
              ) ;
           });

        Order order = new Order(orderId, date, customerId, orderDetails);

        boolean isOrderPlaced = new OrderController().placeOrder(order);


        if(isOrderPlaced){

            new Alert(Alert.AlertType.INFORMATION,"Order Placed").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Order not Placed").show();
        }


    }


}
