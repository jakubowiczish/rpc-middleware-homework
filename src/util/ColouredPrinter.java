package util;


import static util.ConsoleColor.RESET;

public class ColouredPrinter {

    public static void printColoured(final String message, final ConsoleColor color) {
        System.out.print(color + message + RESET);
    }

    public static void printlnColoured(final String message, final ConsoleColor color) {
        System.out.println(color + message + RESET);
    }
}
