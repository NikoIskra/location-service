package com.location.service.impl;

import com.location.exception.NotFoundException;
import com.location.model.PoiGetReturnModel;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.model.SearchNearestPoiModel;
import com.location.model.SearchNearestReturnModel;
import com.location.model.SearchNearestReturnModelResult;
import com.location.model.StatusEnum;
import com.location.persistence.entity.Poi;
import com.location.persistence.entity.Tag;
import com.location.persistence.repository.CustomPoiJPARepository;
import com.location.persistence.repository.TagRepository;
import com.location.service.EntityConverterService;
import com.location.service.PoiService;
import com.location.service.PoiValidator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PoiServiceImpl implements PoiService {

  private static Logger logger = LoggerFactory.getLogger(SLF4JLogger.class);

  private final PoiValidator poiValidator;

  private final EntityConverterService entityConverterService;

  private final CustomPoiJPARepository customPoiRepository;

  private final TagRepository tagRepository;

  @Value("${page.size.default}")
  private Integer defaultPageSize;

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
    Optional<Poi> poiOptional = customPoiRepository.findById(poiID);
    if (poiOptional.isPresent()) {
      Poi poi = poiOptional.get();
      return entityConverterService.convertPoiToGetReturnModel(poi);
    }
    throw new NotFoundException("Record does not exist!");
  }

  @Override
  public SearchNearestReturnModel searchNearest(
      UUID X_ACCOUNT_ID,
      Integer meters,
      Float latitude,
      Float longitude,
      Integer page,
      Integer pageSize) {
    Integer pageSizeActual = Objects.requireNonNullElse(pageSize, defaultPageSize);
    List<Poi> objects =
        customPoiRepository.searchNearest(meters, latitude, longitude, page, pageSizeActual);
    Set<Poi> poiSet = new HashSet<>();
    List<Tag> tags = tagRepository.findAllByPoiIn(objects);
    for (Tag tag : tags) {
      Poi poi = tag.getPoi();
      poiSet.add(poi);
    }
    List<SearchNearestPoiModel> poiModels = entityConverterService.convertPoisToSearchModel(poiSet);
    SearchNearestReturnModelResult searchNearestReturnModelResult =
        new SearchNearestReturnModelResult()
            .data(poiModels)
            .page(page)
            .pageSize(pageSizeActual)
            .latitude(latitude)
            .longitude(longitude)
            .distance(Float.valueOf(meters))
            .numberOfPages((int) Math.ceil(poiModels.size() / pageSizeActual));
    return new SearchNearestReturnModel().ok(true).result(searchNearestReturnModelResult);
  }
}
