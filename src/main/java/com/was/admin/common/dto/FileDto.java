package com.was.admin.common.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
public class FileDto {

    private String path;
    private String oriName;
    private String newName;
    private Long size;

    public FileDto(MultipartFile file, String path) {
        this.path = path;
        this.size = file.getSize();
        this.oriName = file.getOriginalFilename();
        String[] arr = path.split("/");
        this.newName = arr[arr.length - 1];
    }
}
