import java.util.Scanner;

public class AuthForOwner {
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    protected boolean auth() {
        System.out.println("Enter Password: ");
        Scanner scan = new Scanner(System.in);
        String password = scan.nextLine();
        if (password.equals(this.password)) {
            return true;
        }
        return false;
    }
}
