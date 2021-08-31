package com.midorlo.wolkenbruch.model.mapper;

import com.midorlo.wolkenbruch.domain.storage.FileReference;
import com.midorlo.wolkenbruch.model.storage.FileUploadDto;

import java.nio.file.Path;

public class FileReferenceMapper {

   public static FileReference toFileReference(FileUploadDto dto, Path fileLocation) {
      FileReference fileReference = new FileReference();
      fileReference.setNameOriginal(dto.getTargetFileName());
      fileReference.setName(dto.getOriginalFileName());
      fileReference.setExtension(dto.getExtension());
      fileReference.setLocation(fileLocation.toFile().getAbsolutePath());
      fileReference.setSizeBytes(dto.getSizeBytes());
      return fileReference;
   }
}
