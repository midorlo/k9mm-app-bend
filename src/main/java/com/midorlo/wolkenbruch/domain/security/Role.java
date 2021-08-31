package com.midorlo.wolkenbruch.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.midorlo.wolkenbruch.domain.ApplicationEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.RelationColumnNames.ID_PRIVILEGE;
import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.RelationColumnNames.ID_ROLE;
import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.RelationNames.ROLES_TO_PRIVILEGES;
import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.TableNames.ROLES;

@Entity
@Getter
@Setter
@ToString
@Table(name = ROLES)
@NoArgsConstructor
public class Role extends ApplicationEntity {

    @ManyToMany(mappedBy = ROLES, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Collection<Account> accounts = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = ROLES_TO_PRIVILEGES, joinColumns = @JoinColumn(name = ID_ROLE), inverseJoinColumns =
    @JoinColumn(name = ID_PRIVILEGE))
    private Collection<Privilege> privileges = new HashSet<>();

    public Role(String name) {
        super(name);
    }

    public Role(String name, Collection<Privilege> privileges) {
        super(name);
        this.privileges = privileges;
    }
}
