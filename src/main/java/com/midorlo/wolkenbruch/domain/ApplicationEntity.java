package com.midorlo.wolkenbruch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.midorlo.wolkenbruch.domain.security.Account;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.TableColumnNames.*;

/**
 * Parent class for every entity within application scope
 * (with exception to {@link Account}, since its registered as the auditor aware)
 * Ensures a noArgs-Constructor, an ID-/ and a hashCode-Implementation.
 */
@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class ApplicationEntity {

    /**
     * Constructor over unique fields that aren't part of the primary key.
     *
     * @param name name field.
     */
    public ApplicationEntity(String name) {
        this.name = name;
    }

    /**
     * Identify every Entity using a Long ID.
     */
    @Id
    @Column(name = ID, nullable = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Identify every Entity using a unique name.
     */
    @Column(name = NAME, nullable = false, unique = true)
    @NotNull
    private String name;

    @CreatedBy
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = ID_ACCOUNT_CREATED, referencedColumnName = "id")
    @ToString.Exclude
    private Account createdBy;

    @LastModifiedBy
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = ID_ACCOUNT_CHANGED, referencedColumnName = "id")
    @ToString.Exclude
    private Account lastModifiedBy;

    @CreatedDate
    @Column(name = TIME_CREATED, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private Instant timeCreated = Instant.now();

    @LastModifiedDate
    @Column(name = TIME_CHANGED)
    @JsonIgnore
    @ToString.Exclude
    private Instant timeModified = Instant.now();

    /**
     * See https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @return true if <b>this, and the comparison object</b> are, or can be valid, distinct database records.
     */
    @Override
    public boolean equals(Object o) {
        return (this == o) || (
                o != null
                        && getClass().equals(o.getClass())
                        && getId() != null
                        && getName() != null
                        && getId().equals(((ApplicationEntity) o).getId())
                        && getName().equals(((ApplicationEntity) o).getName())
        );
    }

    /**
     * See https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
