package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tbCustomers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    void btnAddCustomerAction(ActionEvent event) {

       Customer customer =new Customer(
              txtID.getText(),
              txtName.getText(),
              txtAddress.getText(),
              Double.parseDouble(txtSalary.getText())
        );

       boolean isCustomerAdd = new CustomerController().addCustomer(customer);
       if(isCustomerAdd){
           new Alert(Alert.AlertType.INFORMATION,"Customer Added Sucessfully").show();
       }else{
           new Alert(Alert.AlertType.ERROR,"Customer Not Added").show();
       }

    }

    @FXML
    void btnDeleteCustomerAction(ActionEvent event) {

    }

    @FXML
    void btnReloadCustomersAction(ActionEvent event) {
            loadTable();
    }

    @FXML
    void btnSearchCustomerAction(ActionEvent event) {

            if(txtID.getText().trim().isEmpty()){
                new Alert(Alert.AlertType.ERROR,"Please Provide customer id").show();
            }else{
                Customer searchedCustomer=new CustomerController().searchCustomer(txtID.getText());
                txtName.setText(searchedCustomer.getName());
                txtAddress.setText(searchedCustomer.getAddress());
                txtSalary.setText(String.valueOf(searchedCustomer.getSalary()));
            }





    }

    @FXML
    void btnUpdateCustomerAction(ActionEvent event) {


        boolean isUpdateCustomer= new CustomerController().updateCustomer(
                new Customer(
                        txtID.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        Double.parseDouble(txtSalary.getText())
                )
        );

        if(isUpdateCustomer){
            new Alert(Alert.AlertType.INFORMATION,"Customer Updated Succesfully").show();
            txtID.clear();
            txtName.clear();
            txtAddress.clear();
            txtSalary.clear();
        }else {
            new Alert(Alert.AlertType.ERROR,"operation failed").show();
        }


    }

    private void loadTable(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));


        ObservableList<Customer> customerObservableLists = FXCollections.observableArrayList();


        List<Customer> customerList= new CustomerController().getAll();
        customerList.forEach(customer ->{
            customerObservableLists.add(customer);
        });

        tbCustomers.setItems(customerObservableLists);
    }

}
