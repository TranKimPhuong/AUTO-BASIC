package nab.codility.interviwe;

public class java {

    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    public static void main(String[] args) {
        String email = "john123@gmail.com";
        System.out.println("The E-mail ID is: " + email);
        System.out.println("Is the above E-mail ID valid? " + isValid(email));
    }
}
