package graphicInterface;

import DatabaseManagement.DatabaseInnovativeSolutions;
import beans.Orders;
import beans.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CustomerController extends InterfaceController{

    private static ObservableList<Product> productsTable = FXCollections.observableArrayList();
    private static ObservableList<Orders> ordersTable = FXCollections.observableArrayList();
    private static TableView<Product> productsTableView;
    private static TableView<Orders> ordersTableView;
    private static AnchorPane productsSection;
    private static AnchorPane ordersSection;
    private static TextField searchInput;
    private boolean currentSection;
    private String customerId;
    private ImageView undoButton;


    CustomerController( Scene app , String cId ){


        String[][] productFields = { { "Name" , "productName" } , { "Availability" , "productAvailability" } , {"Price" , "price"} , { "Description" , "productDescription" }};
        String[][] orderFields = { { "Product" , "product" } , { "Model" , "model" } , { "Purchase Date" , "purchaseDate" } , { "State" , "status" }};
        TableColumn column;

        customerId = cId;

        searchInput = (TextField)app.lookup( "#CUSTOMERSearch" );
        undoButton = (ImageView)app.lookup( "#CUSTOMERUndo" );

        ordersSection = (AnchorPane)app.lookup( "#CUSTOMEROrders" );
        productsSection = (AnchorPane)app.lookup( "#CUSTOMERProducts" );

        ordersTableView =  new TableView<>();
        productsTableView = new TableView<>();

        ordersTable = FXCollections.observableArrayList();
        productsTable = FXCollections.observableArrayList();

        ordersTableView.setMinWidth( 498 );
        productsTableView.setMinWidth( 498 );
        ordersTableView.setMinHeight( 233 );
        productsTableView.setMinHeight( 233 );

        ordersTableView.setItems( ordersTable );
        productsTableView.setItems( productsTable );

        currentSection = false;  //  set productTable for initial table showed

        for( int a = 0; a<orderFields.length; a++ ){

            column = new TableColumn( orderFields[a][0] );
            column.setCellValueFactory( new PropertyValueFactory<>( orderFields[a][1] ));
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            ordersTableView.getColumns().add( column );

        }

        for( int a = 0; a<productFields.length; a++ ){

            column = new TableColumn( productFields[a][0] );
            column.setCellValueFactory( new PropertyValueFactory<>( productFields[a][1] ));
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            productsTableView.getColumns().add( column );

        }

        ordersTable.addAll( DatabaseInnovativeSolutions.getOrderStatus( customerId ));
        productsTable.addAll(DatabaseInnovativeSolutions.getAvailableProducts());

        ((AnchorPane)app.lookup( "#CUSTOMEROrdersTable" )).getChildren().add( ordersTableView );
        ((AnchorPane)app.lookup( "#CUSTOMERProductsTable" )).getChildren().add( productsTableView );

    }

    void searchValue(){

        String value = searchInput.getText();

        if( currentSection ){

            productsTable.removeAll( productsTable );
            productsTable.addAll( DatabaseInnovativeSolutions.searchProducts( value ));
            undoButton.setVisible( true );

        }else{

            ordersTable.removeAll(ordersTable);
            ordersTable.addAll( DatabaseInnovativeSolutions.searchOrders( value , customerId ));
            undoButton.setVisible( true );

        }
    };

    void changeTable( String table ){

        if( table.compareTo( "Products") == 0 ){

            if( currentSection == false ) return;

            currentSection = false;
            ordersSection.setVisible( false );

            if( undoButton.isVisible()){
                ordersTable.removeAll( ordersTable );
                ordersTable.addAll( DatabaseInnovativeSolutions.getOrderStatus( customerId ));
            }

            productsSection.setVisible( true );

        }else{

            if( currentSection == true ) return;

            currentSection = true;
            productsSection.setVisible( false );

            if( undoButton.isVisible()){
                productsTable.removeAll( productsTable );
                productsTable.addAll( DatabaseInnovativeSolutions.getAvailableProducts());
            }

            ordersSection.setVisible( true );


        }
    }

    void undoSearch(){

        undoButton.setVisible( false );
        if( currentSection == true ) {
            ordersTable.removeAll(ordersTable);
            ordersTable.addAll(DatabaseInnovativeSolutions.getOrderStatus(customerId));
        }else{
            productsTable.removeAll(productsTable);
            productsTable.addAll(DatabaseInnovativeSolutions.getAvailableProducts());
        }

    }
}
