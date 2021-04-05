package fit.hanu.myfriends.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Friend implements Serializable {
    private long id;
    private String name;
    private String phone;
    private String email;

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //data from the form
    public Friend(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    //data from database
    public Friend(long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Friend() {
    }
}
