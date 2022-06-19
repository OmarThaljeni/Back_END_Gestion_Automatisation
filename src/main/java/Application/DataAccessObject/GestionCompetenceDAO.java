package Application.DataAccessObject;


import Application.Models.Competence;
import Application.Models.User;
import Application.Repository.CompetenceRepository;
import Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Component
public class GestionCompetenceDAO {

    @Autowired
    private CompetenceRepository competenceRepository;


    @Autowired
    private UserRepository userRepository;

    public Collection<Competence> RecupererlisterCompetences() {
        return competenceRepository.findAll();
    }


    public ResponseEntity<?> AjouterCompetence(@Valid @RequestBody Competence competence) {
        Competence c = new Competence(competence.getDescription(), competence.getTitre());
        competenceRepository.save(c);
        return new ResponseEntity<>(competenceRepository.save(c), HttpStatus.OK);
    }


    public ResponseEntity<?> ModifierCompetence(@PathVariable("id") String id, @RequestBody Competence competence) {
        Optional<Competence> competenceOptional = competenceRepository.findById(id);
        if (competenceOptional.isPresent()) {
            Competence c = competenceOptional.get();
            c.setDescription(competence.getDescription());
            c.setTitre(competence.getTitre());
            return new ResponseEntity<>(competenceRepository.save(c), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    public ResponseEntity<?> SupprimerCompetence(@PathVariable("id") String id) {
        try {
            competenceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> AjouterCompetenceUser(@PathVariable("id") String id, @RequestBody User user) {
        {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                User user1 = optionalUser.get();
                user1.setCompetenceList(user.getCompetenceList());
                return new ResponseEntity<>(userRepository.save(user1), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
    }

}
