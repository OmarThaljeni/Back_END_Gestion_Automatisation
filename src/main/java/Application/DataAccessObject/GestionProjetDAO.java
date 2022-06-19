package Application.DataAccessObject;

import Application.Models.Competence;
import Application.Models.Module;
import Application.Models.Projet;
import Application.Models.User;
import Application.Repository.ChefProjetRepository;
import Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Component
public class GestionProjetDAO {

    @Autowired
    private ChefProjetRepository chefProjetRepository;

    @Autowired
    private UserRepository userRepository;

    public Collection<Projet> RecuperListeProjet() {
        return chefProjetRepository.findAll();
    }

    public Collection<Projet> RecuperListeProjetEnAttente() {
        return chefProjetRepository.findByEtatEquipe("En attente");
    }


    public Collection<Projet> RecuperListeProjetUser(@PathVariable("id") String id) {
        List<Projet> projetList=  chefProjetRepository.findByUserId(id);
        List<Projet> listProjetFinal = new ArrayList<>();
        for(Projet projet : projetList)
            {
                if(projet.getEtatEquipe().equals("En attente"))
                    {
                        listProjetFinal.add(projet);
                    }
            }
        return listProjetFinal;
    }


    public Collection<Projet> RecuperListeProjetUserEtatModule(@PathVariable("id") String id) {
        List<Projet> projetList=  chefProjetRepository.findByUserId(id);
        List<Projet> listProjetFinal = new ArrayList<>();
        for(Projet projet : projetList)
        {
            if(projet.getEtatModule().equals("En attente"))
            {
                listProjetFinal.add(projet);
            }
        }
        return listProjetFinal;
    }


    public ResponseEntity<?> AjouterProjet(@Valid @RequestBody Projet projet) {
        Projet p = new Projet(projet.getTitre(),projet.getDateDebut(),projet.getDateFin(),projet.getDescription(),projet.getUser(),projet.getModuleList());
        List<Module> moduleList = new ArrayList<>();
        User u = projet.getUser();
        p.setEtatEquipe("En attente");
        p.setEtatModule("En attente");
        p.setModuleList(moduleList);
        chefProjetRepository.save(p);
        Optional<User> optionalUser = userRepository.findById(p.getUser().getId());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
                List<Projet> projetList = user.getProjetList();
                projetList.add(p);
                u.setProjetList(projetList);
                userRepository.save(u);
            }
        return new ResponseEntity<>(chefProjetRepository.save(p), HttpStatus.OK);
    }





    public ResponseEntity<?> ModifierProjet(@PathVariable("id") String id, @RequestBody Projet projet) {
        Optional<Projet> projetOptional = chefProjetRepository.findById(id);
        if (projetOptional.isPresent()) {
            Projet p = projetOptional.get();
            p.setTitre(projet.getTitre());
            p.setEtatModule("En attente");
            p.setEtatEquipe("En attente");
            p.setDateDebut(projet.getDateDebut());
            p.setDateFin(projet.getDateFin());
            p.setDescription(projet.getDescription());
            p.setUser(projet.getUser());
            User u = projet.getUser();
            chefProjetRepository.save(p);
            userRepository.save(u);
            List<Module> moduleList = new ArrayList<>();
            p.setModuleList(moduleList);
            return new ResponseEntity<>(chefProjetRepository.save(p), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    public ResponseEntity<?> SupprimerProjet(@PathVariable("id") String id) {
        try {
            chefProjetRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
