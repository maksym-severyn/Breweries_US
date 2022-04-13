package com.example.breweriesapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BreweryDisplay {

    private final BreweryService breweryService;
    private static final String VIEW_SEPARATOR_BOLD = "=======================================================================";
    private static final String VIEW_SEPARATOR_THIN = "------------------------------";
    private static final String VIEW_SEPARATOR_SMALL = " ----- ";
    private static final Integer DESIRED_NUMBER_OF_TOP_CITIES = 5;

    @Autowired
    public BreweryDisplay(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        initialization();
        displayNumberOfBreweriesInEachState();
        displayTopCitiesForBreweries();
        displayCountOfBreweriesWithWebsiteLink();
        displayCountOfBreweriesLocatedInDelawareAndOfferTacos();
        displayPercentageBreweriesOffersWineByState();
        displayNumberOfDuplicatedBreweries();
        System.out.println("\nThe end, thank you!\n");
        System.exit(0);
    }

    private void initialization() {
        System.out.println(VIEW_SEPARATOR_BOLD);
        System.out.println("WELCOME!");
        breweryService.initialDataLoading();
        System.out.println(VIEW_SEPARATOR_BOLD);
    }

    private void displayNumberOfBreweriesInEachState() {
        System.out.println("\n2) a. What is the number of breweries in each state?\n");
        System.out.println("STATE ----- COUNT OF BREWERIES");
        System.out.println(VIEW_SEPARATOR_THIN);
        for (Map.Entry<String, Integer> entry : breweryService.getNumbersOfBreweriesInEachState().entrySet()) {
            System.out.println(entry.getKey() + VIEW_SEPARATOR_SMALL + entry.getValue());
        }
        System.out.println(VIEW_SEPARATOR_BOLD);
    }

    private void displayTopCitiesForBreweries() {
        System.out.println("\n2) b. What are the top cities for breweries?\n");
        System.out.println("TOP ----- CITY");
        List<String> topCitiesByCountOfBreweries = breweryService.getTopCitiesByCountOfBreweries(DESIRED_NUMBER_OF_TOP_CITIES);
        for (int i = 0; i < topCitiesByCountOfBreweries.size(); i++) {
            System.out.println(i + 1 + VIEW_SEPARATOR_SMALL + topCitiesByCountOfBreweries.get(i));
        }
        System.out.println(VIEW_SEPARATOR_BOLD);
    }

    private void displayCountOfBreweriesWithWebsiteLink() {
        System.out.println("\n2) c. How many breweries have the link to the website?\n");
        System.out.println("Count of breweries, that have the link to the website: " + breweryService.countAllByWebsitesExists());
        System.out.println(VIEW_SEPARATOR_BOLD);
    }

    private void displayCountOfBreweriesLocatedInDelawareAndOfferTacos() {
        System.out.println("\n2) d. How many breweries located in Delaware state also offer tacos?\n");
        System.out.println("Count of breweries located in Delaware state also offer tacos: " + breweryService.countBreweriesLocatedInDelawareAndOfferTacos());
        System.out.println(VIEW_SEPARATOR_BOLD);
    }

    private void displayPercentageBreweriesOffersWineByState() {
        System.out.println("\n2) e. What percentage of breweries in each state offers wine?\n");
        System.out.println("STATE ----- PERCENTAGE");
        System.out.println(VIEW_SEPARATOR_THIN);
        Map<String, String> percentageMapByState = breweryService.getPercentageBreweriesOffersWineByState();
        for (Map.Entry<String, String> entry : percentageMapByState.entrySet()) {
            System.out.println(entry.getKey() + VIEW_SEPARATOR_SMALL + entry.getValue() + "%");
        }
        System.out.println(VIEW_SEPARATOR_BOLD);
    }

    private void displayNumberOfDuplicatedBreweries() {
        System.out.println("\n3. Optional* Find the duplicated records. How many breweries are listed more than once?\n");
        System.out.println("Count of breweries listed more than once: " + breweryService.countDuplicatedBreweries());
        System.out.println(VIEW_SEPARATOR_BOLD);
    }
}