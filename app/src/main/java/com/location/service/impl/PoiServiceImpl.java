package com.location.service.impl;

import com.location.model.PoiGetReturnModel;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.model.StatusEnum;
import com.location.persistence.entity.Poi;
import com.location.persistence.repository.CustomPoiJPARepository;
import com.location.service.EntityConverterService;
import com.location.service.PoiService;
import com.location.service.PoiValidator;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PoiServiceImpl implements PoiService {

  private static Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

  private final PoiValidator poiValidator;

  private final EntityConverterService entityConverterService;

  private final CustomPoiJPARepository customPoiRepository;

  @Override
  @Transactional
  public PoiPostReturnModel save(UUID X_ACCOUNT_ID, PoiPostRequestModel poiPostRequestModel) {
    poiValidator.validatePoiPost(X_ACCOUNT_ID, poiPostRequestModel);
    Poi poi = entityConverterService.convertPoiPostRequestModelToPoi(poiPostRequestModel);
    poi.setStatus(StatusEnum.VISIBLE);
    customPoiRepository.insertPoi(poi);
    return entityConverterService.convertPoiToReturnModel(poi);
  }

  @Override
  public PoiGetReturnModel get(UUID X_ACCOUNT_ID, Long poiID) {
    poiValidator.validatePoiGet(X_ACCOUNT_ID, poiID);
    Poi poi = customPoiRepository.findById(poiID).get();
    return entityConverterService.convertPoiToGetReturnModel(poi);
  }
}
