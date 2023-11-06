package com.location.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tagSequenceGenerator")
    @SequenceGenerator(
        name = "tagSequenceGenerator",
        sequenceName = "tag_id_seq",
        allocationSize = 1)
    private Long id;

    @Column(name = "poi_id")
    private Long poiID;

    private String name;

    public Tag() {
    }

    public Tag(Long id, Long poiID, String name) {
        this.id = id;
        this.poiID = poiID;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPoiID() {
        return poiID;
    }

    public void setPoiID(Long poiID) {
        this.poiID = poiID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
}
