package com.midorlo.wolkenbruch.repository.storage;

import com.midorlo.wolkenbruch.domain.storage.FileReference;
import com.midorlo.wolkenbruch.repository.ApplicationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileReferenceRepository extends ApplicationRepository<FileReference> {
}
