package com.cimb.demo.entity;

import com.cimb.demo.entity.enums.FormType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(
        name = "forms",
        indexes = {
                @Index(columnList = "type"),
                @Index(columnList = "tags")
        }
)
public class FormEntity extends AbstractAuditingEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "title", nullable = false)
    @Comment("The title for the form.")
    private String title;

    @Column(name = "name", nullable = false)
    @Comment("The machine name for this form.")
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("The form type.")
    private FormType type = FormType.FORM;

    @Column(name = "display")
    @Comment("The display method for this form.")
    private String display;

    @Column(name = "action")
    private String action;

    @Column(name = "tags")
    private String tags;

    @Column(name = "components", columnDefinition = "json")
    private String components;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "form_access",
            joinColumns = @JoinColumn(name = "access_id"),
            inverseJoinColumns = @JoinColumn(name = "forms_id"))
    private Set<AccessEntity> access = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "form_submission",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "form_id"))
    private Set<AccessEntity> submissionAccess = new HashSet<>();

    @Column(name = "settings", columnDefinition = "json")
    @Comment("Custom form settings json object.")
    private String settings;

    @Column(name = "properties", columnDefinition = "json")
    @Comment("Custom form json properties.")
    private String properties;

    @OneToMany(mappedBy = "form")
    private List<ActionEntity> actions = new ArrayList<>();
}
