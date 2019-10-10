package graphicInterface;

import beans.Employee;
import beans.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class HeadDepartmentController {

    private static ObservableList<Employee> teamsTable = FXCollections.observableArrayList();
    private static ObservableList<Product> productsTable = FXCollections.observableArrayList();
    private static TableView<Employee> teamsTableView;
    private static TableView<Product> productsTableView;
    private static TextField searchInput;
    private static MenuButton tablesMenu;

    HeadDepartmentController( Scene app ){

        String[] fields = { "Team" , "#Members" , "Project" };
        String[] fields2 = { "name" , "model" , "price" };
        TableColumn column;

        searchInput = (TextField)app.lookup( "#DEP_HEADSearch" );
        tablesMenu = (MenuButton)app.lookup( "#DEP_HEADMenu" );

        teamsTableView =  new TableView<>();
        productsTableView = new TableView<>();

        teamsTable = FXCollections.observableArrayList();
        productsTable = FXCollections.observableArrayList();

        teamsTableView.setMinWidth( 498 );
        productsTableView.setMinWidth( 498 );
        teamsTableView.setMinHeight( 233 );
        productsTableView.setMinHeight( 233 );

        teamsTableView.setItems( teamsTable );
        productsTableView.setItems( productsTable );

        for( int a = 0; a<fields.length; a++ ){

            column = new TableColumn( fields[a] );
            column.setCellValueFactory( new PropertyValueFactory<>( fields[a]) );
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            teamsTableView.getColumns().add( column );

        }

        for( int a = 0; a<fields2.length; a++ ){

            column = new TableColumn( fields[a] );
            column.setCellValueFactory( new PropertyValueFactory<>( fields2[a]) );
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            productsTableView.getColumns().add( column );

        }

        ((AnchorPane)app.lookup( "#DEP_HEADTeamsTable" )).getChildren().add( teamsTableView );
        ((AnchorPane)app.lookup( "#DEP_HEADProductsTable" )).getChildren().add( productsTableView );

    }
}
