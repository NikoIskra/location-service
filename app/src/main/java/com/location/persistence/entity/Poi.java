package com.location.persistence.entity;

import com.location.model.StatusEnum;
import com.location.model.TypeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "poi")
public class Poi {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poiSequenceGenerator")
  @SequenceGenerator(name = "poiSequenceGenerator", sequenceName = "poi_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "external_id")
  private String externalID;

  private String name;

  private TypeEnum type;

  @OneToMany(mappedBy = "poi", cascade = CascadeType.MERGE)
  private List<Tag> tags;

  private String description;

  private Float latitude;

  private Float longitude;

  private StatusEnum status;

  @Column(name = "created_at", insertable = false)
  private Timestamp createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Timestamp updatedAt;

  public Poi() {}

  public Poi(
      Long id,
      String externalID,
      String name,
      TypeEnum type,
      Float latitude,
      Float longitude,
      StatusEnum status) {
    this.id = id;
    this.externalID = externalID;
    this.name = name;
    this.type = type;
    this.latitude = latitude;
    this.longitude = longitude;
    this.status = status;
  }

  public Poi(
      Long id,
      String externalID,
      String name,
      TypeEnum type,
      List<Tag> tags,
      String description,
      Float latitude,
      Float longitude,
      StatusEnum status,
      Timestamp createdAt,
      Timestamp updatedAt) {
    this.id = id;
    this.externalID = externalID;
    this.name = name;
    this.type = type;
    this.tags = tags;
    this.description = description;
    this.latitude = latitude;
    this.longitude = longitude;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getExternalID() {
    return externalID;
  }

  public void setExternalID(String externalID) {
    this.externalID = externalID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
  }
}
