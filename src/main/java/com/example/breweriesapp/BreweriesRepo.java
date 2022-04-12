package com.example.breweriesapp;

import com.example.breweriesapp.model.BreweryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BreweriesRepo extends JpaRepository<BreweryEntity, String> {

    @Query(value = "select distinct s.name as state_global, count(b.b_id) as count_of_breweries\n" +
            "from brewery_entity b\n" +
            "left join cities on cities.name = b.b_city\n" +
            "left join states s on cities.state_id = s.id\n" +
            "where s.country_code like 'US'\n" +
            "group by s.name\n" +
            "order by s.name asc;",
            nativeQuery = true)
    List<Object[]> numbersOfBreweriesInEachState();

    @Query(value = "select\n" +
            "b_city\n" +
            "from brewery_entity\n" +
            "group by b_city\n" +
            "order by count(b_id) desc limit :top_number",
            nativeQuery = true)
    Set<String> topCitiesByCountOfBreweries(@Param("top_number") Integer numberOfTop);
}