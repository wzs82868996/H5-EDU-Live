package h5EDULive.exam.web.dto;

public class Student {
    private String sNickname;
    private String sPassword;
    private String sPhone;
    private String icon;

    public String getsNickname() {
        return sNickname;
    }

    public void setsNickname(String sNickname) {
        this.sNickname = sNickname;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
