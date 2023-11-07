package com.location.service.impl;

import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.persistence.entity.Poi;
import com.location.persistence.entity.Tag;
import com.location.persistence.repository.PoiRepository;
import com.location.service.EntityConverterService;
import com.location.service.PoiService;
import com.location.service.PoiValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PoiServiceImpl implements PoiService {

  private final PoiRepository poiRepository;

  private final PoiValidator poiValidator;

  private final EntityConverterService entityConverterService;

  private final EntityManager entityManager;

  private static final String queryString =
      """
            INSERT INTO \"location-service\".poi (external_id, name, type, description, latitude, longitude, location, status)
            VALUES (:externalID, :name, :type, :description, :latitude, :longitude, ST_MakePoint(:longitude, :latitude), 'visible')
            RETURNING id;
            """;

  private static final String queryString2 =
      """
            INSERT INTO \"location-service\".tag (poi_id, name) VALUES (:poiID, :name);
            """;

  @Override
  @Transactional
  public PoiPostReturnModel save(UUID X_ACCOUNT_ID, PoiPostRequestModel poiPostRequestModel) {
    poiValidator.validatePoiPost(X_ACCOUNT_ID, poiPostRequestModel);
    Poi poi = entityConverterService.convertPoiPostRequestModelToPoi(poiPostRequestModel);
    Query queryExecute =
        entityManager
            .createNativeQuery(queryString)
            .setParameter("externalID", poi.getExternalID())
            .setParameter("name", poi.getName())
            .setParameter("type", poi.getType().toString())
            .setParameter("description", poi.getDescription())
            .setParameter("latitude", poi.getLatitude())
            .setParameter("longitude", poi.getLongitude());
    Long poiID = (Long) queryExecute.getSingleResult();
    for (Tag tag : poi.getTags()) {
      Query queryExecute2 =
          entityManager
              .createNativeQuery(queryString2)
              .setParameter("poiID", poiID)
              .setParameter("name", tag.getName().toString());
      queryExecute2.executeUpdate();
    }
    return entityConverterService.convertPoiToReturnModel(poi, poiID);
  }
}
