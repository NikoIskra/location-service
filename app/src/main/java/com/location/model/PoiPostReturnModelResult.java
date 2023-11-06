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

/** PoiPostReturnModelResult */
@JsonTypeName("poiPostReturnModelResult")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PoiPostReturnModelResult {

  private Long id;

  private Long externalId;

  private String name;

  private TypeEnum type;

  @Valid private List<TagsEnum> tags;

  private String description;

  private Float lat;

  private Float _long;

  private StatusEnum status;

  public PoiPostReturnModelResult id(Long id) {
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

  public PoiPostReturnModelResult externalId(Long externalId) {
    this.externalId = externalId;
    return this;
  }

  /**
   * Get externalId
   *
   * @return externalId
   */
  @JsonProperty("externalId")
  public Long getExternalId() {
    return externalId;
  }

  public void setExternalId(Long externalId) {
    this.externalId = externalId;
  }

  public PoiPostReturnModelResult name(String name) {
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

  public PoiPostReturnModelResult type(TypeEnum type) {
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

  public PoiPostReturnModelResult tags(List<TagsEnum> tags) {
    this.tags = tags;
    return this;
  }

  public PoiPostReturnModelResult addTagsItem(TagsEnum tagsItem) {
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

  public PoiPostReturnModelResult description(String description) {
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

  public PoiPostReturnModelResult lat(Float lat) {
    this.lat = lat;
    return this;
  }

  /**
   * Get lat
   *
   * @return lat
   */
  @JsonProperty("lat")
  public Float getLat() {
    return lat;
  }

  public void setLat(Float lat) {
    this.lat = lat;
  }

  public PoiPostReturnModelResult _long(Float _long) {
    this._long = _long;
    return this;
  }

  /**
   * Get _long
   *
   * @return _long
   */
  @JsonProperty("long")
  public Float getLong() {
    return _long;
  }

  public void setLong(Float _long) {
    this._long = _long;
  }

  public PoiPostReturnModelResult status(StatusEnum status) {
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
    PoiPostReturnModelResult poiPostReturnModelResult = (PoiPostReturnModelResult) o;
    return Objects.equals(this.id, poiPostReturnModelResult.id)
        && Objects.equals(this.externalId, poiPostReturnModelResult.externalId)
        && Objects.equals(this.name, poiPostReturnModelResult.name)
        && Objects.equals(this.type, poiPostReturnModelResult.type)
        && Objects.equals(this.tags, poiPostReturnModelResult.tags)
        && Objects.equals(this.description, poiPostReturnModelResult.description)
        && Objects.equals(this.lat, poiPostReturnModelResult.lat)
        && Objects.equals(this._long, poiPostReturnModelResult._long)
        && Objects.equals(this.status, poiPostReturnModelResult.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, externalId, name, type, tags, description, lat, _long, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PoiPostReturnModelResult {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    _long: ").append(toIndentedString(_long)).append("\n");
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
