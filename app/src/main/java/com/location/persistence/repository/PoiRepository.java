package com.location.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.location.persistence.entity.Poi;

public interface PoiRepository extends JpaRepository<Poi, Long> {
    
}
