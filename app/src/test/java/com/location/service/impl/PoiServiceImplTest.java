package com.location.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.location.exception.BadRequestException;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.model.PoiPostReturnModelResult;
import com.location.model.StatusEnum;
import com.location.model.TagsEnum;
import com.location.model.TypeEnum;
import com.location.persistence.entity.Poi;
import com.location.persistence.entity.Tag;
import com.location.service.EntityConverterService;
import com.location.service.PoiValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PoiServiceImplTest {
  @Mock EntityManager entityManager;

  @Mock EntityConverterService entityConverterService;

  @Mock Query query;

  @Mock PoiValidator poiValidator;

  @InjectMocks PoiServiceImpl poiServiceImpl;

  private static final UUID uuid = UUID.fromString("ec73eca8-1e43-4c0d-b5a7-588b3c0e3c9c");

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

  private static PoiPostReturnModel createPoiPostReturnModel() {
    List<TagsEnum> tagsEnums = new ArrayList<>();
    tagsEnums.add(TagsEnum.CHINA_FOOD);
    tagsEnums.add(TagsEnum.FOOD);
    PoiPostReturnModelResult poiPostReturnModelResult =
        new PoiPostReturnModelResult()
            .id(1L)
            .externalId("1")
            .name("name1")
            .type(TypeEnum.RESTAURANT)
            .tags(tagsEnums)
            .description("desc")
            .latitude(Float.valueOf("1.4"))
            .longitude(Float.valueOf("1.5"))
            .status(StatusEnum.VISIBLE);
    return new PoiPostReturnModel().ok(true).result(poiPostReturnModelResult);
  }

  @Test
  void testSave() {
    PoiPostRequestModel poiPostRequestModel = createPoiPostRequestModel();
    Poi poi = createPoi();
    PoiPostReturnModel poiPostReturnModel = createPoiPostReturnModel();
    Query query = mock(Query.class);
    when(query.setParameter(anyString(), any())).thenReturn(query);
    when(entityConverterService.convertPoiPostRequestModelToPoi(poiPostRequestModel))
        .thenReturn(poi);
    when(entityManager.createNativeQuery(anyString())).thenReturn(query);
    when(query.getSingleResult()).thenReturn(1L);
    when(entityConverterService.convertPoiToReturnModel(poi, 1L)).thenReturn(poiPostReturnModel);
    PoiPostReturnModel poiPostReturnModel2 = poiServiceImpl.save(uuid, poiPostRequestModel);
    verify(poiValidator).validatePoiPost(uuid, poiPostRequestModel);
    verify(entityManager, times(3)).createNativeQuery(any());
    verify(entityConverterService).convertPoiPostRequestModelToPoi(poiPostRequestModel);
    verify(entityConverterService).convertPoiToReturnModel(poi, 1L);
    assertEquals(true, poiPostReturnModel2.isOk());
    assertEquals(1L, poiPostReturnModel2.getResult().getId());
    assertEquals(poiPostRequestModel.getTags(), poiPostReturnModel2.getResult().getTags());
    assertEquals(poiPostRequestModel.getName(), poiPostReturnModel2.getResult().getName());
    assertEquals(
        poiPostRequestModel.getExternalId(), poiPostReturnModel2.getResult().getExternalId());
    assertEquals(poiPostRequestModel.getType(), poiPostReturnModel2.getResult().getType());
    assertEquals(
        poiPostRequestModel.getDescription(), poiPostReturnModel2.getResult().getDescription());
    assertEquals(poiPostRequestModel.getLatitude(), poiPostReturnModel2.getResult().getLatitude());
    assertEquals(
        poiPostRequestModel.getLongitude(), poiPostReturnModel2.getResult().getLongitude());
    assertEquals(StatusEnum.VISIBLE, poiPostReturnModel2.getResult().getStatus());
  }

  @Test
  void testSave_validationError() throws Exception {
    PoiPostRequestModel poiPostRequestModel = createPoiPostRequestModel();
    doThrow(BadRequestException.class)
        .when(poiValidator)
        .validatePoiPost(uuid, poiPostRequestModel);
    assertThrows(BadRequestException.class, () -> poiServiceImpl.save(uuid, poiPostRequestModel));
    verify(poiValidator).validatePoiPost(uuid, poiPostRequestModel);
    verifyNoInteractions(entityManager, query, entityConverterService);
  }
}
