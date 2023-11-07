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

  private Float latitude;

  private Float longitude;

  /**
   * Default constructor
   *
   * @deprecated Use {@link PoiPostRequestModel#PoiPostRequestModel(Long, String, TypeEnum)}
   */
  @Deprecated
  public PoiPostRequestModel() {
    super();
  }

  /** Constructor with only required parameters */
  public PoiPostRequestModel(Long externalId, String name, TypeEnum type) {
    this.externalId = externalId;
    this.name = name;
    this.type = type;
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

  public PoiPostRequestModel latitude(Float latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   *
   * @return latitude
   */
  @JsonProperty("latitude")
  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public PoiPostRequestModel longitude(Float longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Get longitude
   *
   * @return longitude
   */
  @JsonProperty("longitude")
  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
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
        && Objects.equals(this.latitude, poiPostRequestModel.latitude)
        && Objects.equals(this.longitude, poiPostRequestModel.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(externalId, name, type, tags, description, latitude, longitude);
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
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
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
