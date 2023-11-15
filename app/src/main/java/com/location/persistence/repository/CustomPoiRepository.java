package com.location.persistence.repository;

import com.location.persistence.entity.Poi;
import java.util.List;

public interface CustomPoiRepository {
  void insertPoi(Poi poi);

  List<Poi> searchNearest(
      Integer meters, Float latitude, Float longitude, Integer page, Integer pageSize);
}
