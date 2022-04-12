package com.example.breweriesapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BreweryEntity {

    private String id;
    private String address;
    private List<String> categories;
    private String city;
    private String country;
    private List<WorkingDay> hours;
    private String keys;
    private Double latitude;
    private Double longitude;
    private String menus;
    private String name;
    private String postalCode;
    private String province;
    private String twitter;
    private String websites;
    private String state;


}
