package Application.DataAccessObject;

import Application.Models.Equipe;
import Application.Models.Projet;
import Application.Repository.ChefProjetRepository;
import Application.Repository.EquipeRepository;
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
public class GestionDesEquipesDAO {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private ChefProjetRepository chefProjetRepository;

    public Collection<Equipe> RecupererlisterEquipe() {
        return equipeRepository.findAll();
    }


    public ResponseEntity<?> AjouterEquipe(@RequestBody Equipe equipe) {
        Equipe e = new Equipe(equipe.getNomEquipe(),equipe.getProjet(),equipe.getEquipeList());
        Projet p = equipe.getProjet();
         p.setEtatEquipe("En cours");
        equipeRepository.save(e);
        chefProjetRepository.save(p);
        return new ResponseEntity<>(equipeRepository.save(e), HttpStatus.OK);
    }


    public ResponseEntity<?> ModifierEquipe(@PathVariable("id") String id, @RequestBody Equipe equipe) {
        Optional<Equipe> equipeOptional = equipeRepository.findById(id);
        if (equipeOptional.isPresent()) {
            Equipe e = equipeOptional.get();
            e.setNomEquipe(equipe.getNomEquipe());
            e.setProjet(equipe.getProjet());
            e.setEquipeList(equipe.getEquipeList());
            return new ResponseEntity<>(equipeRepository.save(e), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    public ResponseEntity<?> SupprimerEquipe(@PathVariable("id") String id) {
        try {
            equipeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    }


