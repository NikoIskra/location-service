package com.location.converter;

import com.location.exception.BadRequestException;
import com.location.model.StatusEnum;
import jakarta.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;

public class StatusEnumConverter implements AttributeConverter<StatusEnum, String> {

  @Override
  public String convertToDatabaseColumn(StatusEnum attribute) {
    if (attribute == null) {
      throw new BadRequestException("conversion failed");
    }
    return attribute.toString();
  }

  @Override
  public StatusEnum convertToEntityAttribute(String dbData) {
    List<StatusEnum> statuses = Arrays.asList(StatusEnum.class.getEnumConstants());
    for (StatusEnum statusEnum : statuses) {
      if (dbData.equals(statusEnum.toString())) {
        return statusEnum;
      }
    }
    throw new BadRequestException("conversion failed");
  }
}
