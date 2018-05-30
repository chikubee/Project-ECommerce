package com.ecommerce.ECommerce.DTO;

import com.ecommerce.ECommerce.UserSecurity.HashPassword;

public class UserDto {
    private String id;
    private String firstname;
    private String lastname;
    private String address;
    private String contact;
    private String email;
    private String password;
    private Boolean isActive;
    private String verificationToken;


    public UserDto(String firstname, String lastname, String address, String contact, String email, String password, Boolean isActive, String verificationToken) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.verificationToken = verificationToken;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDto() {
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        this.isActive = active;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
