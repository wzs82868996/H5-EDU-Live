package h5EDULive.dao.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="user")
public class User  {

//    private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
    private String mobile;

    @Column(name = "name")
    private String name;

    private String password;

    //领域
    private String major;

    //教师还是学生
    private String attr;

    private String gender;

    private String birthday;

    //个人简介
    private String profile;

    private String mail;

    public User(){}

    public User(String mobile,String attr,String name,String password){
        this.mobile=mobile;
        this.attr=attr;
        this.name=name;
        this.password=password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;

    public int getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String message(){
        return getName()+" "+getMobile()+" "+getAttr()+" "+getPassword();
    }

}
