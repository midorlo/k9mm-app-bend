package com.midorlo.wolkenbruch.rest.storage;

import com.midorlo.wolkenbruch.service.storage.FilesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FilesController {

   private final FilesService filesService;

   public FilesController(FilesService filesService) {
      this.filesService = filesService;
   }
}
