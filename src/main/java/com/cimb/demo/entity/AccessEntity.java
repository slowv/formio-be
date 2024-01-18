package com.cimb.demo.entity;

import com.cimb.demo.entity.enums.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "access")
public class AccessEntity extends AbstractAuditingEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @Comment("A permission type is required to associate an available permission with a given role.")
    private AccessType type;

    @ManyToOne
    private RoleEntity role;

    @ManyToMany(mappedBy = "access")
    private Set<FormEntity> formsAccess = new HashSet<>();

    @ManyToMany(mappedBy = "submissionAccess")
    private Set<FormEntity> formsSubmission = new HashSet<>();

    @ManyToMany(mappedBy = "access", cascade = CascadeType.ALL)
    private List<SubmissionEntity> submissions;
}
