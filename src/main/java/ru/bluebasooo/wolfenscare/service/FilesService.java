package ru.bluebasooo.wolfenscare.service;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.stereotype.Service;
import ru.bluebasooo.wolfenscare.dto.FileDto;
import ru.bluebasooo.wolfenscare.entity.FileGFS;
import ru.bluebasooo.wolfenscare.repository.FileInfoRepository;
import ru.bluebasooo.wolfenscare.repository.FileRepository;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class FilesService {

    private FileRepository fileRepository;
    private FileInfoRepository fileInfoRepository;

    private static final long CHUNK = 1024 * 1024; //1 mb

    public FileDto getFileInfo(String filename) {
        return fileGFSToFileDto.apply(
                fileInfoRepository.findOneByFilename(filename));
    }

//    public byte[] getFileChunk(String fileId, long from) {
//        return fileRepository.getChunkFile(fileId, from);
//    }

    public ResourceRegion getChunk(String fileId, long start, long end) {
        var len = Math.min(CHUNK, end - start + 1);
        var inStream = fileRepository.getFile(fileId);

        return new ResourceRegion(inStream, start, len);
    }

    public FileDto uploadFile(InputStreamResource resource, String filename) {
        return fileRepository.uploadFile(resource, filename);
    }

    private static final Function<FileGFS, FileDto> fileGFSToFileDto
            = (filePresentation) -> FileDto.builder()
                    .id(filePresentation.getId().toString())
                    .length(filePresentation.getLength())
                    .build();
}
