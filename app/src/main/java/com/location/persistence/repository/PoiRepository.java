package com.location.persistence.repository;

import com.location.persistence.entity.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoiRepository extends JpaRepository<Poi, Long> {
  Boolean existsByExternalIDAndName(String externalId, String name);
}
