package ru.bluebasooo.wolfenscare.repository;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;
import ru.bluebasooo.wolfenscare.dto.FileDto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FileRepository {

    private GridFsTemplate gridFsTemplate;

//    public byte[] getChunkFile(String id, int from) {
//        var file = Optional.ofNullable(
//                gridFsTemplate.findOne(
//                        new Query(Criteria.where("_id").is(id))))
//                .orElseThrow(() -> new RuntimeException("File not found"));
//
//        int len = from + CHUNK > file.getLength() ? (int)file.getLength() - from : CHUNK;
//
//        var payload = new byte[len];
//
//        try {
//            gridFsTemplate.getResource(file).getContent().read(payload, from, len);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        return payload;
//    }

    public InputStreamResource getFile(String id) {
        var file = Optional.ofNullable(
                        gridFsTemplate.findOne(
                                new Query(Criteria.where("_id").is(id))))
                .orElseThrow(() -> new RuntimeException("File not found"));

        return gridFsTemplate.getResource(file);
    }

    public FileDto uploadFile(byte[] file, String filename) {
            ObjectId id = gridFsTemplate.store(new ByteArrayInputStream(file), filename);
            return FileDto.builder()
                    .id(id.toString())
                    .length((long)file.length)
                    .build();
    }
}
