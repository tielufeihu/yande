package game605.bean;

import lombok.Data;


/**
 * @ClassName Auth
 * @Description 权限
 */
@Data
public class Auth {

    private int id;
    private String name;

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
