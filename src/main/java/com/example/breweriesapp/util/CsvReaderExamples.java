package com.example.breweriesapp.util;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CsvReaderExamples {

    public static List<String[]> makeArrayFromFile() throws Exception {
        List<String> arrayFromFile = new ArrayList<>();
        String[] particularLines = new String[0];
        List<String[]> splitArrays = new ArrayList<>();
        Stream<String[]> stream = null;
        Path filePath = Paths.get("src/main/resources/breweries_usa.csv");

        Reader reader = Files.newBufferedReader(filePath);

        // DOESN'T WORK CORRECTLY!!
        List<String[]> strings = oneByOne(reader);

        List<Integer> errorParsedIndecec = new ArrayList<>();
        for (String[] stri : strings){
            if (stri.length == 15) {
                errorParsedIndecec.add(strings.indexOf(stri));
            }
        }

        return new ArrayList<String[]>();
    }

    public static List<String[]> readAll(Reader reader) throws Exception {
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = new ArrayList<>();
        list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }

    public String readAllExample() throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource("csv/twoColumn.csv").toURI()));
        return readAll(reader).toString();
    }

    public static List<String[]> oneByOne(Reader reader) throws Exception {
        List<String[]> list = new ArrayList<>();
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        int counterLine = -1;
        while ((line = csvReader.readNext()) != null) {
            list.add(line);
            counterLine++;
            if (counterLine == 15005) {
                String[] strings = list.get(counterLine);
            }
        }
        reader.close();
        csvReader.close();
        return list;
    }

    public String oneByOneExample() throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource("csv/twoColumn.csv").toURI()));
        return oneByOne(reader).toString();
    }



}
