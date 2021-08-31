package com.midorlo.wolkenbruch.domain.system;

import com.midorlo.wolkenbruch.domain.ApplicationEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.TableColumnNames.*;
import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.TableNames.SYSTEM_SETTINGS;

@Entity
@Table(name = SYSTEM_SETTINGS)
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class SystemSetting extends ApplicationEntity {

   @Column(name = DESCRIPTION)
   private String description;

   @Column(name = VALUE, nullable = false)
   private String value;

   @Column(name = DATATYPE, nullable = false)
   private String datatype;
}
