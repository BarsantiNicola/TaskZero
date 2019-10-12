package graphicInterface;

import DatabaseManagement.DatabaseInnovativeSolutions;
import beans.Component;
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

public class HeadDepartmentController extends InterfaceController{

    private static ObservableList<Component> componentsTable = FXCollections.observableArrayList();
    private static ObservableList<Employee> employeesTable = FXCollections.observableArrayList();
    private static TableView<Component> componentsTableView;
    private static TableView<Employee> employeesTableView;
    private static AnchorPane componentsSection;
    private static AnchorPane employeesSection;
    private static TextField searchInput;
    private static MenuButton tablesMenu;
    private static boolean currentSection;
    private static String[] sections;
    private static int managedTeam;

    HeadDepartmentController( Scene app , int team ){

        //  associations from the field of the table and the identificator of the bean class variable related
        String[][] componentFields = { { "Name" , "componentName"} , { "Availability" , "componentAvailability" } , { "Description" , "componentDescription"} };
        String[][] employeeFields = { { "ID" , "IDEmployee" } , { "Salary" , "salary" } , {"Role" , "role"} };
        TableColumn column;

        managedTeam = team;

        searchInput = (TextField)app.lookup( "#DEP_HEADSearch" );
        tablesMenu = (MenuButton)app.lookup( "#DEP_HEADMenu" );
        componentsSection = (AnchorPane)app.lookup( "#DEP_HEADComponents" );
        employeesSection = (AnchorPane)app.lookup( "#DEP_HEADEmployees" );

        componentsTableView =  new TableView<>();
        employeesTableView = new TableView<>();

        componentsTable = FXCollections.observableArrayList();
        employeesTable = FXCollections.observableArrayList();


        componentsTableView.setMinWidth( 498 );
        employeesTableView.setMinWidth( 498 );
        componentsTableView.setMinHeight( 233 );
        employeesTableView.setMinHeight( 233 );

        componentsTableView.setItems( componentsTable );
        employeesTableView.setItems( employeesTable );

        currentSection = false; //  set the table to  team products table

        for( int a = 0; a<componentFields.length; a++ ){

            column = new TableColumn( componentFields[a][0] );
            column.setCellValueFactory( new PropertyValueFactory<>( componentFields[a][1] ));
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            componentsTableView.getColumns().add( column );

        }

        for( int a = 0; a<employeeFields.length; a++ ){

            column = new TableColumn( employeeFields[a][0] );
            column.setCellValueFactory( new PropertyValueFactory<>( employeeFields[a][1] ));
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            employeesTableView.getColumns().add( column );

        }

        componentsTable.addAll(DatabaseInnovativeSolutions.getComponents());
        employeesTable.addAll(DatabaseInnovativeSolutions.getTeamEmployees( managedTeam));

        ((AnchorPane)app.lookup( "#DEP_HEADComponentsTable" )).getChildren().add( componentsTableView );
        ((AnchorPane)app.lookup( "#DEP_HEADEmployeesTable" )).getChildren().add( employeesTableView );

    }


    void searchValue(){}

    void loadValues(){}

    void changeTable( String table ){

        if( table.compareTo( "Products") == 0 ){

            if( currentSection == false ) return;
            currentSection = false;
            employeesSection.setVisible( false );
            componentsSection.setVisible( true );

        }else{

            if( currentSection == true ) return;
            currentSection = true;
            componentsSection.setVisible( false );
            employeesSection.setVisible( true );

        }
    }

    void undoSearch(){}


}
