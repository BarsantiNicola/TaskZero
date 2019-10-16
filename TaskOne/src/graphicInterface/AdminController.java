package graphicInterface;

import DatabaseManagement.DatabaseInnovativeSolutions;
import beans.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;

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
    private static AnchorPane insertPopup, updatePopup, deletePopup;

    //  THE FUNCTION LINKS THE TABLES OF "ADMIN INTERFACE" TO CONTROL APPLICATION
    AdminController( Scene app ){

        String[][] userFields = { { "Username" , "username" } , { "Password" ,"password" } , { "Name" , "name" } , { "Surname" ,"surname" } , { "Email" , "mail"} , { "Role" , "role"} , { "Salary" , "salary"} , { "Team" , "team"} };  //  FIELDS OF TABLE EMPLOYEE
        TableColumn column;
        final TableRow p = null;
        searchInput = (TextField)app.lookup( "#ADMINSearch" );  //  TEXT INPUT FOR SEARCH INFORMATION
        undoButton = (ImageView)app.lookup( "#ADMINUndo" );
        insertForm = (HBox)app.lookup( "#ADMINInsert" );
        insertPopup = (AnchorPane)app.lookup( "#ADMINInsertPopUp" );
        updatePopup = (AnchorPane)app.lookup( "#ADMINUpdatePopUp" );
        deletePopup = (AnchorPane)app.lookup( "#ADMINDeletePopUp" );

        adminFail = (Text)app.lookup( "#ADMINFail" );
        adminSuccess = (Text)app.lookup( "#ADMINSuccess" );

        userTableView =  new TableView<>();               //  TABLE EMPLOYEE
        userTableView.setEditable(true);
        userTable = FXCollections.observableArrayList();  //  COLLECTION OF BEANS-CLASS LINKED TO THE TABLE EMPLOYEE

        userTableView.setMinWidth( 492 );
        userTableView.setMinHeight( 230 );
        userTableView.setMaxWidth( 492 );
        userTableView.setMaxHeight( 230 );

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

    public void deleteUser(){

        Iterator<Node> node = deletePopup.getChildren().iterator();
        Iterator<User> users;
        Node app;
        User scroll;
        String username = null;

        while( node.hasNext() ){
            app = node.next();
            if( app instanceof TextField ) {
                username = ((TextField) app).getText();
                break;
            }
        }

        if( username == null ) return;
        if( DatabaseInnovativeSolutions.deleteUser( username ) == 0 ){
            users = userTable.iterator();
            while( users.hasNext()){
                scroll = users.next();
                if( scroll.getUsername().compareTo(username) == 0 ){
                    userTable.remove( scroll );
                    return;
                }
            }

        }

    }
    public void showInsertPopup(){

        insertPopup.setVisible( true );

    }

    public void showUpdatePopup(){

        updatePopup.setVisible( true );

    }

    public void showDeletePopup(){

        deletePopup.setVisible( true );

    }

    public void closePopups(){

        insertPopup.setVisible( false );
        updatePopup.setVisible( false );
        deletePopup.setVisible( false );

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

    void insertUser() {

        Iterator<Node> it = insertPopup.getChildren().iterator();
        HashMap<String, String> values = new HashMap<>();
        TextField value;
        Node app;

        while (it.hasNext()) {
            app =  it.next();
            if( app instanceof TextField ) {
                value = (TextField) app;
                values.put( value.getPromptText(), value.getText());
            }
        }

        User newUser = new User( values.get("Username") , values.get( "Name") , values.get("Surname") , values.get("Password") , values.get("Mail") , values.get("Role") , Integer.parseInt(values.get("Salary")) , Integer.parseInt(values.get("Team" )));
        if ( DatabaseInnovativeSolutions.insertUser( newUser )) {

            it = insertForm.getChildren().iterator();
            while (it.hasNext())
                ((TextField) it.next()).setText("");

            userTable.removeAll(userTable);
            userTable.add(newUser);
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

    void updateUser(){

        Iterator<Node> it = updatePopup.getChildren().iterator();
        Iterator<User> users;
        User user;
        Node app;
        String username = null;
        int salary = 0;
        while( it.hasNext() ){
            app = it.next();
            if( app instanceof TextField )
                if( ((TextField)app).getPromptText().compareTo("Username") == 0 )
                    username = ((TextField)app).getText();
                else
                    salary = Integer.parseInt(((TextField)app).getText());
        }

        DatabaseInnovativeSolutions.updateSalary( salary , username );
        userTable.removeAll(userTable);
        userTable.addAll(DatabaseInnovativeSolutions.getUsers());

    }



}
