package com.location.service;

import com.location.model.PoiGetReturnModel;
import com.location.model.PoiGetReturnModelResult;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.model.PoiPostReturnModelResult;
import com.location.persistence.entity.Poi;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EntityConverterService {

  private final ModelMapper modelMapper;
  private final ModelMapper strictModelMapper;

  public EntityConverterService(
      @Qualifier("strictModelMapper") ModelMapper strictModelMapper,
      @Qualifier("modelMapper") ModelMapper modelMapper) {
    this.strictModelMapper = strictModelMapper;
    this.modelMapper = modelMapper;
  }

  public Poi convertPoiPostRequestModelToPoi(PoiPostRequestModel poiPostRequestModel) {
    Poi poi = modelMapper.map(poiPostRequestModel, Poi.class);
    return poi;
  }

  public PoiPostReturnModel convertPoiToReturnModel(Poi poi) {
    PoiPostReturnModelResult poiPostReturnModelResult =
        modelMapper.map(poi, PoiPostReturnModelResult.class);
    return new PoiPostReturnModel().ok(true).result(poiPostReturnModelResult);
  }

  public PoiGetReturnModel convertPoiToGetReturnModel(Poi poi) {
    PoiGetReturnModelResult poiGetReturnModelResult =
        modelMapper.map(poi, PoiGetReturnModelResult.class);
    return new PoiGetReturnModel().ok(true).result(poiGetReturnModelResult);
  }
}
