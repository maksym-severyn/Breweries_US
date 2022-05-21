package com.example.breweriesapp.service;

import java.util.List;
import java.util.Map;

public interface BreweryService {
    Map<String, Integer> getNumbersOfBreweriesInEachState();
    List<String> getTopCitiesByCountOfBreweries(int numberOfTop);
    Long countAllByWebsitesExists();
    Long countBreweriesLocatedInDelawareAndOfferTacos();
    Map<String, String> getPercentageBreweriesOffersWineByState();
    Integer countDuplicatedBreweries();
    void initialDataLoading();
}