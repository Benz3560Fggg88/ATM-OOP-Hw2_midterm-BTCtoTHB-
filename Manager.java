package ATM_Lab2;

public class Manager extends Person {
    private String loginID;
    private String password;

    public Manager(String idCard, String fullName, String gender, String loginID, String password) {
        super(idCard, fullName, gender);
        this.loginID = loginID;
        this.password = password;
    }

    public String getLoginID() {
        return loginID;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
