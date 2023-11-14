package com.location.config;

import com.location.converter.TagEnumToTagListConverter;
import com.location.converter.TagToTagEnumListConverter;
import com.location.model.PoiGetReturnModelResult;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModelResult;
import com.location.model.SearchNearestPoiModel;
import com.location.persistence.entity.Poi;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean(name = "modelMapper")
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    TypeMap<PoiPostRequestModel, Poi> poiPostRequestModelToPoiTypeMap =
        modelMapper.createTypeMap(PoiPostRequestModel.class, Poi.class);
    poiPostRequestModelToPoiTypeMap.addMappings(
        mapper ->
            mapper
                .using(new TagEnumToTagListConverter())
                .map(PoiPostRequestModel::getTags, Poi::setTags));
    TypeMap<Poi, PoiPostReturnModelResult> poiToPoiPostReturnModelResultTypeMap =
        modelMapper.createTypeMap(Poi.class, PoiPostReturnModelResult.class);
    poiToPoiPostReturnModelResultTypeMap.addMappings(
        mapper ->
            mapper
                .using(new TagToTagEnumListConverter())
                .map(Poi::getTags, PoiPostReturnModelResult::setTags));
    TypeMap<Poi, PoiGetReturnModelResult> poiToPoiGetReturnModelResultTypeMap =
        modelMapper.createTypeMap(Poi.class, PoiGetReturnModelResult.class);
    poiToPoiGetReturnModelResultTypeMap.addMappings(
        mapper ->
            mapper
                .using(new TagToTagEnumListConverter())
                .map(Poi::getTags, PoiGetReturnModelResult::setTags));
    TypeMap<Poi, SearchNearestPoiModel> poiToSearchModelTypeMap =
        modelMapper.createTypeMap(Poi.class, SearchNearestPoiModel.class);
    poiToSearchModelTypeMap.addMappings(
        mapper ->
            mapper
                .using(new TagToTagEnumListConverter())
                .map(Poi::getTags, SearchNearestPoiModel::setTags));
    return modelMapper;
  }

  @Bean(name = "strictModelMapper")
  public ModelMapper strictModelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setSkipNullEnabled(false);
    return modelMapper;
  }
}
