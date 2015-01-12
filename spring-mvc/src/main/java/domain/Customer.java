package domain;

/**
 * Created by xdar on 8.1.15.
 */
public class Customer {

    public Customer(int id, String name){
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
