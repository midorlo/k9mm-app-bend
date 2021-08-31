package com.midorlo.wolkenbruch.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.midorlo.wolkenbruch.domain.ApplicationEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.TableNames.PRIVILEGES;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = PRIVILEGES)
@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class Privilege extends ApplicationEntity {

   public Privilege(String name) {
      super(name);
   }

   @ManyToMany(mappedBy = PRIVILEGES)
   @ToString.Exclude
   @JsonIgnore
   private Collection<Role> roles;
}
