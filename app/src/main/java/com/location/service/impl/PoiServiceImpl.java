package com.location.service.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PoiServiceImpl implements PoiService {

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
}
