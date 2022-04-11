package com.example.breweriesapp.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvParser {

    public static List<String[]> makeArrayFromFile(){
        List<String> arrayFromFile = new ArrayList<>();
        List<String[]> splitArrays = new ArrayList<>();
        Path filePath = Paths.get("src/main/resources/breweries_usa.csv");

        try (Stream<String> lines = Files.lines(filePath)) {
            arrayFromFile = lines.toList();

//            List<BreweryEntity> breweryEntities = lines.map(l -> BreweryEntity.map(l.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"))).toList();
////            stream.map(arr -> arr[0])
//            int aa = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //List<BreweryEntity> entities = stream.map(arr -> BreweryEntity.map(arr)).collect(Collectors.toList());

//        String s1 = arrayFromFile.get(21);
//        String[] split = s1.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        int errorNumbers = 0;
        List<Integer> fieldsNumbers = new ArrayList<>();
        for (int i = 0; i < 17824; i++) {
            try {
                String[] s = arrayFromFile.get(i).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
//                String[] s = arrayFromFile.get(i).split(",((?<=\\})(?=\\{))");
                splitArrays.add(s);
            } catch (StackOverflowError e) {
                errorNumbers++;
            }
        }

        List<BreweryEntity> entities1 = splitArrays.stream().map(BreweryEntity::map).toList();
        //arrayFromFile.forEach(line -> splitArrays.add(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")));

        String sample0 = arrayFromFile.get(0);
        String sample6 = arrayFromFile.get(6);
        String sample32 = arrayFromFile.get(32);
        String[] convertedLines0 = sample0.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        String[] convertedLines6 = sample6.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        String[] convertedLines32 = sample32.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
//        //get particular lines from file by separating them by sign of new line (\n)
//        try {
//            particularLines = Files.readString(path).split("\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //fill array line by line, and separate each line into another list, by "," sign
//        for (int i = 0; i < particularLines.length; i++) {
//            String[] newString = particularLines[i].split("~");
//            arrayFromFile.add(particularLines[i].split("~"));
//        }
//        return arrayFromFile;


        return new ArrayList<String[]>();
    }

}
