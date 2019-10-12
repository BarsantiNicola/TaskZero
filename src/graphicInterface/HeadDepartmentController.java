package graphicInterface;

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
    private static ObservableList<Product> productsTable = FXCollections.observableArrayList();
    private static TableView<Component> componentsTableView;
    private static TableView<Product> productsTableView;
    private static AnchorPane componentsSection;
    private static AnchorPane productsSection;
    private static TextField searchInput;
    private static MenuButton tablesMenu;
    private static boolean currentSection;
    private static String[] sections;

    HeadDepartmentController( Scene app ){

        String[] fields = { "Team" , "#Members" , "Project" };
        String[] fields2 = { "name" , "model" , "price" };
        TableColumn column;

        searchInput = (TextField)app.lookup( "#DEP_HEADSearch" );
        tablesMenu = (MenuButton)app.lookup( "#DEP_HEADMenu" );
        componentsSection = (AnchorPane)app.lookup( "#DEP_HEADComponents" );
        productsSection = (AnchorPane)app.lookup( "#DEP_HEADProducts" );

        componentsTableView =  new TableView<>();
        productsTableView = new TableView<>();

        componentsTable = FXCollections.observableArrayList();
        productsTable = FXCollections.observableArrayList();


        componentsTableView.setMinWidth( 498 );
        productsTableView.setMinWidth( 498 );
        componentsTableView.setMinHeight( 233 );
        productsTableView.setMinHeight( 233 );

        componentsTableView.setItems( componentsTable );
        productsTableView.setItems( productsTable );

        currentSection = false; //  set the table to  team products table

        for( int a = 0; a<fields.length; a++ ){

            column = new TableColumn( fields[a] );
            column.setCellValueFactory( new PropertyValueFactory<>( fields[a]) );
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            componentsTableView.getColumns().add( column );

        }

        for( int a = 0; a<fields2.length; a++ ){

            column = new TableColumn( fields2[a] );
            column.setCellValueFactory( new PropertyValueFactory<>( fields2[a]) );
            column.setMinWidth( 160 );
            column.setMaxWidth( 200 );
            productsTableView.getColumns().add( column );

        }

        ((AnchorPane)app.lookup( "#DEP_HEADComponentsTable" )).getChildren().add( componentsTableView );
        ((AnchorPane)app.lookup( "#DEP_HEADProductsTable" )).getChildren().add( productsTableView );

    }


    void searchValue(){}

    void loadValues(){}

    void changeTable( String table ){

        if( table.compareTo( "Products") == 0 ){

            if( currentSection == false ) return;
            currentSection = false;
            productsSection.setVisible( false );
            componentsSection.setVisible( true );

        }else{

            if( currentSection == true ) return;
            currentSection = true;
            componentsSection.setVisible( false );
            productsSection.setVisible( true );

        }
    }

    void undoSearch(){}


}
