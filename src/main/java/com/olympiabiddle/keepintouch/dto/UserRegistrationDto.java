package com.olympiabiddle.keepintouch.dto;

import javax.validation.constraints.AssertTrue;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.olympiabiddle.keepintouch.repository.FieldMatch;

//import net.javaguides.springboot.springsecurity.constraint.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
    @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserRegistrationDto {

    @NotEmpty(message="Please enter your first name.")
    private String firstName;

    @NotEmpty(message="Please enter your last name.")
    private String lastName;

    @NotEmpty(message="Please enter a password.")
    private String password;

    @NotEmpty(message="Please confirm your password.")
    private String confirmPassword;

    @Email
    @NotEmpty(message="Please your email. e.g. john@email.com")
    private String email;

    @Email
    @NotEmpty(message="Please confirm your email.")
    private String confirmEmail;

    @AssertTrue(message="Please check the box.")
    private Boolean terms;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }
}
