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

enum UserType{
    ADMINISTRATOR,
    HEAD_DEPARTMENT,
    CUSTOMER,
    NOUSER
};

public class GraphicInterface extends Application implements Initializable {

    private static Scene myApplication;
    private static UserType userType = UserType.HEAD_DEPARTMENT;
    private static InterfaceController myInterface;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("interface.fxml"));

        primaryStage.setTitle("Innovation Solutions");
        myApplication = new Scene( root , 590 , 390 );
        primaryStage.setScene( myApplication );
        primaryStage.setResizable( false );


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


        switch( userType ) {
            case CUSTOMER:

                myInterface = new CustomerController( myApplication );
                myApplication.lookup( "#CustomerPage" ).setVisible( true );
                break;

            case ADMINISTRATOR:

                myInterface = new AdminController( myApplication );
                myApplication.lookup( "#AdminPage" ).setVisible( true );
                break;

            case HEAD_DEPARTMENT:

                myInterface = new HeadDepartmentController( myApplication );
                myApplication.lookup( "#HeadPage" ).setVisible( true );
                break;

            default:

                myApplication.lookup("#AlertMessage").setVisible(true);
                return;

        }

        myApplication.lookup( "#AccessPage" ).setVisible( false );


    }

    @FXML
    private void searchValue( ActionEvent event ){

        if( myInterface instanceof AdminController ) {
            ((AdminController) myInterface).searchValue();
            return;
        }

        if( myInterface instanceof  CustomerController ) {
            ((CustomerController) myInterface).searchValue();
            return;
        }
        if( myInterface instanceof HeadDepartmentController ) {
            ((HeadDepartmentController) myInterface).searchValue();
            return;
        }
    }

    @FXML
    private void changeTable( ActionEvent event ){

        String section = ((MenuItem)event.getSource()).getText();

        if( myInterface instanceof  CustomerController ) {
            ((CustomerController) myInterface).changeTable();
            return;
        }
        if( myInterface instanceof HeadDepartmentController ) {
            ((HeadDepartmentController) myInterface).changeTable();
            return;
        }

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
