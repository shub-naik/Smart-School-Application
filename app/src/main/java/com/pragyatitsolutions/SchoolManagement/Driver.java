package com.pragyatitsolutions.SchoolManagement;

public class Driver {
    String fullname, mobile_number, driver_license, password;

    public Driver() {
    }

    public Driver(String fullname, String mobile_number, String driver_license, String password) {
        this.fullname = fullname;
        this.mobile_number = mobile_number;
        this.driver_license = driver_license;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getDriver_license() {
        return driver_license;
    }

    public void setDriver_license(String driver_license) {
        this.driver_license = driver_license;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
