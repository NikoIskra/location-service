package com.location.service;

import com.location.converter.TagEnumToTagListConverter;
import com.location.converter.TagToTagEnumListConverter;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.model.PoiPostReturnModelResult;
import com.location.persistence.entity.Poi;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
    TypeMap<PoiPostRequestModel, Poi> typeMap =
        modelMapper.createTypeMap(PoiPostRequestModel.class, Poi.class);
    typeMap.addMappings(
        mapper ->
            mapper
                .using(new TagEnumToTagListConverter())
                .map(PoiPostRequestModel::getTags, Poi::setTags));
    Poi poi = modelMapper.map(poiPostRequestModel, Poi.class);
    return poi;
  }

  public PoiPostReturnModel convertPoiToReturnModel(Poi poi) {
    TypeMap<Poi, PoiPostReturnModelResult> typeMap =
        modelMapper.createTypeMap(Poi.class, PoiPostReturnModelResult.class);
    typeMap.addMappings(
        mapper ->
            mapper
                .using(new TagToTagEnumListConverter())
                .map(Poi::getTags, PoiPostReturnModelResult::setTags));
    PoiPostReturnModelResult poiPostReturnModelResult =
        modelMapper.map(poi, PoiPostReturnModelResult.class);
    return new PoiPostReturnModel().ok(true).result(poiPostReturnModelResult);
  }
}
