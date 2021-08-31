package com.midorlo.wolkenbruch.domain.storage;


import com.midorlo.wolkenbruch.domain.ApplicationEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.TableColumnNames.LOCATION;
import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.TableNames.FOLDER_REFERENCES;

@Entity
@Table(name = FOLDER_REFERENCES)
@Getter
@Setter
@ToString
public class FolderReference extends ApplicationEntity {

   @Column(name = LOCATION, nullable = false, unique = true)
   private String location;

   @Override
   public boolean equals(Object o) {
      return (o instanceof FolderReference) && getId() != null && getId().equals(((FolderReference) o).getId());
   }
}
