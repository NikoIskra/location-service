package com.location.controller.impl;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.location.exception.BadRequestException;
import com.location.model.PoiGetReturnModel;
import com.location.model.PoiGetReturnModelResult;
import com.location.model.PoiPostRequestModel;
import com.location.model.PoiPostReturnModel;
import com.location.model.PoiPostReturnModelResult;
import com.location.model.StatusEnum;
import com.location.model.TagsEnum;
import com.location.model.TypeEnum;
import com.location.service.impl.PoiServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@WebMvcTest(PoiController.class)
@EnableWebMvc
public class PoiControllerTest {

  @Autowired MockMvc mvc;

  @MockBean PoiServiceImpl poiServiceImpl;

  ObjectMapper mapper = new ObjectMapper();

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

  @Test
  void testInsertPoi() throws Exception {
    PoiPostRequestModel poiPostRequestModel = createPoiPostRequestModel();
    PoiPostReturnModel poiPostReturnModel = createPoiPostReturnModel();
    when(poiServiceImpl.save(uuid, poiPostRequestModel)).thenReturn(poiPostReturnModel);
    mvc.perform(
            MockMvcRequestBuilders.post("/api/v1/poi")
                .header("X-ACCOUNT-ID", uuid.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(poiPostRequestModel)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$.result.id").value(1));
  }

  @Test
  void testInsertPoi_badRequest() throws Exception {
    PoiPostRequestModel poiPostRequestModel = createPoiPostRequestModel();
    when(poiServiceImpl.save(uuid, poiPostRequestModel)).thenThrow(BadRequestException.class);
    mvc.perform(
            MockMvcRequestBuilders.post("/api/v1/poi")
                .header("X-ACCOUNT-ID", uuid.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(poiPostRequestModel)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value(false));
  }

  @Test
  void testGetPoi() throws Exception {
    PoiGetReturnModel poiGetReturnModel = createPoiGetReturnModel();
    when(poiServiceImpl.get(uuid, 1L)).thenReturn(poiGetReturnModel);
    mvc.perform(MockMvcRequestBuilders.get("/api/v1/poi/1").header("X-ACCOUNT-ID", uuid.toString()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$.result.id").value(1));
  }

  @Test
  void testGetPoi_badRequest() throws Exception {
    when(poiServiceImpl.get(uuid, 1L)).thenThrow(BadRequestException.class);
    mvc.perform(MockMvcRequestBuilders.get("/api/v1/poi/1").header("X-ACCOUNT-ID", uuid.toString()))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value(false));
  }
}
