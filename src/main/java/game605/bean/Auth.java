package game605.bean;

import lombok.Data;

@Data
public class Auth {

    int id;
    String name;

    public Auth(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Auth(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
