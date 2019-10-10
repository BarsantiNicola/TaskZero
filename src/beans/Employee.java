package beans;

import javafx.beans.property.SimpleStringProperty;

public class Employee{

    private final SimpleStringProperty nome;
    private final SimpleStringProperty cognome;
    private final SimpleStringProperty email;

    public Employee( String n , String c , String e ){
        nome = new SimpleStringProperty( n );
        cognome = new SimpleStringProperty( c );
        email = new SimpleStringProperty( e );
    }

    public String getNome(){
        return nome.get();
    }

    public String getCognome(){
        return cognome.get();
    }

    public String getEmail(){
        return email.get();
    }

}
