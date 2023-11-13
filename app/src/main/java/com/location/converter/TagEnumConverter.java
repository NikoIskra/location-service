package com.location.converter;

import com.location.exception.BadRequestException;
import com.location.model.TagsEnum;
import jakarta.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;

public class TagEnumConverter implements AttributeConverter<TagsEnum, String> {

  @Override
  public String convertToDatabaseColumn(TagsEnum attribute) {
    if (attribute == null) {
      throw new BadRequestException("conversion failed");
    }
    return attribute.toString();
  }

  @Override
  public TagsEnum convertToEntityAttribute(String dbData) {
    List<TagsEnum> tags = Arrays.asList(TagsEnum.class.getEnumConstants());
    for (TagsEnum tag : tags) {
      if (dbData.equals(tag.toString())) {
        return tag;
      }
    }
    throw new BadRequestException("conversion failed");
  }
}
