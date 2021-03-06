package pl.indexpz.utils;

public class User {
    private int id = 0;
    private String name;
    private String password;
    private String email;

    public User() {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }


    public User(String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  id + " " + name + " " + password +  " " + email;
    }

}