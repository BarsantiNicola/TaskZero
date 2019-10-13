package graphicInterface;

import DatabaseManagement.DatabaseInnovativeSolutions;
import beans.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;

class AdminController extends InterfaceController{

    private static ObservableList<User> userTable = FXCollections.observableArrayList();
    private static TableView<User> userTableView;
    private static TextField searchInput;
    private static ImageView undoButton;
    private static HBox insertForm;
    private static Text adminFail;
    private static Text adminSuccess;

    //  THE FUNCTION LINKS THE TABLES OF "ADMIN INTERFACE" TO CONTROL APPLICATION
    AdminController( Scene app ){

        String[][] userFields = { { "Username" , "username" } , { "Password" ,"password" } , { "Name" , "name" } , { "Surname" ,"surname" } , { "Email" , "mail"} };  //  FIELDS OF TABLE EMPLOYEE
        TableColumn column;

        searchInput = (TextField)app.lookup( "#ADMINSearch" );  //  TEXT INPUT FOR SEARCH INFORMATION
        undoButton = (ImageView)app.lookup( "#ADMINUndo" );
        insertForm = (HBox)app.lookup( "#ADMINInsert" );

        adminFail = (Text)app.lookup( "#ADMINFail" );
        adminSuccess = (Text)app.lookup( "#ADMINSuccess" );

        userTableView =  new TableView<>();               //  TABLE EMPLOYEE
        userTableView.setEditable(true);
        userTable = FXCollections.observableArrayList();  //  COLLECTION OF BEANS-CLASS LINKED TO THE TABLE EMPLOYEE

        userTableView.setMinWidth( 498 );
        userTableView.setMinHeight( 233 );

        userTableView.setItems( userTable );

        for( int a = 0; a<userFields.length; a++ ){

            column = new TableColumn( userFields[a][0] );
            column.setCellValueFactory( new PropertyValueFactory<>( userFields[a][1] ));
            column.setMinWidth( 53 );
            column.setMaxWidth( 200 );
            userTableView.getColumns().add( column );

        }
        // INSERTION OF THE TABLE IN THE ANCHORPANE

         userTable.addAll(DatabaseInnovativeSolutions.getUsers());

        ((AnchorPane)app.lookup( "#ADMINUsersTable" )).getChildren().add( userTableView );
    }

    void searchValue() {

        String value = searchInput.getText();
        userTable.removeAll(userTable);
        userTable.addAll( DatabaseInnovativeSolutions.searchUsers( value ));
        undoButton.setVisible( true );

    }

    void undoSearch(){

        undoButton.setVisible( false );
        userTable.removeAll( userTable );
        userTable.addAll( DatabaseInnovativeSolutions.getUsers());

    }

    void insertValue() {

        Iterator<Node> it = insertForm.getChildren().iterator();
        HashMap<String, String> values = new HashMap<>();
        MessageDigest md = null;
        TextField app;

        try {

            md = MessageDigest.getInstance("SHA-1");

        } catch (NoSuchAlgorithmException e) {

            System.out.println("An error has occured while trying to encrypt data" + e.getMessage());
            System.exit(1);

        }

        while (it.hasNext()) {
            app = ((TextField) it.next());
            if (app.getPromptText().compareTo("Password") == 0)
                values.put(app.getPromptText(), new String(md.digest(app.getText().getBytes())));
            else
                values.put(app.getPromptText(), app.getText());
        }


        if ( DatabaseInnovativeSolutions.insertUser(values)) {

            it = insertForm.getChildren().iterator();
            while (it.hasNext())
                ((TextField) it.next()).setText("");

            userTable.removeAll(userTable);
            userTable.addAll(DatabaseInnovativeSolutions.getUsers());
            adminSuccess.setVisible(true);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
            adminSuccess.setVisible(false);

        }else{

            adminFail.setVisible(true);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
            adminFail.setVisible(false);

        }
    }



}
