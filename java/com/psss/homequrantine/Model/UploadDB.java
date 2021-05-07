package com.psss.homequrantine.Model;

public class UploadDB {
    String name,pincode;

    public UploadDB() {
    }

    public UploadDB(String name, String pincode) {
        this.name = name;
        this.pincode = pincode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
