package com.location.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.location.config.Configuration;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.model.StatusEnum;
import com.location.model.TagsEnum;
import com.location.model.TypeEnum;
import com.location.persistence.entity.Poi;
import com.location.persistence.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class EntityConverterServiceTest {

  private final EntityConverterService entityConverterService =
      new EntityConverterService(
          new Configuration().strictModelMapper(), new Configuration().modelMapper());

  private static List<TagsEnum> createTagEnumList() {
    return List.of(TagsEnum.CHINA_FOOD, TagsEnum.FOOD);
  }

  private static PoiPostRequestModel createPoiPostRequestModel() {
    List<TagsEnum> tagsEnums = new ArrayList<>();
    tagsEnums.add(TagsEnum.CHINA_FOOD);
    tagsEnums.add(TagsEnum.FOOD);
    PoiPostRequestModel poiPostRequestModel =
        new PoiPostRequestModel()
            .externalId("1")
            .name("name1")
            .type(TypeEnum.RESTAURANT)
            .tags(tagsEnums)
            .description("desc")
            .latitude(Float.valueOf("1.4"))
            .longitude(Float.valueOf("1.5"));
    return poiPostRequestModel;
  }

  private static Poi createPoi() {
    Poi poi =
        new Poi(
            1L,
            "1",
            "name",
            TypeEnum.RESTAURANT,
            Float.valueOf("1.4"),
            Float.valueOf("1.5"),
            StatusEnum.VISIBLE);
    Tag tag1 = new Tag(TagsEnum.CHINA_FOOD);
    Tag tag2 = new Tag(TagsEnum.FOOD);
    List<Tag> tags = List.of(tag1, tag2);
    poi.setTags(tags);
    return poi;
  }

  @Test
  void testConvertPoiPostRequestModelToPoi() {
    PoiPostRequestModel poiPostRequestModel = createPoiPostRequestModel();
    Poi poi = entityConverterService.convertPoiPostRequestModelToPoi(poiPostRequestModel);
    assertEquals(poiPostRequestModel.getExternalId(), poi.getExternalID());
    assertEquals(poiPostRequestModel.getName(), poi.getName());
    assertEquals(poiPostRequestModel.getLatitude(), poi.getLatitude());
    assertEquals(poiPostRequestModel.getLongitude(), poi.getLongitude());
  }

  @Test
  void testConvertPoiToReturnModel() {
    Poi poi = createPoi();
    PoiPostReturnModel poiPostReturnModel = entityConverterService.convertPoiToReturnModel(poi);
    assertEquals(1, poiPostReturnModel.getResult().getId());
    assertEquals(poi.getExternalID(), poiPostReturnModel.getResult().getExternalId());
    assertEquals(poi.getName(), poiPostReturnModel.getResult().getName());
    assertEquals(poi.getTags().get(0).getName(), poiPostReturnModel.getResult().getTags().get(0));
    assertEquals(poi.getName(), poiPostReturnModel.getResult().getName());
    assertEquals(poi.getLatitude(), poiPostReturnModel.getResult().getLatitude());
    assertEquals(poi.getLongitude(), poiPostReturnModel.getResult().getLongitude());
    assertEquals(poi.getStatus(), poiPostReturnModel.getResult().getStatus());
  }
}
