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

/** SearchNearestPoiModel */
@JsonTypeName("searchNearestPoiModel")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SearchNearestPoiModel {

  private Long id;

  private String externalId;

  private String name;

  private TypeEnum type;

  @Valid private List<TagsEnum> tags;

  private String description;

  private Float latitude;

  private Float longitude;

  private StatusEnum status;

  public SearchNearestPoiModel id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SearchNearestPoiModel externalId(String externalId) {
    this.externalId = externalId;
    return this;
  }

  /**
   * Get externalId
   *
   * @return externalId
   */
  @JsonProperty("externalId")
  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public SearchNearestPoiModel name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   */
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SearchNearestPoiModel type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   *
   * @return type
   */
  @Valid
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public SearchNearestPoiModel tags(List<TagsEnum> tags) {
    this.tags = tags;
    return this;
  }

  public SearchNearestPoiModel addTagsItem(TagsEnum tagsItem) {
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

  public SearchNearestPoiModel description(String description) {
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

  public SearchNearestPoiModel latitude(Float latitude) {
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

  public SearchNearestPoiModel longitude(Float longitude) {
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

  public SearchNearestPoiModel status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   *
   * @return status
   */
  @Valid
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchNearestPoiModel searchNearestPoiModel = (SearchNearestPoiModel) o;
    return Objects.equals(this.id, searchNearestPoiModel.id)
        && Objects.equals(this.externalId, searchNearestPoiModel.externalId)
        && Objects.equals(this.name, searchNearestPoiModel.name)
        && Objects.equals(this.type, searchNearestPoiModel.type)
        && Objects.equals(this.tags, searchNearestPoiModel.tags)
        && Objects.equals(this.description, searchNearestPoiModel.description)
        && Objects.equals(this.latitude, searchNearestPoiModel.latitude)
        && Objects.equals(this.longitude, searchNearestPoiModel.longitude)
        && Objects.equals(this.status, searchNearestPoiModel.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, externalId, name, type, tags, description, latitude, longitude, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchNearestPoiModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
