package com.location.persistence.entity;

import com.location.converter.TagEnumConverter;
import com.location.model.TagsEnum;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tagSequenceGenerator")
  @SequenceGenerator(name = "tagSequenceGenerator", sequenceName = "tag_id_seq", allocationSize = 1)
  private Long id;

  @Convert(converter = TagEnumConverter.class)
  private TagsEnum name;

  @ManyToOne
  @JoinColumn(name = "poi_id")
  private Poi poi;

  public Tag() {}

  public Tag(TagsEnum name) {
    this.name = name;
  }

  public Tag(TagsEnum name, Poi poi) {
    this.name = name;
    this.poi = poi;
  }

  public Tag(Long id, TagsEnum name) {
    this.id = id;
    this.name = name;
  }

  public Tag(Long id, TagsEnum name, Poi poi) {
    this.id = id;
    this.name = name;
    this.poi = poi;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Poi getPoi() {
    return poi;
  }

  public void setPoi(Poi poi) {
    this.poi = poi;
  }

  public TagsEnum getName() {
    return name;
  }

  public void setName(TagsEnum name) {
    this.name = name;
  }
}
