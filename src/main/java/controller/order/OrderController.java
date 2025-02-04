package controller.order;

import db.DBConnection;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {

    boolean placeOrder(Order order){

        String SQL = "INSERT INTO orders VALUES(?,?,?)";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
           preparedStatement.setObject(1,order.getId());
            preparedStatement.setObject(2,order.getDate());
            preparedStatement.setObject(3,order.getCustomerID());

            Boolean isOrderAdd = preparedStatement.executeUpdate() >0;
            if(isOrderAdd){
                boolean isOrderDetailAdd = new OrderDetailController().addOrderDetail(order.getOrderDetailList());
                if(isOrderDetailAdd){

                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
