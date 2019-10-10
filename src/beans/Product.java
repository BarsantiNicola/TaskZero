package beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private final SimpleStringProperty name;
    private final SimpleStringProperty model;
    private final SimpleIntegerProperty price;

    public Product( String n , String m , Integer p ){

        name = new SimpleStringProperty(n);
        model = new SimpleStringProperty(m);
        price = new SimpleIntegerProperty(p);

    }

    public String getName(){
        return name.get();
    }

    public String getModel(){
        return model.get();
    }

    public int getPrice(){
        return price.get();
    }
}
