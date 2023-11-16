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

/** PoiPutRequestModel */
@JsonTypeName("poiPutRequestModel")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PoiPutRequestModel {

  private String name;

  private TypeEnum type;

  @Valid private List<TagsEnum> tags;

  private String description;

  private StatusEnum status;

  private Float latitude;

  private Float longitude;

  /**
   * Default constructor
   *
   * @deprecated Use {@link PoiPutRequestModel#PoiPutRequestModel(String, TypeEnum, StatusEnum)}
   */
  @Deprecated
  public PoiPutRequestModel() {
    super();
  }

  /** Constructor with only required parameters */
  public PoiPutRequestModel(String name, TypeEnum type, StatusEnum status) {
    this.name = name;
    this.type = type;
    this.status = status;
  }

  public PoiPutRequestModel name(String name) {
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

  public PoiPutRequestModel type(TypeEnum type) {
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

  public PoiPutRequestModel tags(List<TagsEnum> tags) {
    this.tags = tags;
    return this;
  }

  public PoiPutRequestModel addTagsItem(TagsEnum tagsItem) {
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

  public PoiPutRequestModel description(String description) {
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

  public PoiPutRequestModel status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   *
   * @return status
   */
  @NotNull
  @Valid
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public PoiPutRequestModel latitude(Float latitude) {
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

  public PoiPutRequestModel longitude(Float longitude) {
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
    PoiPutRequestModel poiPutRequestModel = (PoiPutRequestModel) o;
    return Objects.equals(this.name, poiPutRequestModel.name)
        && Objects.equals(this.type, poiPutRequestModel.type)
        && Objects.equals(this.tags, poiPutRequestModel.tags)
        && Objects.equals(this.description, poiPutRequestModel.description)
        && Objects.equals(this.status, poiPutRequestModel.status)
        && Objects.equals(this.latitude, poiPutRequestModel.latitude)
        && Objects.equals(this.longitude, poiPutRequestModel.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, tags, description, status, latitude, longitude);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PoiPutRequestModel {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
