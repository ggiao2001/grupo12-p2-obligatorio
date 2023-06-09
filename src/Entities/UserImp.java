package Entities;

import Interfaces.User;

public class UserImp<T> implements User<T> {
    private long id;
    private String name;

    public UserImp(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
