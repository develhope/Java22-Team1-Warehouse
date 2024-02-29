import java.util.Scanner;

public class GetValidInput {
    private static Scanner sc = new Scanner(System.in);
    public static String getString(String prompt, int maxLength) {
        String input;
        do {
            System.out.println(prompt);
            input = sc.nextLine().trim();
            if (input.length() > maxLength) {
                System.out.println("La lunghezza massima consentita è " + maxLength + " caratteri.");
            } else {
                return input;
            }
        } while (true);
    }


    // Prende un input double e lo valida prima di ritornarlo

    public static double getDouble(String prompt) {
        do {
            System.out.println(prompt);
            String input = sc.nextLine().trim();
            if (input.matches(("[0-9]+(.[0-9]+)?"))) {
                try {
                    return Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero valido.");
                }
            } else {
                System.out.println("Input non valido. Inserisci un numero valido.");
            }
        } while (true);
    }

    public static Long getLong(String prompt) {
        do {
            System.out.println(prompt);
            String input = sc.nextLine().trim();
            if (input.matches(("[0-9]+(.[0-9]+)?"))) {
                try {
                    return Long.parseLong(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero valido.");
                }
            } else {
                System.out.println("Input non valido. Inserisci un numero valido.");
                break;
            }
        } while (true);
        return null;
    }

    // Prende un input integer e lo valida prima di ritornarlo
    public static int getInteger(String prompt) {
        do {
            System.out.println(prompt);
            String input = sc.nextLine().trim();
            if (input.matches("[0-9]+")) {
                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero intero valido.");
                }
            } else {
                System.out.println("Input non valido. Inserisci un numero intero valido.");
            }
        } while (true);
    }
}
