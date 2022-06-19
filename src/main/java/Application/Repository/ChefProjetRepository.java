package Application.Repository;

import Application.Models.Projet;
import Application.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChefProjetRepository extends MongoRepository<Projet, String> {

    List<Projet> findByEtatEquipe(String etatProjet);

  //  @Query("{ 'etatProjet' : 'En attente' }")
    List<Projet> findByUserId(String id);

}
