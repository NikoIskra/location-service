package com.location.persistence.repository.impl;

import com.location.model.PoiPutRequestModel;
import com.location.persistence.entity.Poi;
import com.location.persistence.entity.Tag;
import com.location.persistence.repository.CustomPoiRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
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

  private static final String searchNearestQueryString =
      """
    select p.id, p.external_id, p.name, p.type, p.description, p.latitude, p.longitude, p.status, p.created_at, p.updated_at, ST_DistanceSphere(p.location\\:::geometry, ST_MakePoint(:longitude, :latitude)) as distance
    from poi p WHERE ST_DWithin(p.location, ST_MakePoint(:longitude, :latitude), :meters) AND p.status = 'visible' ORDER BY distance ASC;
      """;

  private static final String poiUpdateQueryString =
      """
      UPDATE poi
      SET name = :name, type = :type, description = :description, status = :status, latitude = :latitude, longitude = :longitude, location = ST_MakePoint(:longitude, :latitude)
      WHERE id = :id
      RETURNING *;
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

  @Override
  public List<Poi> searchNearest(
      Integer meters, Float latitude, Float longitude, Integer page, Integer pageSize) {
    Query searchNearestQuery =
        entityManager
            .createNativeQuery(searchNearestQueryString, Poi.class)
            .setParameter("latitude", latitude)
            .setParameter("longitude", longitude)
            .setParameter("meters", meters)
            .setFirstResult(page * pageSize)
            .setMaxResults(pageSize);
    List<Poi> objects = searchNearestQuery.getResultList();
    return objects;
  }

  @Override
  @Transactional
  public Poi updatePoi(Long poiId, PoiPutRequestModel poiPutRequestModel) {
    Query updatePoiQuery =
        entityManager
            .createNativeQuery(poiUpdateQueryString, Poi.class)
            .setParameter("name", poiPutRequestModel.getName())
            .setParameter("type", poiPutRequestModel.getType().toString())
            .setParameter("status", poiPutRequestModel.getStatus().toString())
            .setParameter("description", poiPutRequestModel.getDescription())
            .setParameter("latitude", poiPutRequestModel.getLatitude())
            .setParameter("longitude", poiPutRequestModel.getLongitude())
            .setParameter("id", poiId);
    Poi poi = (Poi) updatePoiQuery.getSingleResult();
    return poi;
  }
}
