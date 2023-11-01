package ru.bluebasooo.wolfenscare.repository;

import org.apache.catalina.startup.ClassLoaderFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.bluebasooo.wolfenscare.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
