package com.cimb.demo.repository;

import com.cimb.demo.entity.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<ActionEntity, String> {
}
