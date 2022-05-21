package com.example.breweriesapp;

import com.example.breweriesapp.mapper.FromArraysToEntityMapper;
import com.example.breweriesapp.model.BreweryEntity;
import com.example.breweriesapp.model.WorkingDay;
import com.example.breweriesapp.util.CsvParser;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BreweryService {

    private final FromArraysToEntityMapper fromArraysToEntityMapper;
    private final BreweriesRepo breweriesRepo;

    public BreweryService(FromArraysToEntityMapper fromArraysToEntityMapper, BreweriesRepo breweriesRepo) {
        this.fromArraysToEntityMapper = fromArraysToEntityMapper;
        this.breweriesRepo = breweriesRepo;
    }

    public Map<String, Integer> getNumbersOfBreweriesInEachState() {
        Map<String, Integer> numbersOfBreweriesInEachState = new HashMap<>();
        breweriesRepo.numbersOfBreweriesInEachState()
                .forEach(row -> numbersOfBreweriesInEachState.put(row[0].toString(), Integer.valueOf(row[1].toString())));
        return numbersOfBreweriesInEachState;
    }

    public List<String> getTopCitiesByCountOfBreweries(int numberOfTop) {
        return breweriesRepo.topCitiesByCountOfBreweries(numberOfTop);
    }

    public Long countAllByWebsitesExists() {
        return breweriesRepo.countAllByWebsitesNotNull();
    }

    public Long countBreweriesLocatedInDelawareAndOfferTacos() {
        return breweriesRepo.countBreweriesLocatedInDelawareAndOfferTacos();
    }

    public Map<String, String> getPercentageBreweriesOffersWineByState() {
        Map<String, String> percentageMapByState = new HashMap<>();
        breweriesRepo.percentageBreweriesOffersWineByState()
                .forEach(stateRow -> percentageMapByState.put(stateRow[0], (stateRow[3])));
        return percentageMapByState;
    }

    public Integer countDuplicatedBreweries() {
        return breweriesRepo.findDuplicatedBreweries().size();
    }

    public void initialDataLoading() {
        if (breweriesRepo.count() < 1) {
            System.out.println("Initialize data not needed - DB is not empty!");
            return;
        }
        System.out.println("Data getting from CSV...");
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
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        executorService.submit(() -> breweriesRepo.saveAll(breweryEntities));
        executorService.shutdown();
        System.out.println("Data loaded to DB!");
    }
}