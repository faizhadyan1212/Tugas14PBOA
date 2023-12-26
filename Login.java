import java.util.Scanner;
import java.util.Random;
class Login {

    private static final String USERNAME = "hanabi";
    private static final String PASSWORD = "Sparkle";
    private static final int CAPTCHA_LENGTH = 5;
    
    static boolean login(Scanner scanner) {
        System.out.println("SELAMAT DATANG DI FONTAINE MINIMARKET");
        System.out.println("===============================");
        System.out.println("Log in");

        System.out.print("Username: ");
        String username = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        System.out.print("Kode Captcha: ");
        String captcha = generateCaptcha(CAPTCHA_LENGTH);
        System.out.println("Captcha: " + captcha);

        System.out.print("Entry Captcha: ");
        String entryCaptcha = scanner.next();

        if (username.equals(USERNAME) && password.equals(PASSWORD) && entryCaptcha.equals(captcha)) {
            System.out.println("Login berhasil!");
           
            return true;
        } else {
            System.out.println("Login gagal. Program berhenti.");
            return false;
        }
    }

    private static String generateCaptcha(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }

        return captcha.toString();
    }
}