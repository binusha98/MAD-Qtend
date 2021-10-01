package com.example.qtend;

public class User {
    private String id;
    private String name;
    private String desig;
    private String email;
    private String phne;
    private String pswde;

    public User() {
    }

    public User(String id, String name, String desig, String email,  String phne, String pswde) {
        this.id = id;
        this.name = name;
        this.desig = desig;
        this.email = email;
        this.phne = phne;
        this.pswde = pswde;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public String getPhne() {
        return phne;
    }

    public void setPhne(String phne) {
        this.phne = phne;
    }

    public String getPswde() {
        return pswde;
    }

    public void setPswde(String pswde) {
        this.pswde = pswde;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void getImageurl() {

    }
}
