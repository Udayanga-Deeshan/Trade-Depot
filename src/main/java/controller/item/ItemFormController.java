package controller.item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.util.List;

public class ItemFormController {
    public TableView tbItems;
    @FXML
    private TableColumn colCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colStock;

    @FXML
    private TableColumn colUnitPrice;



    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtUnitPrice;

    public void btnItemAddOnAction(ActionEvent actionEvent) {

        Item item = new Item(
                txtCode.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtStock.getText())
        );

        boolean isItemAdd=new ItemController().addItem(item);
        if(isItemAdd){
            new Alert(Alert.AlertType.INFORMATION,"Item has been succesfully added").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Failed to add the item").show();
        }



    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
        if(txtCode.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"provide the code in order to search the item").show();
        }
        Item searchedItem =new ItemController().searchItem(txtCode.getText());
        txtDescription.setText(searchedItem.getDescription());
        txtUnitPrice.setText(String.valueOf(searchedItem.getUnitPrice()));
        txtStock.setText(String.valueOf(searchedItem.getStock()));



    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateItemOnAction(ActionEvent actionEvent) {
    }

    public void btnReloadItemsOnAction(ActionEvent actionEvent) {
        loadItemsTable();
    }

    private void loadItemsTable(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        List<Item>itemList =new ItemController().getALl();
        itemList.forEach(item->{
            itemObservableList.add(item);
        });
        tbItems.setItems(itemObservableList);
    }
}
