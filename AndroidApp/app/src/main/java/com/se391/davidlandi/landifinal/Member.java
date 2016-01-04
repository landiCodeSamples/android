package com.se391.davidlandi.landifinal;

/**
 * Created by David Landi on 5/23/2015.
 */
public class Member {
    private int usrID;
    private String usrName;
    private String usrEmail;
    private String usrLoginName;
    private String password;

    public Member(){}

    public Member(int ID, String name, String email, String loginName, String password ){
        setUsrID(ID);
        setUsrName(name);
        setUsrEmail(email);
        setUsrLoginName(loginName);
        setPassword(password);
    }

    public int getUsrID() {return usrID;}

    public void setUsrID(int usrID) {this.usrID = usrID;}

    public String getUsrName() {return usrName;}

    public void setUsrName(String usrName) {this.usrName = usrName;}

    public String getUsrEmail() {return usrEmail;}

    public void setUsrEmail(String usrEmail) {this.usrEmail = usrEmail;}

    public String getUsrLoginName() {return usrLoginName;}

    public void setUsrLoginName(String usrLoginName) {this.usrLoginName = usrLoginName;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}
