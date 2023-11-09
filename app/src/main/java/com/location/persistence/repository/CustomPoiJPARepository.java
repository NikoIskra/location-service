package com.location.persistence.repository;

import com.location.persistence.entity.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomPoiJPARepository extends JpaRepository<Poi, Long>, CustomPoiRepository {}
