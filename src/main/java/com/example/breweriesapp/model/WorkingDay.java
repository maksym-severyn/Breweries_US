package com.example.breweriesapp.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = WorkingDay.TABLE_NAME)
public class WorkingDay {

    public static final String TABLE_NAME = "working_day";
    public static final String COLUMN_PREFIX = "wd_";

    @Id
    @Column(name = COLUMN_PREFIX + "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "owned_brewery_entity_id")
    private BreweryEntity ownedBreweryEntity;
    @Column(name = COLUMN_PREFIX + "hour")
    private String hour;
    @Column(name = COLUMN_PREFIX + "day")
    private String day;
}