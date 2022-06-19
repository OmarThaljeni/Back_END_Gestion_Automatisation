package Application.Controllers;


import Application.DataAccessObject.GestionCompetenceDAO;
import Application.Models.Competence;
import Application.Models.User;
import Application.Services.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/GestionProjet")
public class GestionCompetenceController {

    @Autowired
    private CompetenceService competenceService;

    @GetMapping("/listerCompetences")
    public Collection<Competence> RecupererlisterCompetences() {
        return competenceService.RecupererlisterCompetences();
    }

    @PostMapping("/AjouterCompetence")
    public ResponseEntity<?> AjouterCompetence(@Valid @RequestBody Competence competence) {
        return competenceService.AjouterCompetence(competence);

    }

    @PutMapping("/ModifierCompetence/{id}")
    public ResponseEntity<?> ModifierCompetence(@PathVariable("id") String id, @RequestBody Competence competence) {
        return competenceService.ModifierCompetence(id,competence);
    }

    @DeleteMapping("/SupprimerCompetence/{id}")
    public ResponseEntity<?> SupprimerCompetence(@PathVariable("id") String id) {
        return competenceService.SupprimerCompetence(id);
    }


    @PutMapping("AjouterCompetenceUser/{id}")
    public ResponseEntity<?> AjouterCompetenceUser(@PathVariable("id") String id, @RequestBody User user) {
        return competenceService.AjouterCompetenceUser(id,user);
    }


}
