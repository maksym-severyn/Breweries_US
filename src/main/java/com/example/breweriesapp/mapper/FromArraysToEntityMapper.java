package com.example.breweriesapp.mapper;

import com.example.breweriesapp.model.BreweryEntity;
import com.example.breweriesapp.model.WorkingDay;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Component
public class FromArraysToEntityMapper {

    public BreweryEntity map(String[] fieldsInArray) {
        BreweryEntity entity = new BreweryEntity();
        entity.setId(emptyFieldConverter(fieldsInArray[0]));
        entity.setAddress(emptyFieldConverter(fieldsInArray[1]));
        entity.setCategories(Arrays.asList(fieldsInArray[2].split(",")));
        entity.setCity(emptyFieldConverter(fieldsInArray[3]));
        entity.setCountry(emptyFieldConverter(fieldsInArray[4]));
        try {
            entity.setHours(workingDayConverter(emptyFieldConverter(fieldsInArray[5])));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot convert working days from file into entity");
        }
        entity.setKeys(emptyFieldConverter(fieldsInArray[6]));
        entity.setLatitude(parseDouble(emptyFieldConverter(fieldsInArray[7])));
        entity.setLongitude(parseDouble(emptyFieldConverter(fieldsInArray[8])));
        entity.setMenus(emptyFieldConverter(fieldsInArray[9]));
        entity.setName(emptyFieldConverter(fieldsInArray[10]));
        entity.setPostalCode(emptyFieldConverter(fieldsInArray[11]));
        entity.setProvince(emptyFieldConverter(fieldsInArray[12]));
        entity.setTwitter(emptyFieldConverter(fieldsInArray[13]));
        entity.setWebsites(emptyFieldConverter(fieldsInArray[14]));
        return entity;
    }

    private List<WorkingDay> workingDayConverter(String flatJsonRecord) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        if (flatJsonRecord == null || flatJsonRecord.isEmpty()) {
            return Collections.emptyList();
        }
        return om.readValue(flatJsonRecord, new TypeReference<ArrayList<WorkingDay>>() {});
    }

    private String emptyFieldConverter(String originalString) {
        if (originalString.isEmpty() || originalString.isBlank()) {
            return null;
        } else {
            return originalString;
        }
    }

    private Double parseDouble(String originalString) {
        if (originalString == null || originalString.isEmpty() || originalString.isBlank()) {
            return null;
        } else {
            return Double.parseDouble(originalString);
        }
    }
}