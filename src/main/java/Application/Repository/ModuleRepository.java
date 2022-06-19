package Application.Repository;

import Application.Models.Module;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ModuleRepository extends MongoRepository<Module, String> {


    List<Module> findByProjetId (String id);

}
