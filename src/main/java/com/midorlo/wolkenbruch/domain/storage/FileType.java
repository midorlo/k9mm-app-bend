package com.midorlo.wolkenbruch.domain.storage;

import com.midorlo.wolkenbruch.domain.ApplicationEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

import static com.midorlo.wolkenbruch.configuration.ApplicationConstants.TableNames.FILE_TYPES;

@Entity
@Table(name = FILE_TYPES)
@Getter
@Setter
@ToString
public class FileType extends ApplicationEntity {
}
