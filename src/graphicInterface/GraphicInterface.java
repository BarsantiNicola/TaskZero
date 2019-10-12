package graphicInterface;

import DatabaseManagement.DatabaseInnovativeSolutions;
import DatabaseManagement.UserType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;


import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ResourceBundle;



public class GraphicInterface extends Application implements Initializable {

    private static Scene myApplication;
    private static UserType userType = UserType.CUSTOMER;
    private static InterfaceController myInterface;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("interface.fxml"));

        new DatabaseInnovativeSolutions();
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
        userType = DatabaseInnovativeSolutions.login( name , password );
        myApplication.lookup("#AlertMessage" ).setVisible( false );


        switch( userType ) {
            case CUSTOMER:

                myInterface = new CustomerController( myApplication , name );
                myApplication.lookup( "#CustomerPage" ).setVisible( true );
                break;

            case ADMINISTRATOR:

                myInterface = new AdminController( myApplication );
                myApplication.lookup( "#AdminPage" ).setVisible( true );
                break;

            case HEAD_DEPARTMENT:

                myInterface = new HeadDepartmentController( myApplication , DatabaseInnovativeSolutions.getTeam( name ) );
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

        String section = ((MenuItem)event.getSource()).getText();  //  name of the table selected

        if( myInterface instanceof  CustomerController ) {
            ((CustomerController) myInterface).changeTable( section );
            return;
        }
        if( myInterface instanceof HeadDepartmentController ) {
            ((HeadDepartmentController) myInterface).changeTable( section );
            return;
        }

    }

    @FXML
    private void undoSearch(){

        if( myInterface instanceof AdminController ) {
            ((AdminController) myInterface).undoSearch();
            return;
        }

        if( myInterface instanceof  CustomerController ) {
            ((CustomerController) myInterface).undoSearch();
            return;
        }
        if( myInterface instanceof HeadDepartmentController ) {
            ((HeadDepartmentController) myInterface).undoSearch();
            return;
        }
    }




}
