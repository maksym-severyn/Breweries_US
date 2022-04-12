package com.example.breweriesapp;

import com.example.breweriesapp.mapper.FromArraysToEntityMapper;
import com.example.breweriesapp.model.BreweryEntity;
import com.example.breweriesapp.model.WorkingDay;
import com.example.breweriesapp.util.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BreweryService {

    private static final Integer DESIRED_NUMBER_OF_TOP_CITIES = 5;
    private final FromArraysToEntityMapper fromArraysToEntityMapper;
    private final BreweriesRepo breweriesRepo;

    @Autowired
    public BreweryService(FromArraysToEntityMapper fromArraysToEntityMapper, BreweriesRepo breweriesRepo) {
        this.fromArraysToEntityMapper = fromArraysToEntityMapper;
        this.breweriesRepo = breweriesRepo;
    }

    public void run() {
        Map<String, Integer> numbersOfBreweriesInEachState = numbersOfBreweriesInEachState();
        Set<String> topCities = top5CitiesByCountOfBreweries(DESIRED_NUMBER_OF_TOP_CITIES);
    }

    public Set<String> top5CitiesByCountOfBreweries(int numberOfTop) {
        Set<String> topCities = breweriesRepo.topCitiesByCountOfBreweries(numberOfTop);
        return topCities;
    }

    public Map<String, Integer> numbersOfBreweriesInEachState() {
        List<Object[]> stringIntegerMap = breweriesRepo.numbersOfBreweriesInEachState();
        Map<String, Integer> numbersOfBreweriesInEachState = new HashMap<>();
        for (Object[] row : stringIntegerMap){
            numbersOfBreweriesInEachState.put(row[0].toString(), Integer.valueOf(row[1].toString()));
        }
        return numbersOfBreweriesInEachState;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialDataLoading() {
        if (breweriesRepo.count() > 1) {
            System.out.println("Initialize data not needed - DB is not empty.");
            run();
        } else {
            List<String[]> strings = CsvParser.getFile();
            List<BreweryEntity> breweryEntities = strings
                    .stream()
                    .map(fromArraysToEntityMapper::map).toList();

            Long manualId = 1L;
            for (BreweryEntity breweryEntity : breweryEntities) {
                List<WorkingDay> hours = breweryEntity.getHours();
                for (WorkingDay day : hours) {
                    day.setId(manualId);
                    day.setOwnedBreweryEntity(breweryEntity);
                    manualId++;
                }
            }
            System.out.println("Data started to loading to DB...");
            breweriesRepo.saveAll(breweryEntities);
            System.out.println("Data loaded to DB!");
            run();
        }
    }
}