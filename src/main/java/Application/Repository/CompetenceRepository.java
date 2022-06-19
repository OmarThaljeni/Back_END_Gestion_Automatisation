package Application.Repository;

import Application.Models.Competence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetenceRepository extends MongoRepository<Competence, String> {


        }