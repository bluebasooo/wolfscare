package ru.bluebasooo.wolfenscare.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bluebasooo.wolfenscare.dto.FileDto;
import ru.bluebasooo.wolfenscare.service.FilesService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/video")
@AllArgsConstructor
public class FileController {

    private FilesService filesService;

    @GetMapping("/info/{filename}")
    public FileDto getFileInfo(@PathVariable String filename) {
        return filesService.getFileInfo(filename);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<ResourceRegion> getChunkFileById(
            @PathVariable String fileId,
            @RequestHeader HttpHeaders headers) {
        var httpRange = headers.getRange().get(0);
        var start = httpRange.getRangeStart(headers.getContentLength());
        var end = httpRange.getRangeEnd(headers.getContentLength());
        System.out.println(start + " " + end);

        var videoPart = filesService.getChunk(fileId, start, end);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(videoPart);
    }

    @PostMapping(value = "/upload", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public FileDto uploadVideo(
            @RequestBody byte[] resource,
            @RequestParam String filename) {
        System.out.println(resource.length);
        return filesService.uploadFile(resource, filename);
    }
}
