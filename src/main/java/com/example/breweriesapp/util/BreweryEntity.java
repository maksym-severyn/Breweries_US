package com.example.breweriesapp.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BreweryEntity {

    private String id;
    private String address;
    private String categories;
    private String city;
    private String country;
    private String hours;
    private String keys;
    private String latitude;
    private String longitude;
    private String menus;
    private String name;
    private String postalCode;
    private String province;
    private String twitter;
    private String websites;

    public static BreweryEntity map(String[] fieldsInArray) {
        BreweryEntity entity = new BreweryEntity();
        entity.setId(fieldsInArray[0]);
        entity.setAddress(fieldsInArray[1]);
        entity.setCategories(fieldsInArray[2]);
        return entity;
    }

}
