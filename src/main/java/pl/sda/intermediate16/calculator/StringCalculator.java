package pl.sda.intermediate16.calculator;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {


    public static Integer adding(String text) {
        // można zrobić isBlank i wtdy sprawdzi wszystkie przypadki null i "" i "  " zamiast tego wszystkie
        // trzy przypadki osobno
        if (StringUtils.isBlank(text)) {
            return 0;
        }

        if (text.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.*)\n(.*)");
            Matcher matcher = pattern.matcher(text);
            matcher.matches();
            String delimiters = matcher.group(1);
            delimiters = delimiters
                    .replaceAll("[\\[|\\]]+", " ")
                    .trim()
                    .replaceAll("\\s", "|");
            String[] splitted = text.split("\n");
            return tokenizeAndSum(splitted[1], delimiters);
        }
        return tokenizeAndSum(text, ",|\n");
    }

    private static Integer tokenizeAndSum(String text, String regex) {
        //metoda dzieląca stringa według ustalonego regex na integery i wrzuca je do listy
        List<Integer> integerList = Arrays.stream(text.split(regex))
                .map(e -> Integer.valueOf(e.trim()))
                .collect(Collectors.toList());

        List<Integer> negativeValues = integerList.stream()
                // metoda odrzucająca wartości minusowe do inne listy i wypisująca błąd jeśli lista nie jest pusta
                .filter(i -> i < 0)
                .collect(Collectors.toList());

        if (!negativeValues.isEmpty()) {
            // nie robić size bo jk jest dużo elementów to to długo będzie trwało
            throw new NegativeNumberFoundException("Tak nie można! " + negativeValues);
        }

        return integerList.stream()
                // metoda ignorująca liczby większe niż 1000
                .filter(a -> a <= 1000)
                .reduce((a, b) -> a + b)
                //tak je redukujemy z dwóch czy ile tam ich dostaliśmy do jednego
                .orElseGet(() -> superHardLongAndSourcesNeedingMethod());
    }

    private static Integer superHardLongAndSourcesNeedingMethod() {
        // metoda spowalniająca
        System.out.println("NIE OK");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
