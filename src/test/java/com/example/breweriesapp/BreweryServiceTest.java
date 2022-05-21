package com.example.breweriesapp;

import com.example.breweriesapp.mapper.FromArraysToEntityMapper;
import com.example.breweriesapp.model.BreweryEntity;
import com.example.breweriesapp.model.WorkingDay;
import com.example.breweriesapp.util.CsvParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BreweryServiceTest {

    @Mock(lenient = true)
    private BreweriesRepo breweriesRepo;
    @Mock(lenient = true)
    private FromArraysToEntityMapper mapper;
    @InjectMocks
    private BreweryService breweryService;
    @Captor
    private ArgumentCaptor<Integer> numberCaptor;

    @Test
    void getNumbersOfBreweriesInEachState_returnsNumberOfBreweriesInEachState() {
        // given
        List<Object[]> breweriesByStates = new ArrayList<>();
        breweriesByStates.add(new Object[]{"Alabama", "915"});
        breweriesByStates.add(new Object[]{"Alaska", "199"});
        Map<String, Integer> numbersOfBreweriesInEachStateInMap = Map.ofEntries(
                Map.entry("Alabama", 915),
                Map.entry("Alaska", 199)
        );
        given(breweriesRepo.numbersOfBreweriesInEachState()).willReturn(breweriesByStates);
        // when
        Map<String, Integer> numbersOfBreweriesInEachState = breweryService.getNumbersOfBreweriesInEachState();
        // then
        assertThat(numbersOfBreweriesInEachState).isEqualTo(numbersOfBreweriesInEachStateInMap);
        verify(breweriesRepo).numbersOfBreweriesInEachState();
    }

    @Test
    void getTopCitiesByCountOfBreweries_returnsTopCitiesByCountOfBreweries_givenNumbersOfCities() {
        // given
        int numberOfTop = 2;
        List<String> cities = List.of("Seattle", "Portland");
        given(breweriesRepo.topCitiesByCountOfBreweries(anyInt())).willReturn(cities);
        // when
        List<String> citiesByCountOfBreweries = breweryService.getTopCitiesByCountOfBreweries(numberOfTop);
        // then
        assertThat(citiesByCountOfBreweries).isSameAs(cities);
        verify(breweriesRepo).topCitiesByCountOfBreweries(numberOfTop);
    }

    @Test
    void getTopCitiesByCountOfBreweries_returnsEmptyList_noDataInDB() {
        // given
        int numberOfTop = 2;
        List<String> cities = List.of("Seattle", "Portland");
        given(breweriesRepo.topCitiesByCountOfBreweries(numberCaptor.capture())).willReturn(Collections.emptyList());
        // when
        List<String> citiesByCountOfBreweries = breweryService.getTopCitiesByCountOfBreweries(numberOfTop);
        // then
        assertThat(citiesByCountOfBreweries).isEmpty();
        assertThat(numberCaptor.getValue()).isSameAs(numberOfTop);
        verify(breweriesRepo).topCitiesByCountOfBreweries(numberOfTop);
    }

    @Test
    void getTopCitiesByCountOfBreweries_throwsException_givenNullAsParameter() {
        // given
        Integer numberOfTop = null;
        // when
        Throwable thrown = catchThrowable(() -> breweryService.getTopCitiesByCountOfBreweries(numberOfTop));
        // then
        assertThat(thrown).isExactlyInstanceOf(NullPointerException.class);
        then(breweriesRepo).shouldHaveNoInteractions();
    }

    @Test
    void countAllByWebsitesExists_returnCountOfBreweriesWithWebsiteLink() {
        // given
        Long returnedNumber = 11L;
        given(breweriesRepo.countAllByWebsitesNotNull()).willReturn(returnedNumber);
        // when
        Long countAllByWebsitesExists = breweryService.countAllByWebsitesExists();
        // then
        assertThat(countAllByWebsitesExists).isSameAs(returnedNumber);
        verify(breweriesRepo).countAllByWebsitesNotNull();
    }

    @Test
    void countBreweriesLocatedInDelawareAndOfferTacos_returnCountBreweriesLocatedInDelawareAndOfferTacos() {
        // given
        Long returnedNumber = 11L;
        given(breweriesRepo.countBreweriesLocatedInDelawareAndOfferTacos()).willReturn(returnedNumber);
        // when
        Long countAllByWebsitesExists = breweryService.countBreweriesLocatedInDelawareAndOfferTacos();
        // then
        assertThat(countAllByWebsitesExists).isSameAs(returnedNumber);
        verify(breweriesRepo).countBreweriesLocatedInDelawareAndOfferTacos();
    }

    @Test
    void getPercentageBreweriesOffersWineByState_returnPercentageBreweriesOffersWineByState() {
        // given
        List<String[]> listWithPercentageBreweriesOffersWineByState = new ArrayList<>();
        listWithPercentageBreweriesOffersWineByState.add(new String[]{"Nevada", "194", "12", "6.1856"});
        Map<String, String> mapWithStateAndPercentagesBreweriesOffersWineByState = Map.ofEntries(
                Map.entry("Nevada", "6.1856")
        );
        given(breweriesRepo.percentageBreweriesOffersWineByState()).willReturn(listWithPercentageBreweriesOffersWineByState);
        // when
        Map<String, String> percentageBreweriesOffersWineByState = breweryService.getPercentageBreweriesOffersWineByState();
        // then
        assertThat(percentageBreweriesOffersWineByState).isEqualTo(mapWithStateAndPercentagesBreweriesOffersWineByState);
        verify(breweriesRepo).percentageBreweriesOffersWineByState();
    }

    @Test
    void countDuplicatedBreweries_returnCountDuplicatedBreweries() {
        // given
        List<String> example = List.of("Example1", "Example2");
        given(breweriesRepo.findDuplicatedBreweries()).willReturn(example);
        // when
        Integer countDuplicatedBreweries = breweryService.countDuplicatedBreweries();
        // then
        assertThat(countDuplicatedBreweries).isEqualTo(example.size());
        verify(breweriesRepo).findDuplicatedBreweries();
    }

    @Test
    void initialDataLoading_doNothing_DBNotEmpty() {
        // given
        BreweryEntity brewery1 = new BreweryEntity();
        brewery1.setId("Axuo6Xhx");
        brewery1.setCountry("US");
        brewery1.setCity("New York");
        BreweryEntity brewery2 = new BreweryEntity();
        brewery2.setId("kxcviunw");
        brewery2.setCountry("US");
        brewery2.setCity("Denver");
        List<BreweryEntity> breweryEntityList = List.of(brewery1, brewery2);
        given(breweriesRepo.count()).willReturn(1L);
        given(mapper.map(any())).willReturn(brewery1);
        given(breweriesRepo.saveAll(any())).willReturn(breweryEntityList);
        // when
        breweryService.initialDataLoading();
        // then
        verifyNoInteractions(mapper);
        verify(breweriesRepo, never()).saveAll(any());
        verify(breweriesRepo).count();
    }

    @Test
    void initialDataLoading_initializeDBWithData_DBIsEmpty() {
        // given
        List<WorkingDay> hours = new ArrayList<>();
        WorkingDay day = new WorkingDay();
        day.setDay("Mon");
        day.setHour("07:00 - 21:00");
        hours.add(day);
        BreweryEntity brewery1 = new BreweryEntity();
        brewery1.setId("Axuo6Xhx");
        brewery1.setCountry("US");
        brewery1.setCity("New York");
        brewery1.setHours(hours);
        BreweryEntity brewery2 = new BreweryEntity();
        brewery2.setId("kxcviunw");
        brewery2.setCountry("US");
        brewery2.setCity("Denver");
        brewery2.setHours(hours);
        List<BreweryEntity> breweryEntityList = List.of(brewery1, brewery2);
        given(breweriesRepo.count()).willReturn(0L);
        given(mapper.map(any())).willReturn(brewery1);
        given(breweriesRepo.saveAll(any())).willReturn(breweryEntityList);
        // when
        breweryService.initialDataLoading();
        // then
        verify(mapper, times(CsvParser.getFile().size())).map(any());
        verify(breweriesRepo, times(1)).saveAll(any());
    }
}