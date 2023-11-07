package com.location.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;
import java.util.*;
import org.hibernate.validator.constraints.*;

/** Gets or Sets tagsEnum */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum TagsEnum {
  FOOD("food"),

  REPAIR("repair"),

  RESTAURANT_FOOD("restaurant_food"),

  CHINA_FOOD("china_food"),

  PIZZA("pizza"),

  MEAT("meat"),

  VEGAN("vegan"),

  ORGANIC("organic"),

  GROCERY("grocery"),

  GIFT("gift");

  private String value;

  TagsEnum(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TagsEnum fromValue(String value) {
    for (TagsEnum b : TagsEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
