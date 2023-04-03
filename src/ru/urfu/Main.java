package ru.urfu;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.*;

/*
    Для сопоставления слов и их частот был выбран LinkedHashMap, так как он позволяет
    хранить пары ключ-значение, что очень удобно для подсчёта частот слов, ведь слова
    являются множеством
    Сложность O(n) для самого подсчёта частот и O(n log n) для сортировки, т.к. сортировка
    делегируется Arrays.sort()
 */

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = getFileName();
        String text = FileReader.readFile(fileName);

        String[] wordsArray = text.split("[\\p{Punct}\\s-\\w\\p{Pd}\\u00A0]+");

        Map<String, Long> result = Counter.countWords(Arrays.stream(wordsArray).toList());

        System.out.println("top 10 most used:");
        System.out.println(getTop10(result));
        System.out.println("top 10 least used:");
        System.out.println(getBottom10(result));
    }

    private static String getFileName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("insert filename: ");
        return scanner.nextLine();
    }

    private static List<Map.Entry<String, Long>> getTop10(Map<String, Long> sortedMap){
        return sortedMap.entrySet().stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    private static List<Map.Entry<String, Long>> getBottom10(Map<String, Long> sortedMap){
        return sortedMap.entrySet().stream()
                .sorted(comparingByValue(Comparator.naturalOrder()))
                .limit(10)
                .collect(Collectors.toList());
    }
}
