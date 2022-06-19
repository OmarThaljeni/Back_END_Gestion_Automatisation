package Application.Services;

import Application.DataAccessObject.GestionCompetenceDAO;
import Application.Models.Competence;
import Application.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Service
public class CompetenceService {

    @Autowired
    private GestionCompetenceDAO gestionCompetenceDAO;

    public Collection<Competence> RecupererlisterCompetences() {
        return gestionCompetenceDAO.RecupererlisterCompetences();
    }


    public ResponseEntity<?> AjouterCompetence(@Valid @RequestBody Competence competence) {
        return gestionCompetenceDAO.AjouterCompetence(competence);

    }


    public ResponseEntity<?> ModifierCompetence(@PathVariable("id") String id, @RequestBody Competence competence) {
        return gestionCompetenceDAO.ModifierCompetence(id,competence);
    }


    public ResponseEntity<?> SupprimerCompetence(@PathVariable("id") String id) {
        return gestionCompetenceDAO.SupprimerCompetence(id);
    }


    public ResponseEntity<?> AjouterCompetenceUser(@PathVariable("id") String id, @RequestBody User user) {
        return gestionCompetenceDAO.AjouterCompetenceUser(id,user);
    }



}
