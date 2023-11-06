package com.location.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.location.model.StatusEnum;
import com.location.model.TypeEnum;
import com.location.persistence.entity.Poi;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class PoiRepositoryTest {

  @Autowired PoiRepository poiRepository;

  private static Poi createPoi() {
    Poi poi =
        new Poi(
            1L,
            "external",
            "name",
            TypeEnum.TOOLS,
            Float.valueOf(1),
            Float.valueOf(1),
            StatusEnum.VISIBLE);
    return poi;
  }

  @Test
  public void expectEmptyList() {
    List<Poi> pois = poiRepository.findAll();
    assertEquals(pois.size(), 0);
  }

  @Test
  @DirtiesContext
  public void testInsertToDB() {
    Poi poi = createPoi();
    poiRepository.save(poi);
    List<Poi> pois = poiRepository.findAll();
    assertEquals(1, pois.size());
  }

  @Test
  @DirtiesContext
  public void testDataPersists() {
    Poi poi = createPoi();
    poiRepository.save(poi);
    Poi poiFromDB = poiRepository.findById(poi.getId()).get();
    assertEquals(poi.getExternalID(), poiFromDB.getExternalID());
    assertEquals(poi.getName(), poiFromDB.getName());
    assertEquals(poi.getType(), poiFromDB.getType());
    assertEquals(poi.getLatitude(), poiFromDB.getLatitude());
    assertEquals(poi.getLongitude(), poiFromDB.getLongitude());
    assertEquals(poi.getStatus(), poiFromDB.getStatus());
  }
}
