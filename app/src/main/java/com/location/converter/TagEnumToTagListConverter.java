package com.location.converter;

import com.location.model.TagsEnum;
import com.location.persistence.entity.Tag;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.AbstractConverter;

public class TagEnumToTagListConverter extends AbstractConverter<List<TagsEnum>, List<Tag>> {

  @Override
  protected List<Tag> convert(List<TagsEnum> source) {
    return source.stream()
        .map(
            tagEnum -> {
              Tag tag = new Tag(tagEnum);
              return tag;
            })
        .collect(Collectors.toList());
  }
}
