package Application.Repository;

import Application.Models.Tache;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TacheRepository extends MongoRepository<Tache, String> {

    List<Tache> findByModule_Id (String id);

    List<Tache> findByUser_Id(String id);

}
