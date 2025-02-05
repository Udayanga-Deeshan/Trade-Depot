package controller.order;

import controller.item.ItemController;
import db.DBConnection;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {

    boolean placeOrder(Order order) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String SQL = "INSERT INTO orders VALUES(?,?,?)";
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, order.getId());
            preparedStatement.setObject(2, order.getDate());
            preparedStatement.setObject(3, order.getCustomerID());

            Boolean isOrderAdd = preparedStatement.executeUpdate() > 0;
            if (isOrderAdd) {
                boolean isOrderDetailAdd = new OrderDetailController().addOrderDetail(order.getOrderDetailList());

                if (isOrderDetailAdd) {
                    boolean isUpdateStock = new ItemController().updateStock(order.getOrderDetailList());

                    if (isUpdateStock) {
                        connection.commit();
                        return true;
                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }

        connection.rollback();
        return false;
    }
}
