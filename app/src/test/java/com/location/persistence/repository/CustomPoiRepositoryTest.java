package com.location.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.location.model.StatusEnum;
import com.location.model.TagsEnum;
import com.location.model.TypeEnum;
import com.location.persistence.entity.Poi;
import com.location.persistence.entity.Tag;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class CustomPoiRepositoryTest {
  @Autowired CustomPoiJPARepository customPoiJPARepository;

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
  public void expectEmptyList() {
    List<Poi> pois = customPoiJPARepository.findAll();
    assertEquals(pois.size(), 0);
  }

  @Test
  @DirtiesContext
  public void testInsertToDB() {
    Poi poi = createPoi();
    customPoiJPARepository.save(poi);
    List<Poi> pois = customPoiJPARepository.findAll();
    assertEquals(pois.size(), 1);
  }

  @Test
  @DirtiesContext
  public void testDataPersists() {
    Poi poi = createPoi();
    customPoiJPARepository.save(poi);
    Poi poiFromDB = customPoiJPARepository.findById(poi.getId()).get();
    assertEquals(poi.getExternalID(), poiFromDB.getExternalID());
    assertEquals(poi.getName(), poiFromDB.getName());
    assertEquals(poi.getType(), poiFromDB.getType());
    assertEquals(poi.getLatitude(), poiFromDB.getLatitude());
    assertEquals(poi.getLongitude(), poiFromDB.getLongitude());
    assertEquals(poi.getStatus(), poiFromDB.getStatus());
  }
}
