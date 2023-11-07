package com.location.service;

import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.model.PoiPostReturnModelResult;
import com.location.model.StatusEnum;
import com.location.model.TagsEnum;
import com.location.persistence.entity.Poi;
import com.location.persistence.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    if (!CollectionUtils.isEmpty(poiPostRequestModel.getTags())) {
      poi.setTags(convertListTagsEnumToTags(poiPostRequestModel.getTags()));
    }
    poi.setStatus(StatusEnum.VISIBLE);
    return poi;
  }

  public List<Tag> convertListTagsEnumToTags(List<TagsEnum> tagsEnums) {
    List<Tag> tags = new ArrayList<>();
    for (TagsEnum tagsEnum : tagsEnums) {
      Tag tag = new Tag(tagsEnum);
      tags.add(tag);
    }
    return tags;
  }

  public PoiPostReturnModel convertPoiToReturnModel(Poi poi, Long poiID) {
    PoiPostReturnModelResult poiPostReturnModelResult =
        modelMapper.map(poi, PoiPostReturnModelResult.class);
    List<TagsEnum> tagsEnums = new ArrayList<>();
    if (!CollectionUtils.isEmpty(poi.getTags())) {
      for (Tag tag : poi.getTags()) {
        tagsEnums.add(tag.getName());
      }
    }
    poiPostReturnModelResult.setTags(tagsEnums);
    poiPostReturnModelResult.setId(poiID);
    return new PoiPostReturnModel().ok(true).result(poiPostReturnModelResult);
  }
}
