package graphicInterface;

import com.sun.istack.internal.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.options.LoggingOption;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class GraphicInterface extends Application implements Initializable {

    private static Scene myApplication;
    private static String currentSection;
    private static String userType = "DEP_HEAD";
    private static HashMap<String , String[]> sections;
    private static Logger LOG = Logger.getLogger( GraphicInterface.class);
    private static HashMap<String,ObservableList<Object>> tablesValues;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("interface.fxml"));

        primaryStage.setTitle("Innovation Solutions");
        myApplication = new Scene( root , 590 , 390 );
        primaryStage.setScene(myApplication);
        primaryStage.setResizable( false );

       // System.out.println(base.getChildren().get( base.getChildren().indexOf("FormName")));
        sections = new HashMap<>();
        String[] depHeadSections = { "Employees" , "Products"};
        String[] customerSections = { "Orders" , "Products"};
        String[] adminSections = { "Employees" };
        sections.put( "ADMIN" , adminSections );
        sections.put( "CUSTOMER" , customerSections );
        sections.put( "DEP_HEAD" , depHeadSections );
        System.out.println( "Hard Coded Data Uploaded");
        tablesValues = new HashMap<>();

        primaryStage.show();
       // System.out.println("Contenuto: " + p.getCharacters().toString());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }

    public static void startInterface( String[] args ){



        launch(args);

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

    void createTable( String section , ObservableList<Object> tuples ){


    }

}
