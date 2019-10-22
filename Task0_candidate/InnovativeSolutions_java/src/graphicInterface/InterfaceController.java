package graphicInterface;


import java.io.PrintWriter;

abstract class InterfaceController {

    public static PrintWriter LOG = new PrintWriter( System.out );

    abstract void searchValue();
    abstract void undoSearch();
    abstract void showInsertPopup();
    abstract void insertNewElement();
    abstract void closePopups();
    abstract void changeTable( String SECTION );

}
