package com.example.breweriesapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = BreweryEntity.TABLE_NAME)
public class BreweryEntity {

    public static final String TABLE_NAME = "brewery_entity";
    public static final String COLUMN_PREFIX = "b_";

    @Id
    @Column(name = COLUMN_PREFIX + "id")
    private String id;
    @Column(name = COLUMN_PREFIX + "address")
    private String address;
    @Column(name = COLUMN_PREFIX + "categories")
    @ElementCollection
    private List<String> categories;
    @Column(name = COLUMN_PREFIX + "city")
    private String city;
    @Column(name = COLUMN_PREFIX + "country")
    private String country;
    @Column(name = COLUMN_PREFIX + "hours")
    @OneToMany(mappedBy = "breweryEntity", fetch = FetchType.LAZY)
    private List<WorkingDay> hours;
    @Column(name = COLUMN_PREFIX + "keys")
    private String keys;
    @Column(name = COLUMN_PREFIX + "latitude")
    private Double latitude;
    @Column(name = COLUMN_PREFIX + "longitude")
    private Double longitude;
    @Column(name = COLUMN_PREFIX + "menus")
    private String menus;
    @Column(name = COLUMN_PREFIX + "name")
    private String name;
    @Column(name = COLUMN_PREFIX + "postalCode")
    private String postalCode;
    @Column(name = COLUMN_PREFIX + "province")
    private String province;
    @Column(name = COLUMN_PREFIX + "twitter")
    private String twitter;
    @Column(name = COLUMN_PREFIX + "websites")
    private String websites;
    @Column(name = COLUMN_PREFIX + "state")
    private String state;


}