package graphicInterface;

import beans.Orders;
import beans.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class CustomerController extends InterfaceController{

    private static ObservableList<Product> productsTable = FXCollections.observableArrayList();
    private static ObservableList<Orders> ordersTable = FXCollections.observableArrayList();
    private static TableView<Product> productsTableView;
    private static TableView<Orders> ordersTableView;
    private static AnchorPane productsSection;
    private static AnchorPane ordersSection;
    private static TextField searchInput;
    boolean currentSection;


    CustomerController( Scene app ){

        String[] fields = { "name" , "date" , "state" , "price" };
        String[] fields2 = { "name" , "model" , "price" };
        TableColumn column;

        searchInput = (TextField)app.lookup( "#CUSTOMERSearch" );
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

        for( int a = 0; a<fields.length; a++ ){

            column = new TableColumn( fields[a] );
            column.setCellValueFactory( new PropertyValueFactory<>( fields[a]) );
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            ordersTableView.getColumns().add( column );

        }

        for( int a = 0; a<fields2.length; a++ ){

            column = new TableColumn( fields2[a] );
            column.setCellValueFactory( new PropertyValueFactory<>( fields2[a]) );
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            productsTableView.getColumns().add( column );

        }

        ((AnchorPane)app.lookup( "#CUSTOMEROrdersTable" )).getChildren().add( ordersTableView );
        ((AnchorPane)app.lookup( "#CUSTOMERProductsTable" )).getChildren().add( productsTableView );

    }

    void searchValue(){};

    void changeTable( String table ){

        if( table.compareTo( "Products") == 0 ){

            if( currentSection == false ) return;
            currentSection = false;
            ordersSection.setVisible( false );
            productsSection.setVisible( true );

        }else{

            if( currentSection == true ) return;
            currentSection = true;
            productsSection.setVisible( false );
            ordersSection.setVisible( true );


        }
    }

    void loadValues(){};

    void undoSearch(){};
}
