package com.example.breweriesapp.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class CsvParser {

    private static final Integer DESIRED_NUMBER_OF_COLUMNS_IN_FILE = 15;
    private static final String PATH_TO_FILE = "src/main/resources/breweries_usa.csv";

    private CsvParser() {
    }

    public static List<String[]> getFile() {
        List<String[]> uncheckedFile = makeArrayFromFile();
        checkFile(uncheckedFile);
        return uncheckedFile;
    }

    private static List<String[]> makeArrayFromFile() {
        Path filePath = Paths.get(PATH_TO_FILE);
        List<String[]> parsedFile = null;
        try {
            Reader reader = Files.newBufferedReader(filePath);
            parsedFile = oneByOne(reader);
        } catch (IOException | CsvException e) {
            System.out.println("Cannot read the file: " + filePath + "\n" + e.getMessage());
        }
        return parsedFile;
    }

    public static List<String[]> oneByOne(Reader reader) throws IOException, CsvException {
        List<String[]> list = new LinkedList<>();
        CSVReader csvReader = new CSVReader(reader);
        csvReader.skip(1);
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            list.add(line);
        }
        reader.close();
        csvReader.close();
        return list;
    }

    private static void checkFile(List<String[]> parsedFile) {
        checkIfParsedFileNotNull(parsedFile);
        int countOfRecordWithPossibleParseError = checkIfNumberOfColumnsCorrect(parsedFile);
        if (countOfRecordWithPossibleParseError != 0) {
            throw new RuntimeException(countOfRecordWithPossibleParseError + " records have fewer columns than desired");
        }
    }

    private static int checkIfNumberOfColumnsCorrect(List<String[]> parsedFile) {
        int errorsCounter = 0;
        for (String[] rowByColumn : parsedFile) {
            if (rowByColumn.length != DESIRED_NUMBER_OF_COLUMNS_IN_FILE) {
                errorsCounter++;
            }
        }
        return errorsCounter;
    }

    private static void checkIfParsedFileNotNull(List<String[]> parsedFile) {
        if (parsedFile == null) {
            throw new NullPointerException("ParsedFile is NULL!");
        }
    }
}