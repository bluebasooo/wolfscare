package ru.bluebasooo.wolfenscare.entity;

import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "fs.files")
public class FileGFS {
    @Id
    ObjectId id;
    DBObject metadata;
    String filename;
    Long chunkSize;
    LocalDateTime uploadDate;
    Long length;
    String contentType;
    String md5;
}
