package com.location.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.location.persistence.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    
}
