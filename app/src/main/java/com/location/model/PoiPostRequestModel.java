package com.location.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.*;

/** PoiPostRequestModel */
@JsonTypeName("poiPostRequestModel")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PoiPostRequestModel {

  private Long externalId;

  private String name;

  private TypeEnum type;

  @Valid private List<TagsEnum> tags;

  private String description;

  private Float lat;

  private Float _long;

  /**
   * Default constructor
   *
   * @deprecated Use {@link PoiPostRequestModel#PoiPostRequestModel(Long, String, TypeEnum, Float,
   *     Float)}
   */
  @Deprecated
  public PoiPostRequestModel() {
    super();
  }

  /** Constructor with only required parameters */
  public PoiPostRequestModel(Long externalId, String name, TypeEnum type, Float lat, Float _long) {
    this.externalId = externalId;
    this.name = name;
    this.type = type;
    this.lat = lat;
    this._long = _long;
  }

  public PoiPostRequestModel externalId(Long externalId) {
    this.externalId = externalId;
    return this;
  }

  /**
   * Get externalId
   *
   * @return externalId
   */
  @NotNull
  @JsonProperty("externalId")
  public Long getExternalId() {
    return externalId;
  }

  public void setExternalId(Long externalId) {
    this.externalId = externalId;
  }

  public PoiPostRequestModel name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   */
  @NotNull
  @Size(min = 5, max = 64)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PoiPostRequestModel type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   *
   * @return type
   */
  @NotNull
  @Valid
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public PoiPostRequestModel tags(List<TagsEnum> tags) {
    this.tags = tags;
    return this;
  }

  public PoiPostRequestModel addTagsItem(TagsEnum tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   *
   * @return tags
   */
  @Valid
  @JsonProperty("tags")
  public List<TagsEnum> getTags() {
    return tags;
  }

  public void setTags(List<TagsEnum> tags) {
    this.tags = tags;
  }

  public PoiPostRequestModel description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   *
   * @return description
   */
  @Size(max = 512)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PoiPostRequestModel lat(Float lat) {
    this.lat = lat;
    return this;
  }

  /**
   * Get lat
   *
   * @return lat
   */
  @NotNull
  @JsonProperty("lat")
  public Float getLat() {
    return lat;
  }

  public void setLat(Float lat) {
    this.lat = lat;
  }

  public PoiPostRequestModel _long(Float _long) {
    this._long = _long;
    return this;
  }

  /**
   * Get _long
   *
   * @return _long
   */
  @NotNull
  @JsonProperty("long")
  public Float getLong() {
    return _long;
  }

  public void setLong(Float _long) {
    this._long = _long;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PoiPostRequestModel poiPostRequestModel = (PoiPostRequestModel) o;
    return Objects.equals(this.externalId, poiPostRequestModel.externalId)
        && Objects.equals(this.name, poiPostRequestModel.name)
        && Objects.equals(this.type, poiPostRequestModel.type)
        && Objects.equals(this.tags, poiPostRequestModel.tags)
        && Objects.equals(this.description, poiPostRequestModel.description)
        && Objects.equals(this.lat, poiPostRequestModel.lat)
        && Objects.equals(this._long, poiPostRequestModel._long);
  }

  @Override
  public int hashCode() {
    return Objects.hash(externalId, name, type, tags, description, lat, _long);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PoiPostRequestModel {\n");
    sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    _long: ").append(toIndentedString(_long)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
