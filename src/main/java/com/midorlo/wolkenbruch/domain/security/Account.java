package com.midorlo.wolkenbruch.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.midorlo.wolkenbruch.domain.ApplicationEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.RelationColumnNames.*;
import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.RelationNames.ACCOUNTS_PRIVILEGES;
import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.RelationNames.ACCOUNTS_ROLES;
import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.TableColumnNames.ID;
import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.TableNames.accounts;

/**
 * Account Domain Object. Used for Spring Security and as an Auditor Reference <b>only</b>.
 */
@Entity
@Table(name = accounts)
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Account extends ApplicationEntity {


    @Column(name = "login", length = 254, unique = true, nullable = false)
    @NotNull
    private String login;

    @Column(name = "password", length = 60, nullable = false)
    @NotNull
    @JsonIgnore
    @ToString.Exclude
    private String passwordHash;

    @Column(name = "active", nullable = false)
    @NotNull
    private boolean active;

    @JoinTable(
            name = ACCOUNTS_PRIVILEGES,
            joinColumns = {@JoinColumn(name = ID_ACCOUNT, referencedColumnName = ID)},
            inverseJoinColumns = {@JoinColumn(name = ID_PRIVILEGE, referencedColumnName = ID)}
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Privilege> directPrivileges = new HashSet<>();

    @JoinTable(
            name = ACCOUNTS_ROLES,
            joinColumns = @JoinColumn(name = ID_ACCOUNT),
            inverseJoinColumns = @JoinColumn(name = ID_ROLE))
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new HashSet<>();

    public Account() {
    }

    @Override
    public Account setName(String name) {
        super.setName(name);
        return this;
    }

    public Account(String name, String login, String passwordHash, boolean active, Collection<Role> roles) {
        super(name);
        this.login        = login;
        this.passwordHash = passwordHash;
        this.active       = active;
        this.roles        = roles;
    }

    @Transient
    public Account addDirectPrivilege(Privilege privilege) {
        if (hasPrivilege(privilege)) {
            throw new RuntimeException("Cannot assign duplicate privilege " + privilege);
        }
        getDirectPrivileges().add(privilege);
        return this;
    }

    @Transient
    public Account addDirectPrivileges(Collection<Privilege> privileges) {
        privileges.forEach(this::addDirectPrivilege);
        return this;
    }

    @Transient
    public boolean hasExplicitPrivilegeD(Privilege p) {
        return getDirectPrivileges().contains(p);
    }

    @Transient
    public Account addRole(Role role) {
        if (hasRole(role)) {
            throw new RuntimeException("Cannot assign duplicate role " + role);
        }
        getRoles().add(role);
        return this;
    }

    @Transient
    public Account addRoles(Collection<Role> roles) {
        roles.forEach(this::addRole);
        return this;
    }

    @Transient
    public boolean hasRole(Role role) {
        return getRoles().contains(role);
    }

    @Transient
    public Set<Privilege> getAllPrivileges() {
        HashSet<Privilege> distinctPrivileges = new HashSet<>();
        getRoles().stream()
                  .map(Role::getPrivileges)
                  .flatMap(Collection::stream)
                  .forEach(distinctPrivileges::add);
        distinctPrivileges.addAll(getDirectPrivileges());
        return distinctPrivileges;
    }

    @Transient
    public boolean hasPrivilege(Privilege p) {
        return getAllPrivileges().contains(p);
    }
}
