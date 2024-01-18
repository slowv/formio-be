package com.cimb.demo.repository;

import com.cimb.demo.entity.AccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRepository extends JpaRepository<AccessEntity, String> {
}
