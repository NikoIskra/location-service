package com.location.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.location.exception.BadRequestException;
import com.location.exception.NotFoundException;
import com.location.model.PoiGetReturnModel;
import com.location.model.PoiGetReturnModelResult;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.model.PoiPostReturnModelResult;
import com.location.model.SearchNearestPoiModel;
import com.location.model.SearchNearestReturnModel;
import com.location.model.StatusEnum;
import com.location.model.TagsEnum;
import com.location.model.TypeEnum;
import com.location.persistence.entity.Poi;
import com.location.persistence.entity.Tag;
import com.location.persistence.repository.CustomPoiJPARepository;
import com.location.persistence.repository.TagRepository;
import com.location.service.EntityConverterService;
import com.location.service.PoiValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PoiServiceImplTest {

  @Mock EntityConverterService entityConverterService;

  @Mock PoiValidator poiValidator;

  @Mock CustomPoiJPARepository customPoiRepository;

  @Mock TagRepository tagRepository;

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

  private static PoiGetReturnModel createPoiGetReturnModel() {
    List<TagsEnum> tagsEnums = new ArrayList<>();
    tagsEnums.add(TagsEnum.CHINA_FOOD);
    tagsEnums.add(TagsEnum.FOOD);
    PoiGetReturnModelResult poiGetReturnModelResult =
        new PoiGetReturnModelResult()
            .id(1L)
            .externalId("1")
            .name("name")
            .type(TypeEnum.RESTAURANT)
            .tags(tagsEnums)
            .description("desc")
            .latitude(Float.valueOf("1.4"))
            .longitude(Float.valueOf("1.5"));
    return new PoiGetReturnModel().ok(true).result(poiGetReturnModelResult);
  }

  private static Tag createTag() {
    return new Tag(1L, TagsEnum.FOOD);
  }

  private static SearchNearestPoiModel createSearchNearestPoiModel() {
    List<TagsEnum> tags = List.of(TagsEnum.FOOD, TagsEnum.PIZZA);
    SearchNearestPoiModel searchNearestPoiModel =
        new SearchNearestPoiModel()
            .id(1L)
            .externalId("1")
            .name("name")
            .type(TypeEnum.RESTAURANT)
            .tags(tags)
            .description("desc")
            .latitude(Float.valueOf("1.3"))
            .longitude(Float.valueOf("1.3"))
            .status(StatusEnum.VISIBLE);
    return searchNearestPoiModel;
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
    when(entityConverterService.convertPoiPostRequestModelToPoi(poiPostRequestModel))
        .thenReturn(poi);
    when(entityConverterService.convertPoiToReturnModel(poi)).thenReturn(poiPostReturnModel);
    PoiPostReturnModel poiPostReturnModel2 = poiServiceImpl.save(uuid, poiPostRequestModel);
    verify(customPoiRepository).insertPoi(poi);
    verify(poiValidator).validatePoiPost(uuid, poiPostRequestModel);
    verify(entityConverterService).convertPoiPostRequestModelToPoi(poiPostRequestModel);
    verify(entityConverterService).convertPoiToReturnModel(poi);
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
    verifyNoInteractions(entityConverterService, customPoiRepository);
  }

  @Test
  void testGet() throws Exception {
    Poi poi = createPoi();
    PoiGetReturnModel poiGetReturnModel = createPoiGetReturnModel();
    when(customPoiRepository.findById(anyLong())).thenReturn(Optional.of(poi));
    when(entityConverterService.convertPoiToGetReturnModel(poi)).thenReturn(poiGetReturnModel);
    PoiGetReturnModel poiGetReturnModel2 = poiServiceImpl.get(uuid, 1L);
    assertEquals(true, poiGetReturnModel.isOk());
    assertEquals(1L, poiGetReturnModel2.getResult().getId());
    assertEquals(poi.getName(), poiGetReturnModel2.getResult().getName());
    assertEquals(poi.getExternalID(), poiGetReturnModel2.getResult().getExternalId());
    assertEquals(poi.getType(), poiGetReturnModel2.getResult().getType());
    assertEquals(poi.getLatitude(), poiGetReturnModel2.getResult().getLatitude());
    assertEquals(poi.getLongitude(), poiGetReturnModel2.getResult().getLongitude());
    verify(customPoiRepository).findById(anyLong());
    verify(entityConverterService).convertPoiToGetReturnModel(poi);
  }

  @Test
  void testGet_notFound() throws Exception {
    when(customPoiRepository.findById(1L)).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> poiServiceImpl.get(uuid, 1L));
    verify(customPoiRepository).findById(1L);
    verifyNoInteractions(entityConverterService);
  }

  @Test
  void testSearchNearest() throws Exception {
    Poi poi = createPoi();
    List<Poi> pois = List.of(poi, poi);
    Tag tag = createTag();
    List<Tag> tags = List.of(tag, tag);
    SearchNearestPoiModel searchNearestPoiModel = createSearchNearestPoiModel();
    List<SearchNearestPoiModel> models = List.of(searchNearestPoiModel, searchNearestPoiModel);
    when(customPoiRepository.searchNearest(1, Float.valueOf("1"), Float.valueOf("1"), 0, 50))
        .thenReturn(pois);
    when(tagRepository.findAllByPoiIn(pois)).thenReturn(tags);
    when(entityConverterService.convertPoisToSearchModel(anySet())).thenReturn(models);
    SearchNearestReturnModel returnmodel =
        poiServiceImpl.searchNearest(uuid, 1, Float.valueOf("1"), Float.valueOf("1"), 0, 50);
    assertEquals(returnmodel.isOk(), true);
    assertEquals(returnmodel.getResult().getPage(), 0);
    assertEquals(returnmodel.getResult().getData().get(0).getId(), 1L);
    verify(customPoiRepository).searchNearest(1, Float.valueOf("1"), Float.valueOf("1"), 0, 50);
    verify(tagRepository).findAllByPoiIn(pois);
    verify(entityConverterService).convertPoisToSearchModel(anySet());
  }

  @Test
  void testSearchNearest_badRequest() throws Exception {
    when(customPoiRepository.searchNearest(1, Float.valueOf("1"), Float.valueOf("1"), 0, 50))
        .thenThrow(new BadRequestException("bad request"));
    assertThrows(
        BadRequestException.class,
        () -> poiServiceImpl.searchNearest(uuid, 1, Float.valueOf("1"), Float.valueOf("1"), 0, 50));
    verify(customPoiRepository).searchNearest(1, Float.valueOf("1"), Float.valueOf("1"), 0, 50);
    verifyNoInteractions(tagRepository, entityConverterService);
  }
}
