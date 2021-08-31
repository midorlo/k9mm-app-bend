package com.midorlo.wolkenbruch.repository.storage;

import com.midorlo.wolkenbruch.domain.storage.FileType;
import com.midorlo.wolkenbruch.repository.ApplicationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileTypeRepository extends ApplicationRepository<FileType> {
}
