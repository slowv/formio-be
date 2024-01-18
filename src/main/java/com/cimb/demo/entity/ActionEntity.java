package com.cimb.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(
        name = "actions",
        indexes = {
                @Index(columnList = "priority"),
                @Index(columnList = "form_id")
        }
)
public class ActionEntity extends AbstractAuditingEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "handler", nullable = false)
    private String handler;

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "machineName")
    private String machineName;

    @Column(name = "conditions")
    @Comment("Custom JavaScript or json condition logic")
    private String condition;

    @ManyToOne
    @JoinColumn(name = "form_id", referencedColumnName = "id", nullable = false)
    private FormEntity form;

    @Column(name = "priority", nullable = false)
    private float priority;

    @Column(name = "settings")
    private String settings;
}
