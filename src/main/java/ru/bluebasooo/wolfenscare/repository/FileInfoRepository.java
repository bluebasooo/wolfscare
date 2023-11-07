package ru.bluebasooo.wolfenscare.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bluebasooo.wolfenscare.entity.FileGFS;

@Repository
public interface FileInfoRepository extends MongoRepository<FileGFS, ObjectId> {
    @Query("{'filename' : ?0}")
    FileGFS findOneByFilename(String filename);
}
