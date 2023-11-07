package com.location.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.location.exception.BadRequestException;
import com.location.exception.ConflictException;
import com.location.model.PoiPostRequestModel;
import com.location.model.TagsEnum;
import com.location.model.TypeEnum;
import com.location.persistence.repository.PoiRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PoiValidatorTest {
  @Mock PoiRepository poiRepository;
  @Mock AccountApiClient accountApiClient;
  @InjectMocks PoiValidator poiValidator;

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

  @Test
  void testValidatePoiPost() {
    PoiPostRequestModel poiPostRequestModel = createPoiPostRequestModel();
    when(poiRepository.existsByExternalIDAndName(anyString(), anyString())).thenReturn(false);
    assertDoesNotThrow(() -> poiValidator.validatePoiPost(uuid, poiPostRequestModel));
    verify(accountApiClient).verifyAccountID(uuid);
    verify(poiRepository).existsByExternalIDAndName(anyString(), anyString());
  }

  @Test
  void testValidatePoiPost_duplicateTags() {
    PoiPostRequestModel poiPostRequestModel = createPoiPostRequestModel();
    List<TagsEnum> tagsEnums = poiPostRequestModel.getTags();
    tagsEnums.add(TagsEnum.FOOD);
    poiPostRequestModel.setTags(tagsEnums);
    assertThrows(
        BadRequestException.class, () -> poiValidator.validatePoiPost(uuid, poiPostRequestModel));
    verify(accountApiClient).verifyAccountID(uuid);
    verifyNoInteractions(poiRepository);
  }

  @Test
  void testValidatePoiPost_duplicate() {
    PoiPostRequestModel poiPostRequestModel = createPoiPostRequestModel();
    when(poiRepository.existsByExternalIDAndName(anyString(), anyString())).thenReturn(true);
    assertThrows(
        ConflictException.class, () -> poiValidator.validatePoiPost(uuid, poiPostRequestModel));
    verify(accountApiClient).verifyAccountID(uuid);
    verify(poiRepository).existsByExternalIDAndName(anyString(), anyString());
  }
}
