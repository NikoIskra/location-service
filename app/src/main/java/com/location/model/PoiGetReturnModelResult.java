package com.location.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

/** PoiGetReturnModelResult */
@JsonTypeName("poiGetReturnModelResult")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PoiGetReturnModelResult {

  private Long id;

  private String externalId;

  private String name;

  private TypeEnum type;

  @Valid private List<TagsEnum> tags;

  private String description;

  private Float latitude;

  private Float longitude;

  private StatusEnum status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  public PoiGetReturnModelResult id(Long id) {
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

  public PoiGetReturnModelResult externalId(String externalId) {
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

  public PoiGetReturnModelResult name(String name) {
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

  public PoiGetReturnModelResult type(TypeEnum type) {
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

  public PoiGetReturnModelResult tags(List<TagsEnum> tags) {
    this.tags = tags;
    return this;
  }

  public PoiGetReturnModelResult addTagsItem(TagsEnum tagsItem) {
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

  public PoiGetReturnModelResult description(String description) {
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

  public PoiGetReturnModelResult latitude(Float latitude) {
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

  public PoiGetReturnModelResult longitude(Float longitude) {
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

  public PoiGetReturnModelResult status(StatusEnum status) {
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

  public PoiGetReturnModelResult createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   *
   * @return createdAt
   */
  @Valid
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public PoiGetReturnModelResult updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   *
   * @return updatedAt
   */
  @Valid
  @JsonProperty("updatedAt")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PoiGetReturnModelResult poiGetReturnModelResult = (PoiGetReturnModelResult) o;
    return Objects.equals(this.id, poiGetReturnModelResult.id)
        && Objects.equals(this.externalId, poiGetReturnModelResult.externalId)
        && Objects.equals(this.name, poiGetReturnModelResult.name)
        && Objects.equals(this.type, poiGetReturnModelResult.type)
        && Objects.equals(this.tags, poiGetReturnModelResult.tags)
        && Objects.equals(this.description, poiGetReturnModelResult.description)
        && Objects.equals(this.latitude, poiGetReturnModelResult.latitude)
        && Objects.equals(this.longitude, poiGetReturnModelResult.longitude)
        && Objects.equals(this.status, poiGetReturnModelResult.status)
        && Objects.equals(this.createdAt, poiGetReturnModelResult.createdAt)
        && Objects.equals(this.updatedAt, poiGetReturnModelResult.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        externalId,
        name,
        type,
        tags,
        description,
        latitude,
        longitude,
        status,
        createdAt,
        updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PoiGetReturnModelResult {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
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
