package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements  CustomerService{
    @Override
    public boolean addCustomer(Customer customer) {
        String SQL="INSERT INTO customer VALUES(?,?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,customer.getId());
            preparedStatement.setObject(2,customer.getName());
            preparedStatement.setObject(3,customer.getAddress());
            preparedStatement.setObject(4,customer.getSalary());
            return preparedStatement.executeUpdate() >0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String SQL="UPDATE customer SET name=? ,address=?, salary=? WHERE id= ?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setObject(1,customer.getName());
            preparedStatement.setObject(2,customer.getAddress());
            preparedStatement.setObject(3,customer.getSalary());
            preparedStatement.setObject(4,customer.getId());
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Customer searchCustomer(String id) {
        String SQL ="SELECT * FROM customer WHERE id="+"'"+id+"'";
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SQL);
            resultSet.next();

            return  new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Customer> getAll() {
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM customer");

            while (resultSet.next()){

                Customer customer=  new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                );

                customerArrayList.add(customer);

            }

            System.out.println(customerArrayList);




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customerArrayList;
    }

    public ObservableList<String> getCustomerIds(){
        ObservableList<String> customerIdList = FXCollections.observableArrayList();
        getAll().forEach(customer -> {
            customerIdList.add(customer.getId());
        });

        return customerIdList;
    }

    @Override
    public boolean deleteCustomer(String id) {

        try {
            Connection connection=DBConnection.getInstance().getConnection();
            Statement statement= connection.createStatement();
            return  statement.executeUpdate("DELETE FROM customer WHERE id='"+id+"'") >0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
