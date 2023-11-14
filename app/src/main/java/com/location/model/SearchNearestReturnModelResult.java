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

/** SearchNearestReturnModelResult */
@JsonTypeName("searchNearestReturnModelResult")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SearchNearestReturnModelResult {

  @Valid private List<@Valid SearchNearestPoiModel> data;

  private Integer page;

  private Integer pageSize;

  private Float latitude;

  private Float longitude;

  private Float distance;

  private Integer numberOfPages;

  public SearchNearestReturnModelResult data(List<@Valid SearchNearestPoiModel> data) {
    this.data = data;
    return this;
  }

  public SearchNearestReturnModelResult addDataItem(SearchNearestPoiModel dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Get data
   *
   * @return data
   */
  @Valid
  @JsonProperty("data")
  public List<@Valid SearchNearestPoiModel> getData() {
    return data;
  }

  public void setData(List<@Valid SearchNearestPoiModel> data) {
    this.data = data;
  }

  public SearchNearestReturnModelResult page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Get page
   *
   * @return page
   */
  @JsonProperty("page")
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public SearchNearestReturnModelResult pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * Get pageSize
   *
   * @return pageSize
   */
  @JsonProperty("pageSize")
  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public SearchNearestReturnModelResult latitude(Float latitude) {
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

  public SearchNearestReturnModelResult longitude(Float longitude) {
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

  public SearchNearestReturnModelResult distance(Float distance) {
    this.distance = distance;
    return this;
  }

  /**
   * Get distance
   *
   * @return distance
   */
  @JsonProperty("distance")
  public Float getDistance() {
    return distance;
  }

  public void setDistance(Float distance) {
    this.distance = distance;
  }

  public SearchNearestReturnModelResult numberOfPages(Integer numberOfPages) {
    this.numberOfPages = numberOfPages;
    return this;
  }

  /**
   * Get numberOfPages
   *
   * @return numberOfPages
   */
  @JsonProperty("numberOfPages")
  public Integer getNumberOfPages() {
    return numberOfPages;
  }

  public void setNumberOfPages(Integer numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchNearestReturnModelResult searchNearestReturnModelResult =
        (SearchNearestReturnModelResult) o;
    return Objects.equals(this.data, searchNearestReturnModelResult.data)
        && Objects.equals(this.page, searchNearestReturnModelResult.page)
        && Objects.equals(this.pageSize, searchNearestReturnModelResult.pageSize)
        && Objects.equals(this.latitude, searchNearestReturnModelResult.latitude)
        && Objects.equals(this.longitude, searchNearestReturnModelResult.longitude)
        && Objects.equals(this.distance, searchNearestReturnModelResult.distance)
        && Objects.equals(this.numberOfPages, searchNearestReturnModelResult.numberOfPages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, page, pageSize, latitude, longitude, distance, numberOfPages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchNearestReturnModelResult {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    numberOfPages: ").append(toIndentedString(numberOfPages)).append("\n");
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
