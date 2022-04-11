package com.example.breweriesapp;

import com.example.breweriesapp.util.CsvParser;
import com.example.breweriesapp.util.CsvReaderExamples;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BreweriesAppApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BreweriesAppApplication.class, args);
        System.out.println("Hi!");
//        List<String[]> strings1 = CsvParser.makeArrayFromFile();
        List<String[]> strings = CsvReaderExamples.makeArrayFromFile();
    }

}
