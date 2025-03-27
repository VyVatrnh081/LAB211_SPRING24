/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Validation {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Validation() {
    }

    public static int inputInt(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.err.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.err.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    public static double inputDouble(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            double min, double max) {
        do {
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.err.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.err.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    public static String inputString(String messageInfo, String messageError,
            final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine();
            if (str.matches(REGEX)) {
                return str;
            }
            System.err.println(messageError);
        } while (true);
    }
}
