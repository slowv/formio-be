package com.cimb.demo.repository;

import com.cimb.demo.entity.SubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<SubmissionEntity, String> {
    @Query(
            nativeQuery = true,
            value = """
                    select s.data
                    from submissions s
                    where s.created_date in (select max(created_date)
                                             from submissions
                                             where JSON_EXTRACT(data, concat('$.', :key)) = :value
                                               and form_id = :formId
                                             group by code, form_id)
                    """
    )
    List<String> uniqueValue(
            @Param("key") String key,
            @Param("value") Object value,
            @Param("formId") String formId
    );
}
