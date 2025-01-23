package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    }

    @FXML
    void btnUpdateCustomerAction(ActionEvent event) {

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
