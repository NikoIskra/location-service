package com.location.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "poi")
public class Poi {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poiSequenceGenerator")
  @SequenceGenerator(name = "poiSequenceGenerator", sequenceName = "poi_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "eternal_id")
  private String externalID;

  private String name;

  private String type;

  private String description;

  private Float lat;

  @Column(name = "long")
  private Float longFloat;

  private String status;

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
      String type,
      Float lat,
      Float longFloat,
      String status) {
    this.id = id;
    this.externalID = externalID;
    this.name = name;
    this.type = type;
    this.lat = lat;
    this.longFloat = longFloat;
    this.status = status;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Float getLat() {
    return lat;
  }

  public void setLat(Float lat) {
    this.lat = lat;
  }

  public Float getLongFloat() {
    return longFloat;
  }

  public void setLongFloat(Float longFloat) {
    this.longFloat = longFloat;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
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
}
