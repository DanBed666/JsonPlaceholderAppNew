package com.example.jsonplaceholderappnew;

public class UserDB
{
    private String id;
    private String name;
    private String username;
    private String email;
    private String city;

    public UserDB(String id, String name, String username, String email, String city)
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
