package Application.DataAccessObject;

import Application.Models.Module;
import Application.Models.Projet;
import Application.Models.Tache;
import Application.Repository.ChefProjetRepository;
import Application.Repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class GestionDesModulesDAO {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ChefProjetRepository chefProjetRepository;

    public Collection<Module> RecuperListeModules() {
        return moduleRepository.findAll();
    }


    public ResponseEntity<?> AjouterModule(@PathVariable("id") String id, @Valid @RequestBody Module module) {
        Module m = new Module(module.getTitre(),module.getDateDebut(),module.getDateFin(),module.getDescription(),module.getProjet());
        Optional<Projet> optionalProjet = chefProjetRepository.findById(id);
        List<Tache> tacheList = new ArrayList<>();
        m.setTacheList(tacheList);
        moduleRepository.save(m);
        if(optionalProjet.isPresent())
        {
             Projet p = optionalProjet.get();
          //   p.setEtatModule("En cours");
            List<Module> listModule = p.getModuleList();
            listModule.add(m);
                p.setModuleList(listModule);
                chefProjetRepository.save(p);

        }
        return new ResponseEntity<>(moduleRepository.save(m), HttpStatus.OK);
    }


    public Collection<Module> RecuperListeModulesProjet(@PathVariable("id") String id) {

        return moduleRepository.findByProjetId(id);
    }


}
