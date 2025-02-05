package controller.item;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements  ItemService{
    @Override
    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public Item searchItem(String code) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet= connection.createStatement().executeQuery("SELECT * FROM item WHERE code="+"'"+code+"'");
            resultSet.next();
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> getALl()  {
        List<Item> itemsList = new ArrayList<>();
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            ResultSet resultSet= connection.createStatement().executeQuery("SELECT * FROM item");
            while (resultSet.next()){
                itemsList.add(new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                ));


            }

            return  itemsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<String> getItemCodes(){
        ObservableList<String> itemCodeList = FXCollections.observableArrayList();
        List<Item> itemList = getALl();
        itemList .forEach(item ->{
            itemCodeList.add(item.getCode());
        });
        return itemCodeList;
    }

    public  boolean updateStock(List<OrderDetail>orderDetails){
        for(OrderDetail orderDetail:orderDetails){
            boolean isUpdateStock = updateStock(orderDetail);
            if(!isUpdateStock){
                return false;
            }
        }

        return  true;
    }

    public boolean updateStock(OrderDetail orderDetail){
        String SQL="UPDATE item SET qtyOnHand = qtyOnHand -? WHERE code=? ";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,orderDetail.getQty());
            preparedStatement.setObject(2,orderDetail.getItemCode());
            return  preparedStatement.executeUpdate() >0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
