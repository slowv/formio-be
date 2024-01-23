package com.cimb.demo.entity;

import com.cimb.demo.common.converter.ConverterFieldListString;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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
    @Convert(converter = ConverterFieldListString.class)
    private List<String> handler;

    @Column(name = "method", nullable = false)
    @Convert(converter = ConverterFieldListString.class)
    private List<String> method;

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

    @Column(name = "title")
    private String title;

    @Column(name = "settings")
    private String settings;
}
