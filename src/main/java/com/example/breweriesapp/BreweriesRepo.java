package com.example.breweriesapp;

import com.example.breweriesapp.model.BreweryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreweriesRepo extends JpaRepository<BreweryEntity, String> {

    @Query(value = """
                select distinct s.name as state_global, count(b.b_id) as count_of_breweries
                from brewery_entity b
                left join cities on cities.name = b.b_city
                left join states s on cities.state_id = s.id
                where s.country_code like 'US'
                group by s.name
                order by s.name asc;
            """,
            nativeQuery = true)
    List<Object[]> numbersOfBreweriesInEachState();

    @Query(value = """
                select
                b_city
                from brewery_entity
                group by b_city
                order by count(b_id) desc limit :top_number
            """,
            nativeQuery = true)
    List<String> topCitiesByCountOfBreweries(@Param("top_number") Integer numberOfTop);

    Long countAllByWebsitesNotNull();

    @Query(value = """
                select count(b)
                from BreweryEntity b
                where b.city like 'Delaware' and ((b.menus like '%taco%') or (b.menus like '%Taco%'))
            """)
    Long countBreweriesLocatedInDelawareAndOfferTacos();

    @Query(value = """
                select distinct
                s.name as state_global,
                count(b.b_name) as count_of_breweries,
                sum(case when b.b_menus like '%Wine%' then 1
                         when b.b_menus like '%wine%' then 1 else 0 end
                    ) as breweries_offer_wine,
                (sum(case when b.b_menus like '%Wine%' then 1
                          when b.b_menus like '%wine%' then 1
                          else 0 end)
                / count(b.b_name))*100 as percentage
                from brewery_entity b
                         left join cities on cities.name = b.b_city
                         left join states s on cities.state_id = s.id
                where s.country_code like 'US'
                group by s.name
                order by percentage desc
            """,
            nativeQuery = true)
    List<String[]> percentageBreweriesOffersWineByState();

    @Query(value = """
                select b_id from brewery_entity group by b_id having count(b_id) > 1;
            """,
            nativeQuery = true)
    List<String> findDuplicatedBreweries();
}