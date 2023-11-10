package com.location.persistence.repository.impl;

import com.location.persistence.entity.Poi;
import com.location.persistence.entity.Tag;
import com.location.persistence.repository.CustomPoiRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomPoiRepositoryImpl implements CustomPoiRepository {

  private static Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

  @PersistenceContext private EntityManager entityManager;

  private static final String poiInsertQueryString =
      """
          INSERT INTO poi (external_id, name, type, description, latitude, longitude, location, status)
          VALUES (:externalID, :name, :type, :description, :latitude, :longitude, ST_MakePoint(:longitude, :latitude), 'visible')
          RETURNING id;
          """;

  private static final String tagInsertQueryString =
      """
          INSERT INTO tag (poi_id, name) VALUES (:poiID, :name);
          """;

  @Override
  @Transactional
  public void insertPoi(Poi poi) {
    Query insertPoiQuery =
        entityManager
            .createNativeQuery(poiInsertQueryString)
            .setParameter("externalID", poi.getExternalID())
            .setParameter("name", poi.getName())
            .setParameter("type", poi.getType().toString())
            .setParameter("description", poi.getDescription())
            .setParameter("latitude", poi.getLatitude())
            .setParameter("longitude", poi.getLongitude());
    Long poiID = (Long) insertPoiQuery.getSingleResult();
    logger.info("INSERT POI QUERY EXECUTED");
    poi.setId(poiID);
    logger.info("POI ID = " + poiID.toString());
    for (Tag tag : poi.getTags()) {
      Query insertTagQuery =
          entityManager
              .createNativeQuery(tagInsertQueryString)
              .setParameter("poiID", poiID)
              .setParameter("name", tag.getName().toString());
      insertTagQuery.executeUpdate();
    }
  }
}
