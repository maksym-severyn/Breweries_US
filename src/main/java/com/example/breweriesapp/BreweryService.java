package com.example.breweriesapp;

import com.example.breweriesapp.mapper.FromArraysToEntityMapper;
import com.example.breweriesapp.model.BreweryEntity;
import com.example.breweriesapp.model.WorkingDay;
import com.example.breweriesapp.util.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreweryService {

    private final FromArraysToEntityMapper fromArraysToEntityMapper;
    private final BreweriesRepo breweriesRepo;

    @Autowired
    public BreweryService(FromArraysToEntityMapper fromArraysToEntityMapper, BreweriesRepo breweriesRepo) {
        this.fromArraysToEntityMapper = fromArraysToEntityMapper;
        this.breweriesRepo = breweriesRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialDataLoading() {
        if (breweriesRepo.count() > 1) {
            System.out.println("Initialize data not needed - DB is not empty.");
            return;
        }
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
    }
}