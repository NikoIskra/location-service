package com.location.converter;

import com.location.exception.BadRequestException;
import com.location.model.TypeEnum;
import jakarta.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;

public class TypeEnumConverter implements AttributeConverter<TypeEnum, String> {

  @Override
  public String convertToDatabaseColumn(TypeEnum attribute) {
    if (attribute == null) {
      return "service";
    }
    return attribute.toString();
  }

  @Override
  public TypeEnum convertToEntityAttribute(String dbData) {
    List<TypeEnum> types = Arrays.asList(TypeEnum.class.getEnumConstants());
    for (TypeEnum type : types) {
      if (dbData.equals(type.toString())) {
        return type;
      }
    }
    throw new BadRequestException("conversion failed");
  }
}
