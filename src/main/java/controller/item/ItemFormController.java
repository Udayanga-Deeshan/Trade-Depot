package controller.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Item;

public class ItemFormController {
    @FXML
    private TableColumn colCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colStock;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableView tbCustomers;

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
    }
}
