package com.location.persistence.repository;

import com.location.persistence.entity.Poi;
import com.location.persistence.entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
  List<Tag> findAllByPoiIn(List<Poi> pois);
}
