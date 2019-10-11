package graphicInterface;

import beans.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AdminController extends InterfaceController{

    private static ObservableList<Employee> employeeTable = FXCollections.observableArrayList();
    private static TableView<Employee> employeeTableView;
    private static TextField searchInput;

    //  THE FUNCION LINKS THE TABLES OF "ADMIN INTERFACE" TO CONTROL APPLICATION
    AdminController( Scene app ){

        String[] fields = { "name" , "surname" , "email" };  //  FIELDS OF TABLE EMPLOYEE
        TableColumn column;

        searchInput = (TextField)app.lookup( "#ADMINSearch" );  //  TEXT INPUT FOR SEARCH INFORMATION

        employeeTableView =  new TableView<>();               //  TABLE EMPLOYEE
        employeeTable = FXCollections.observableArrayList();  //  COLLECTION OF BEANS-CLASS LINKED TO THE TABLE EMPLOYEE

        employeeTableView.setMinWidth( 498 );
        employeeTableView.setMinHeight( 233 );

        employeeTableView.setItems( employeeTable );

        for( int a = 0; a<fields.length; a++ ){

            column = new TableColumn( fields[a] );
            column.setCellValueFactory( new PropertyValueFactory<>( fields[a]) );
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            employeeTableView.getColumns().add( column );

        }
        // INSERTION OF THE TABLE IN THE ANCHORPANE
        ((AnchorPane)app.lookup( "#ADMINEmployeesTable" )).getChildren().add( employeeTableView );

        //  LOAD VALUES INTO TABLES

    }

    void searchValue(){

        String value = searchInput.getText();
        ObservableList<Employee> results;

        //  results = MYSQLConnector.searchEmployee( String value );
        results = FXCollections.observableArrayList();

        employeeTable.removeAll();
        employeeTable.addAll( results );

    }

    void undoSearch(){

        loadValues();

    }

    void loadValues(){

        ObservableList<Employee> results;

        //  results = MYSQLConnector.getEmployees() );
        results = FXCollections.observableArrayList();

        employeeTable.removeAll();
        employeeTable.addAll( results );

    }

    void showTable( String table ){}



}
