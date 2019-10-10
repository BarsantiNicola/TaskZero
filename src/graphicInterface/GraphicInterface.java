package graphicInterface;

import beans.Employee;
import beans.Product;
import com.sun.istack.internal.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;


public class GraphicInterface extends Application implements Initializable {

    private static Scene myApplication;
    private static String currentSection;
    private static String userType = "DEP_HEAD";
    private static HashMap<String , String[]> sections;
    private static HashMap<String,ObservableList<Object>> tablesValues;
    private static ObservableList<Employee> employeeTable = FXCollections.observableArrayList();
    private static ObservableList<Product> productTable = FXCollections.observableArrayList();
    private static TableView<Employee> employeeTableView;
    private static TableView<Product> productTableView;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("interface.fxml"));
        String[] depHeadSections = { "Employees" , "Products"};
        String[] customerSections = { "Orders" , "Products"};
        String[] adminSections = { "Employees" };
        tablesValues = new HashMap<>();

        primaryStage.setTitle("Innovation Solutions");
        myApplication = new Scene( root , 590 , 390 );
        primaryStage.setScene( myApplication );
        primaryStage.setResizable( false );

        sections = new HashMap<>();
        sections.put( "ADMIN" , adminSections );
        sections.put( "CUSTOMER" , customerSections );
        sections.put( "DEP_HEAD" , depHeadSections );

        System.out.println( "Hard Coded Data Uploaded");

        primaryStage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }


    @FXML
    private void accessRequest( ActionEvent event ){

        String name = ((TextField)myApplication.lookup( "#FormName" )).getCharacters().toString();
        String password = ((PasswordField)myApplication.lookup(( "#FormPassword" ))).getText();
        MessageDigest md = null;

        try {

            md = MessageDigest.getInstance( "SHA-1" );

        }catch( NoSuchAlgorithmException e ) {

            System.out.println("An error has occured while trying to encrypt data" + e.getMessage());
            System.exit(1 );

        }

        password = new String( md.digest(password.getBytes() ));
        System.out.println( "Nome: " + name + "\tPassword: " + password );
        myApplication.lookup("#AlertMessage" ).setVisible( false );

        // userType = getAccess( name , password );
        // sections = getSection( userType );
        // if( sections == NULL || sections.length() == 0 ) System.exit( 1 );
        // else currentSection = sections[0];

        if( userType == null ){

            myApplication.lookup("#AlertMessage" ).setVisible( true );
            return;

        }

        if( userType.compareTo( "CUSTOMER" ) == 0 ) myApplication.lookup( "#CustomerPage" ).setVisible( true );

        if( userType.compareTo( "ADMIN" ) == 0 ) myApplication.lookup( "#AdminPage" ).setVisible( true );

        if( userType.compareTo( "DEP_HEAD" ) == 0 ) myApplication.lookup( "#HeadPage" ).setVisible( true );

        loadTables();

        myApplication.lookup( "#AccessPage" ).setVisible( false );


    }

    @FXML
    private void searchValue( ActionEvent event ){

        String value = ((TextField)myApplication.lookup( "#" + userType + "Search")).getCharacters().toString();
        System.out.println( "Ricerca: " + value +"\n");
    }

    @FXML
    private void changeTable( ActionEvent event ){
        String section = ((MenuItem)event.getSource()).getText();

        if( currentSection.compareTo( section) == 0 ) return;
        myApplication.lookup("#" + userType + currentSection ).setVisible( false );
        myApplication.lookup( "#" + userType + section ).setVisible( true );
        currentSection = section;

    }
    private void loadTables(){

        String[] mySections;
        ObservableList<Object> tuples = FXCollections.observableArrayList();

        mySections = sections.get( userType );
        if( mySections == null || userType == null ){

            System.out.println("Error in loadTables for userType: " + userType );
            return;

        }

        currentSection = mySections[0];

        for( String section: mySections ){

          //  tuples = getTableValues( section );
            tablesValues.put( section , tuples );
            createTable( section , tuples );

        }

    }

    private void createTable( String section , ObservableList<Object> tuples ){

        String[] fields = { "nome" , "cognome" , "email"};
        String[] fields2 = { "name" , "model" , "price"};
        TableView<Object> table = new TableView<>();
        TableColumn col;

        if( section.compareTo("Employees") == 0 ) {

            employeeTableView = new TableView<>();
            testEmployee(employeeTable, (int) (Math.random() * 100) % 6);
            employeeTableView.setItems(employeeTable);
            employeeTableView.setMinWidth(498);
            employeeTableView.setMinHeight(233);
            for( int a = 0; a<fields.length; a++ ){
                col = new TableColumn( fields[a]);
                col.setCellValueFactory(new PropertyValueFactory<>(fields[a]));
                col.setMinWidth(160);
                col.setMaxWidth(200);
                employeeTableView.getColumns().add(col);
            }
            ((AnchorPane)myApplication.lookup( "#" + userType + section + "Table")).getChildren().add(employeeTableView);

        }else{

            productTableView = new TableView<>();
            testProduct( productTable , (int)(Math.random()*100)%6);
            productTableView.setItems(productTable);
            productTableView.setMinWidth(498);
            productTableView.setMinHeight(233);
            for( int a = 0; a<fields2.length; a++ ){
                col = new TableColumn( fields2[a]);
                col.setCellValueFactory(new PropertyValueFactory<>(fields2[a]));
                col.setMinWidth(160);
                col.setMaxWidth(200);
                productTableView.getColumns().add(col);
            }
            ((AnchorPane)myApplication.lookup( "#" + userType + section + "Table")).getChildren().add(productTableView);

        }





        System.out.println("Width " + table.getWidth() + " Height: " + table.getHeight());
        System.out.println("#" + userType + section + "Table" );
        System.out.println("Table loaded");

        //  fields = getFields( userType , section );


    }


    private void testEmployee( ObservableList<Employee> values , int value ){

        switch( value ){

            case 5:  values.add( new Employee( "Luca" , "Cardelli" , "prova5@unipi.it"));
            case 4:  values.add( new Employee( "Marco" , "Ponziani" , "prova4@unipi.it"));
            case 3:  values.add( new Employee( "Mirco" , "Quintavalla" , "prova3@unipi.it"));
            case 2:  values.add( new Employee( "Riccardo" , "Bertini" , "prova2@unipi.it"));
            case 1:  values.add( new Employee( "Lorenzo" , "Quintavalla" , "prova1@unipi.it"));
            default: values.add( new Employee( "Nicola" , "Barsanti" , "prova0@unipi.it"));

        }

    }

    private void testProduct( ObservableList<Product> values , int value ){

        switch( value ){

            case 5:  values.add( new Product( "PlayStation2" , "1.0" , 30));
            case 4:  values.add( new Product( "Google Nest" , "2.1" , 50));
            case 3:  values.add( new Product( "Ferrari" , "Gallardo" , 300000));
            case 2:  values.add( new Product( "Shuttle" , "PS5823-1" , 20000000));
            case 1:  values.add( new Product( "Acer Aspire" , "5951g" , 1000));
            default: values.add( new Product( "Iphone" , "4" , 800));

        }

    }
}
