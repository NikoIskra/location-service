package com.location.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;
import java.util.*;
import org.hibernate.validator.constraints.*;

/** Gets or Sets typeEnum */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum TypeEnum {
  SERVICE("service"),

  MARKET("market"),

  RESTAURANT("restaurant"),

  GROCERY("grocery"),

  NUTRITION("nutrition"),

  TOOLS("tools"),

  ELECTRONICS("electronics");

  private String value;

  TypeEnum(String value) {
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
  public static TypeEnum fromValue(String value) {
    for (TypeEnum b : TypeEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
