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

    private static ObservableList<Employee> employeeTable = FXCollections.observableArrayList();
    private static ObservableList<Product> productTable = FXCollections.observableArrayList();
    private static TableView<Employee> employeeTableView;
    private static TableView<Product> productTableView;
    private static TextField searchInput;
    private static MenuButton tablesMenu;

    HeadDepartmentController( Scene app ){

        String[] fields = { "nome" , "cognome" , "email" };
        String[] fields2 = { "name" , "model" , "price" };
        TableColumn column;

        searchInput = (TextField)app.lookup( "#DEP_HEADSearch" );
        tablesMenu = (MenuButton)app.lookup( "#DEP_HEADMenu" );

        employeeTableView =  new TableView<>();
        productTableView = new TableView<>();

        employeeTable = FXCollections.observableArrayList();
        productTable = FXCollections.observableArrayList();

        employeeTableView.setMinWidth( 498 );
        productTableView.setMinWidth( 498 );
        employeeTableView.setMinHeight( 233 );
        productTableView.setMinHeight( 233 );

        employeeTableView.setItems( employeeTable );
        productTableView.setItems( productTable );

        for( int a = 0; a<fields.length; a++ ){

            column = new TableColumn( fields[a] );
            column.setCellValueFactory( new PropertyValueFactory<>( fields[a]) );
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            employeeTableView.getColumns().add( column );

        }

        for( int a = 0; a<fields2.length; a++ ){

            column = new TableColumn( fields[a] );
            column.setCellValueFactory( new PropertyValueFactory<>( fields2[a]) );
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            productTableView.getColumns().add( column );

        }

        ((AnchorPane)app.lookup( "#DEP_HEADEmployeesTable" )).getChildren().add( employeeTableView );
        ((AnchorPane)app.lookup( "#DEP_HEADProductsTable" )).getChildren().add( productTableView );

    }
}
