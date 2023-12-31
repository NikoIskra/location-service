package com.location.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.location.model.TagsEnum;
import com.location.persistence.entity.Tag;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class tagRepositoryTest {

  @Autowired TagRepository tagRepository;

  private static Tag createTag() {
    Tag tag = new Tag(1L, TagsEnum.MEAT);
    return tag;
  }

  @Test
  public void expectEmptyList() {
    List<Tag> tags = tagRepository.findAll();
    assertEquals(tags.size(), 0);
  }

  @Test
  @DirtiesContext
  public void testInsertToDB() {
    Tag tag = createTag();
    tagRepository.save(tag);
    List<Tag> tags = tagRepository.findAll();
    assertEquals(1, tags.size());
  }

  @Test
  @DirtiesContext
  public void testDataPersists() {
    Tag tag = createTag();
    tagRepository.save(tag);
    Tag tagFromDB = tagRepository.findById(tag.getId()).get();
    assertEquals(tag.getName(), tagFromDB.getName());
  }
}
