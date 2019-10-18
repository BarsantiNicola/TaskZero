package graphicInterface;

import DatabaseManagement.DatabaseInnovativeSolutions;
import beans.Employee;
import beans.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Iterator;

public class HeadDepartmentController extends InterfaceController{

    private static ObservableList<Product> productsTable = FXCollections.observableArrayList();
    private static ObservableList<Employee> employeesTable = FXCollections.observableArrayList();
    private static TableView<Product> productsTableView;
    private static TableView<Employee> employeesTableView;
    private static AnchorPane productsSection;
    private static AnchorPane employeesSection;
    private static TextField searchInput;
    private static ImageView undoButton;
    private static boolean currentSection;
    private static int managedTeam;
    private static AnchorPane insertPopup;

    HeadDepartmentController( Scene app , int team ){

        //  associations from the field of the table and the identificator of the bean class variable related
        String[][] productFields = { { "Name" , "productName"} , { "Price" , "productPrice" } , { "Availability" , "ProductAvailability"} , { "Description" , "productDescription"} };
        String[][] employeeFields = { { "ID" , "IDemployee" }  , { "Name" , "name" }  , { "Surname" , "surname" } , { "Email" , "mail" } , {"Role" , "role"} };
        TableColumn column;

        managedTeam = team;

        searchInput = (TextField)app.lookup( "#DEP_HEADSearch" );
        undoButton = (ImageView)app.lookup( "#DEP_HEADUndo" );

        productsSection = (AnchorPane)app.lookup( "#DEP_HEADProducts" );
        employeesSection = (AnchorPane)app.lookup( "#DEP_HEADEmployees" );
        insertPopup = (AnchorPane)app.lookup( "#DEP_HEADInsertPopUp" );

        productsTableView =  new TableView<>();
        employeesTableView = new TableView<>();

        productsTable = FXCollections.observableArrayList();
        employeesTable = FXCollections.observableArrayList();


        productsTableView.setMaxWidth( 485 );
        employeesTableView.setMinWidth( 485 );
        productsTableView.setMaxHeight( 233 );
        employeesTableView.setMinHeight( 233 );
        productsTableView.setPrefSize( 485 , 233 );
        employeesTableView.setMinHeight( 233 );

        productsTableView.setItems( productsTable );
        employeesTableView.setItems( employeesTable );

        currentSection = false; //  set the table to  team products table

        for( int a = 0; a<productFields.length; a++ ){

            column = new TableColumn( productFields[a][0] );
            column.setCellValueFactory( new PropertyValueFactory<>( productFields[a][1] ));

            column.setMinWidth( 53 );
            column.setMaxWidth( 233 );
            productsTableView.getColumns().add( column );

        }

        for( int a = 0; a<employeeFields.length; a++ ){

            column = new TableColumn( employeeFields[a][0] );
            column.setCellValueFactory( new PropertyValueFactory<>( employeeFields[a][1] ));
            column.setMinWidth( 53 );
            column.setMaxWidth( 233 );
            employeesTableView.getColumns().add( column );

        }

        productsTable.addAll(DatabaseInnovativeSolutions.getTeamProducts( managedTeam ));
        employeesTable.addAll(DatabaseInnovativeSolutions.getTeamEmployees( managedTeam ));

        ((AnchorPane)app.lookup( "#DEP_HEADProductsTable" )).getChildren().add( productsTableView );
        ((AnchorPane)app.lookup( "#DEP_HEADEmployeesTable" )).getChildren().add( employeesTableView );

    }


    void showInsertPopup(){

        insertPopup.setVisible(true);

    }

    void closePopups(){
        insertPopup.setVisible(false);
    }

    void insertNewElement(){

        Iterator<Node> it = insertPopup.getChildren().iterator();
        Iterator<Product> product = productsTable.iterator();
        Node app;
        TextField value;
        HashMap<String , String> values = new HashMap<>();

        while (it.hasNext()) {
            app =  it.next();
            if( app instanceof TextField ) {
                value = (TextField) app;
                values.put( value.getPromptText(), value.getText());
                value.setText("");
                break;
            }

        }

        Product p;
        String name = values.get("ProductName");

        while( product.hasNext() ){
            p = product.next();
            if( p.getProductName().compareTo(name) == 0 ) {

                if (DatabaseInnovativeSolutions.updateProductAvailability(p.getProductType(), p.getProductAvailability() + 1)) {
                    productsTable.removeAll( p );
                    p.setProductAvailability( p.getProductAvailability() + 1 );
                    productsTable.add( p );
                    closePopups();


                }
                return;
            }
        }
        closePopups();


    }

    void searchValue(){

        String value = searchInput.getText();

        if( currentSection == false){

            employeesTable.removeAll( employeesTable );
            employeesTable.addAll( DatabaseInnovativeSolutions.searchTeamEmployees( managedTeam , value ));
            undoButton.setVisible( true );

        }else{

            productsTable.removeAll(productsTable);
            productsTable.addAll( DatabaseInnovativeSolutions.searchTeamProducts( managedTeam , value ));
            undoButton.setVisible( true );

        }
    };

    void changeTable( String table ){

        if( table.compareTo( "Employees") == 0 ){

            if( currentSection == false ) return;

            currentSection = false;
            productsSection.setVisible( false );

            if( undoButton.isVisible()){
                productsTable.removeAll( employeesTable );
                productsTable.addAll( DatabaseInnovativeSolutions.getTeamProducts(managedTeam));
            }

            employeesSection.setVisible( true );

        }else{

            if( currentSection == true ) return;

            currentSection = true;
            employeesSection.setVisible( false );

            if( undoButton.isVisible()){
                employeesTable.removeAll( productsTable );
                employeesTable.addAll( DatabaseInnovativeSolutions.getTeamEmployees(managedTeam));
            }

            productsSection.setVisible( true );


        }
    }

    void undoSearch(){

        undoButton.setVisible( false );
        if( currentSection == true ) {
            productsTable.removeAll(productsTable);
            productsTable.addAll(DatabaseInnovativeSolutions.getTeamProducts( managedTeam ));
        }else{
            employeesTable.removeAll(employeesTable);
            employeesTable.addAll(DatabaseInnovativeSolutions.getTeamEmployees( managedTeam ));
        }

    }


}
