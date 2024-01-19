package com.cimb.demo.repository;

import com.cimb.demo.entity.SubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<SubmissionEntity, String> {
}
