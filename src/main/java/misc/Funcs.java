package misc;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class Funcs {

    public String firstLetterCapitalize(String str) {
        if (!str.isEmpty()) return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        else return "";
    }

    public String agePostfix(int age) {
        //определяем падеж года
        int lastDigit = age % 10;
        int lastTwoDigits = age % 100;
        if (lastDigit == 0 || lastDigit >= 5 || (lastTwoDigits >= 11 && lastTwoDigits <= 14)) return "лет";
        else if (lastDigit == 1) return "год";
        else return "года";
    }


    public String inputWithRegexValidate(String regexRule, String questionText, String errorText) {

        for (; ; ) {
            Scanner console = new Scanner(System.in);
            System.out.println("\u001b[36;1m> " + questionText + "\u001B[0m");
            System.out.println("\t \u001b[3m(вернуться в главное меню: \u001b[1mcancel\u001B[0m)");
            String input = console.nextLine().trim();
            if (input.equals("cancel")) return null;
            if (input.matches(regexRule)) return input;
            else if (!errorText.isEmpty()) System.out.println(errorText);
        }
    }


}

