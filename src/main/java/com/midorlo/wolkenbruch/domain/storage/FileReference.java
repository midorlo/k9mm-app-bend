package com.midorlo.wolkenbruch.domain.storage;

import com.midorlo.wolkenbruch.configuration.ApplicationConstants;
import com.midorlo.wolkenbruch.domain.ApplicationEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.TableNames.FILE_REFERENCES;

@Entity
@Table(name = FILE_REFERENCES)
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class FileReference extends ApplicationEntity {


   @Column(name = ApplicationConstants.TableColumnNames.NAME_ORIGINAL, nullable = false, unique = true)
   private String nameOriginal;

   @Column(name = ApplicationConstants.TableColumnNames.EXTENSION)
   private String extension;

   @Column(name = ApplicationConstants.TableColumnNames.SIZE_BYTES, nullable = false)
   private Long sizeBytes;

   @Column(name = ApplicationConstants.TableColumnNames.LOCATION, nullable = false, unique = true)
   private String location;
}
