package com.se391.davidlandi.landifinal;

/**
 * Created by David Landi on 5/24/2015.
 */
public class FieldValidator {
    private String nameInput;
    private String emailInput;
    private String userNameInput;
    private String passwordInput;
    private String confPasswordInput;
    private String newline = System.getProperty("line.separator");
    private boolean loginFields = false;
    private boolean signUpFields = false;

    public FieldValidator(){}

    public FieldValidator(String txtName, String txtEmail, String txtUserName, String txtPassword,
                          String txtConfPassword){
        setNameInput(txtName);
        setEmailInput(txtEmail);
        setUserNameInput(txtUserName);
        setPasswordInput(txtPassword);
        setConfPasswordInput(txtConfPassword);
    }

    public FieldValidator(String txtUserName, String txtPassword){
        setUserNameInput(txtUserName);
        setPasswordInput(txtPassword);
    }

    public String getAllSignUpFormErrors(){
        String errors = "";
        errors += getNameFieldErrors(getNameInput());
        errors += getEmailFieldErrors(getEmailInput());
        errors += getUserNameFieldErrors(getUserNameInput(), getUserNameInput());
        errors += getPasswordFieldErrors(getPasswordInput(), getConfPasswordInput());
        if(errors.equals("valid")){
            return "Successful";
        }
        return errors;
    }

    public String getAllLoginFromErrors(){
        String errors = "";
        errors += getUserNameLoginErrors(getUserNameInput());
        errors += getPasswordLoginErrors(getPasswordInput());
        if(errors.equals("valid")){
            return "Successful";
        }
        return errors;
    }

    public boolean loginFieldsAreValid(){
        boolean isValid = false;
        if(getUserNameLoginErrors(getUserNameInput()).equals("valid")){
            isValid = true;
        }
        if(!getUserNameLoginErrors(getUserNameInput()).equals("valid")){
            isValid = false;
        }
        if(getPasswordLoginErrors(getPasswordInput()).equals("valid")){
            isValid = true;
        }
        if(!getPasswordLoginErrors(getPasswordInput()).equals("valid")){
            isValid = false;
        }
        return isValid;
    }

    public boolean signUpFieldsAreValid(){
        boolean isValid;
        if(getNameFieldErrors(getNameInput()).equals("valid")){
            isValid = true;
        }
        else if(getEmailFieldErrors(getEmailInput()).equals("valid")){
            isValid = true;
        }
        else if(getUserNameFieldErrors(getUserNameInput(), getNameInput()).equals("valid")){
            isValid = true;
        }
        else if(getPasswordFieldErrors(getPasswordInput(), getConfPasswordInput()).equals("valid")){
            isValid = true;
        }
        else{isValid = false;}

        return isValid;
    }

    private String getPasswordFieldErrors(String password1, String password2){
        String msg = "";
        if(! password1.equals(password2)){
            msg += "Password and Confirm password fields must be the same." + newline;
        }
        else if(password1.equals(null)){
            msg += "Password field can not be empty." + newline;
        }
        else if(password2.equals(null)){
            msg += "Confirm Password field can not be empty." + newline;
        }
        else if(password1.length() < 6){
            msg += "Password must be at least 6 characters long." + newline;
        }
        else{msg = "valid";}
        return msg;
    }

    private String getPasswordLoginErrors(String password1){
        String msg = "";
        if(password1.equals(null)){
            msg += "Password field can not be empty." + newline;
        }
        else if(password1.length() < 6){
            msg += "Password must be at least 6 characters long.";
        }
        else{msg = "valid";}
        return msg;
    }

    private String getNameFieldErrors(String txtName){
        String msg = "";
        if(txtName.equals(null)){
            msg += "Name field can not be empty" + newline;
        }
        else if(txtName.length() < 2) {
            msg += "Name must be greater than 2 letters." + newline;
        }
        else {msg = "valid";}
        return msg;
    }

    private String getEmailFieldErrors(String txtEmail){
        String msg = "";
        CharSequence dot = ".";
        CharSequence atSign = "@";
        if (! txtEmail.contains(dot)){
            msg = "Invalid Email";
        }
        else if(! txtEmail.contains(atSign)){
            msg = "Invalid Email";
        }
        else if(txtEmail.equals(null)){
            msg = "Invalid Email";
        }
        return msg;
    }

    private String getUserNameFieldErrors(String txtUserName, String txtName){
        String msg = "";
        if(txtUserName.equals(null)){
            msg += "Username field can not be empty" + newline;
        }
        else if(txtUserName.length() < 2) {
            msg += "Username must be greater than 2 letters." + newline;
        }
        else if(txtUserName.equals(txtName)){
            msg += "Username can not be the same as your name." + newline;
        }
        else {
            msg = "valid";
        }
        return msg;
    }

    private String getUserNameLoginErrors(String txtUserName){
        String msg = "";
        if(txtUserName.equals(null)){
            msg += "Username field can not be empty" + newline;
        }
        else if(txtUserName.length() < 2) {
            msg += "Username must be greater than 2 letters." + newline;
        }
        else {msg = "valid";}
        return msg;
    }

    public String getNameInput() {
        return nameInput;
    }

    public void setNameInput(String nameInput) {
        this.nameInput = nameInput;
    }

    public String getEmailInput() {
        return emailInput;
    }

    public void setEmailInput(String emailInput) {
        this.emailInput = emailInput;
    }

    public String getUserNameInput() {
        return userNameInput;
    }

    public void setUserNameInput(String userNameInput) {
        this.userNameInput = userNameInput;
    }

    public String getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(String passwordInput) {
        this.passwordInput = passwordInput;
    }

    public String getConfPasswordInput() {
        return confPasswordInput;
    }

    public void setConfPasswordInput(String confPasswordInput) {
        this.confPasswordInput = confPasswordInput;
    }
}
