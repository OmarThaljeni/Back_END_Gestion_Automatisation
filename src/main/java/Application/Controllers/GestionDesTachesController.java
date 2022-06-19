package Application.Controllers;


import Application.Models.Tache;
import Application.Services.GestionTachesService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/GestionProjet")
public class GestionDesTachesController {

    @Autowired
    private GestionTachesService gestionTachesService;

    @GetMapping("/ListeDesTaches")
    public Collection<Tache> RecuperListeTaches() {
        return gestionTachesService.RecuperListeTaches();
    }


    @PostMapping("/AjouterTache/{id}")
    public ResponseEntity<?> AjouteTacheModule(@PathVariable("id") String id, @RequestBody Tache tache) {
        {
            return gestionTachesService.AjouteTacheModule(id,tache);
        }
    }

    @GetMapping("/ListeDesTachesModules/{id}")
    public Collection<Tache> RecuperListeTachesModule(@PathVariable("id") String id) {
        return gestionTachesService.RecuperListeTachesModule(id);
    }

    @GetMapping("/ListeDesTachesUsers/{id}")
    public Collection<Tache> RecuperListeUser(@PathVariable("id") String id) {
        return gestionTachesService.RecuperListeUser(id);
    }

    @PostMapping("/terminerTache/{id}")
    public ResponseEntity<?> terminerTache(@PathVariable("id") String id, @RequestBody Tache tache) {
        return gestionTachesService.terminerTache(id,tache);
    }



}
