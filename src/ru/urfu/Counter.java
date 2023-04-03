package ru.urfu;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry.*;
import static java.util.stream.Collectors.*;

public class Counter {

    public static Map<String, Long> countWords(List<String> words){
        Map<String, Long> wordCount = words.stream() // Создаем последовательный поток из words
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting())); // Собираем в Map, где ключ - слово, значение - частота
        return sortWords(wordCount); // Сортируем

    }

    private static Map<String, Long> sortWords(Map<String, Long> countedWords) {
        return countedWords.entrySet() // Представляем в виде коллекции
                .stream() // Создаем поток из данной коллекции
                .sorted(comparingByValue(Comparator.reverseOrder())) // Сортируем по значениям в обратном порядке
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e2,
                        LinkedHashMap::new)); // Собираем в LinkedHashMap
    }
}
