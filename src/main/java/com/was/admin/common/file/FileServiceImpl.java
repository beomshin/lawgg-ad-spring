package com.was.admin.common.file;

import com.was.admin.common.dto.FileDto;
import com.was.admin.common.utils.AwsS3Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileServiceImpl implements FileService<FileDto> {

    private final AwsS3Utils awsS3Utils;
    private final int IMAGE_MAX_SIZE = 3 * 1024 * 1024; // 3MB
    private final int VIDEO_MAX_SIZE = 100 * 1024 * 1024; // 15MB
    private final int REPLAY_MAX_SIZE = 50 * 1024 * 1024; // 15MB
    private Set<String> fileExt;
    private Set<String> videoExt;
    private Set<String> replayExt;
    public FileServiceImpl(AwsS3Utils awsS3Utils) {
        this.awsS3Utils = awsS3Utils;
        this.fileExt = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp").stream().collect(Collectors.toSet());
        this.videoExt = Arrays.asList(".mp4", ".avi", ".wmv", ".mpg", ".mkv", ".webm").stream().collect(Collectors.toSet());
        this.replayExt = Arrays.asList(".rofl").stream().collect(Collectors.toSet());
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> uploadFiles = new ArrayList<>();
        files.stream().forEach(file -> {
            try {
                String ext = getFileExtension(file.getOriginalFilename());
                if (file.getSize() < IMAGE_MAX_SIZE && fileExt.contains(ext)) uploadFiles.add(getFileDto(file, ext));
            } catch (Exception e) {
                log.error("{}", file);
                log.error("{}", e);
            }
        });
        return uploadFiles;
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {
        String ext = getFileExtension(file.getOriginalFilename());
        if (file.getSize() >= IMAGE_MAX_SIZE) return null;
        else if (!fileExt.contains(ext)) return null;
        return getFileDto(file, ext);
    }

    @Override
    public FileDto uploadVideo(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        String ext = getFileExtension(file.getOriginalFilename());
        if (file.getSize() >= VIDEO_MAX_SIZE) return null;
        else if (!videoExt.contains(ext)) return null;
        return getFileDto(file, ext);
    }

    @Override
    public FileDto uploadReplay(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        String ext = getFileExtension(file.getOriginalFilename());
        if (file.getSize() >= REPLAY_MAX_SIZE) return null;
        else if (!replayExt.contains(ext)) return null;
        return getFileDto(file, ext);
    }


    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            log.error("[잘못된 형식의 파일] ================> ");
            return null;
        }
    }

    private FileDto getFileDto(MultipartFile file, String ext) {
        String path = awsS3Utils.fileUploadToS3(file, ext);
        if (path == null) return null;
        return new FileDto(file, path);
    }

}
