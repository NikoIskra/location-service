package com.location.converter;

import com.location.model.TagsEnum;
import com.location.persistence.entity.Tag;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.AbstractConverter;

public class TagToTagEnumListConverter extends AbstractConverter<List<Tag>, List<TagsEnum>> {

  @Override
  protected List<TagsEnum> convert(List<Tag> source) {
    return source.stream().map(Tag::getName).collect(Collectors.toList());
  }
}
