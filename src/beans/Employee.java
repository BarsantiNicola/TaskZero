package beans;

import javafx.beans.property.SimpleStringProperty;

public static class Employee{

    private final SimpleStringProperty role;

    private Employee( String r ){
        role = new SimpleStringProperty( r );
    }

    public String getRole(){
        return role.get();
    }
    
}
