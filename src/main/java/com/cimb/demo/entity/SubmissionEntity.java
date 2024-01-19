package com.cimb.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
        name = "submissions",
        indexes = {
                @Index(columnList = "form_id")
        }
)
public class SubmissionEntity extends AbstractAuditingEntity<String> implements Serializable {

    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false)
    private FormEntity form;

//    @ManyToMany
//    @JoinTable(
//            name = "submission_role",
//            joinColumns = @JoinColumn(name = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "submission_id"))
//    private List<RoleEntity> role = new ArrayList<>();

    @Column(name = "access", columnDefinition = "json")
    private String access;

    @Column(name = "metadata", columnDefinition = "json")
    @Comment("Configurable metadata. Ex: {headers: connection: keep-alive}")
    private String metadata;

    @Column(name = "data", columnDefinition = "json", nullable = false)
    private String data = "{}";
}
