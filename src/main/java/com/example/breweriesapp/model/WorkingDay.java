package com.example.breweriesapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = WorkingDay.TABLE_NAME)
public class WorkingDay {

    public static final String TABLE_NAME = "working_day";
    public static final String COLUMN_PREFIX = "wd_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "brewery_entity_id")
    private BreweryEntity breweryEntity;
    private String hour;
    private String day;
}