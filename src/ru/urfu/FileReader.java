package ru.urfu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    public static String readFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line = scanner.nextLine();
        while (scanner.hasNextLine()) {
            stringBuilder.append(line);
            line = scanner.nextLine();
        }
        return stringBuilder.toString();
    }
}