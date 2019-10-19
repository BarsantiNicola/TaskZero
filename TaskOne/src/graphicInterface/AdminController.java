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
import java.util.HashMap;
import java.util.Iterator;


//  class for manage the administrator interface, the interface has one table for show the user
//  and three popups to permit the user to add/update and remove users in the database

class AdminController extends InterfaceController{

    private static ObservableList<User> userTable = FXCollections.observableArrayList();
    private static TableView<User> userTableView;
    private static TextField searchInput;
    private static ImageView undoButton;
    private static AnchorPane insertPopup, updatePopup, deletePopup;

    //  THE FUNCTION LINKS THE TABLES OF "ADMIN INTERFACE" TO CONTROL APPLICATION
    AdminController( Scene app ){

        //  it links the fields to the class variable associated
        String[][] userFields = { { "Username" , "username" } , { "Password" ,"password" } , { "Name" , "name" } , { "Surname" ,"surname" } , { "Email" , "mail"} , { "Role" , "role"} , { "Salary" , "salary"} , { "Team" , "team"} };  //  FIELDS OF TABLE EMPLOYEE
        TableColumn column;

        searchInput = (TextField)app.lookup( "#ADMINSearch" );  //  TEXT INPUT FOR SEARCH INFORMATION
        undoButton = (ImageView)app.lookup( "#ADMINUndo" );     //  BUTTON FOR CLOSE THE SEARCHING TABLE


        insertPopup = (AnchorPane)app.lookup( "#ADMINInsertPopUp" );  // POPUP FOR INSERT USERS
        updatePopup = (AnchorPane)app.lookup( "#ADMINUpdatePopUp" );  // POPUP FOR UPDATE USERS
        deletePopup = (AnchorPane)app.lookup( "#ADMINDeletePopUp" );  // POPUP FOR DELETE USERS

        userTableView =  new TableView<>();
        userTableView.setEditable(true);
        userTableView.setMinWidth( 492 );
        userTableView.setMinHeight( 230 );
        userTableView.setMaxWidth( 492 );
        userTableView.setMaxHeight( 230 );

        userTable = FXCollections.observableArrayList();  //  COLLECTION OF BEANS-CLASS LINKED TO THE TABLE

        userTableView.setItems( userTable );

        //  LINKING OF THE COLUMN FIELDS TO THE BEANS-CLASS VARIABLES
        for( int a = 0; a<userFields.length; a++ ){

            column = new TableColumn( userFields[a][0] );
            column.setCellValueFactory( new PropertyValueFactory<>( userFields[a][1] ));
            column.setMinWidth( 53 );
            column.setMaxWidth( 200 );
            userTableView.getColumns().add( column );

        }

         userTable.addAll(DatabaseInnovativeSolutions.getUsers());
         ((AnchorPane)app.lookup( "#ADMINUsersTable" )).getChildren().add( userTableView );

    }


    //  it shows the popup for insert new users
    @Override
    void showInsertPopup(){ insertPopup.setVisible( true ); }

    //  it shows the popup for update the users salary
    void showUpdatePopup(){ updatePopup.setVisible( true ); }

    //  it shows the popup for delete users accounts
    void showDeletePopup(){ deletePopup.setVisible( true ); }

    //  it closes all popups of the interface
    @Override
    void closePopups(){

        insertPopup.setVisible( false );
        updatePopup.setVisible( false );
        deletePopup.setVisible( false );

    }

    //  it searches the input value in all the possible String fields
    @Override
    void searchValue() {

        String value = searchInput.getText();

        userTable.removeAll(userTable);
        userTable.addAll( DatabaseInnovativeSolutions.searchUsers( value ));
        undoButton.setVisible( true );

    }

    //  it does the undo of a research reset the interface to the default behavior
    @Override
    void undoSearch(){

        undoButton.setVisible( false );
        userTable.removeAll( userTable );
        userTable.addAll( DatabaseInnovativeSolutions.getUsers());

    }

    //  it insert a user insert by the textFile given in the insert popup
    @Override
    void insertNewElement() {

        Iterator<Node> it = insertPopup.getChildren().iterator();
        HashMap<String, String> values = new HashMap<>();
        TextField value;
        Node app;

        //  GETTING ALL THE VALUE INSERTED INTO THE FORM
        while (it.hasNext()){

            app =  it.next();
            if( app instanceof TextField ){

                value = (TextField) app;
                values.put( value.getPromptText(), value.getText());
                value.setText("");

            }
        }

        //  parseInt give errors if used on a size 0 string
        User newUser;
        if( values.get("Team").length() >0 && values.get("Salary").length() > 0 )

            newUser = new User(values.get("Username"),
                    values.get("Name"), values.get("Surname"),
                    values.get("Password"), values.get("Mail"),
                    values.get("Role"), Integer.parseInt(values.get("Salary")),
                    Integer.parseInt(values.get("Team")));
        else
            newUser = new User(values.get("Username"),
                    values.get("Name"), values.get("Surname"),
                    values.get("Password"), values.get("Mail"),
                    values.get("Role"), 0,
                    0);

        if(DatabaseInnovativeSolutions.insertUser(newUser))
            userTable.add(newUser);

        closePopups();

    }

    //  it updates the salary of a employee given by his username(the primary key of users)
    void updateUser() {

        Iterator<Node> it = updatePopup.getChildren().iterator();
        Node app;
        String username = null;
        int salary = 0;

        //  GETTING ALL THE VALUES INSERTED INTO THE FORM
        while (it.hasNext()) {
            app = it.next();
            if (app instanceof TextField)
                if (((TextField) app).getPromptText().compareTo("Username") == 0)
                    username = ((TextField) app).getText();
                else
                    salary = Integer.parseInt(((TextField) app).getText());
        }

        DatabaseInnovativeSolutions.updateSalary(salary, username);

        userTable.removeAll(userTable);
        userTable.addAll(DatabaseInnovativeSolutions.getUsers());
        closePopups();


    }


    //  it deletes a user account defined by his username(primary key of users)
    void deleteUser(){

        Iterator<Node> node = deletePopup.getChildren().iterator();
        Iterator<User> users;
        Node app;
        User scroll;
        String username = null;

        while( node.hasNext() ){

            app = node.next();

            if( app instanceof TextField ) {

                username = ((TextField) app).getText();
                ((TextField) app).setText("");
                break;

            }
        }

        if( username != null && DatabaseInnovativeSolutions.deleteUser( username )){

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

    void changeTable( String section){}

}
