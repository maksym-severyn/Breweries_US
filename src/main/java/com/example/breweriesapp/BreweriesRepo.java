package com.example.breweriesapp;

import com.example.breweriesapp.model.BreweryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweriesRepo extends JpaRepository<BreweryEntity, String> {
}