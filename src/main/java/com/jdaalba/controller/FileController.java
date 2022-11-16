package com.jdaalba.controller;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FileController {

  private String FILE_PATH_ROOT = "src/main/resources/static/images/";

  @GetMapping("/images/{filename}")
  public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
    log.info("Buscando imagen {}", filename);
    byte[] image = new byte[0];
    try {
      image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT + filename));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
  }
}
