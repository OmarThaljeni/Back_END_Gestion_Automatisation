package Application.Controllers;


import Application.DataAccessObject.GestionDesEquipesDAO;
import Application.Models.Equipe;
import Application.Services.GestionDesEquipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/GestionProjet")
public class GestionEquipeController {

    @Autowired
    private GestionDesEquipesService gestionDesEquipesService;

    @GetMapping("/listerEquipe")
    public Collection<Equipe> RecupererlisterEquipe() {
        return gestionDesEquipesService.RecupererlisterEquipe();
    }

    @PostMapping("/AjouterEquipe")
    public ResponseEntity<?> AjouterEquipe(@RequestBody Equipe equipe) {
        return gestionDesEquipesService.AjouterEquipe(equipe);
    }

    @PutMapping("/ModifierEquipe/{id}")
    public ResponseEntity<?> ModifierEquipe(@PathVariable("id") String id, @RequestBody Equipe equipe) {
        return gestionDesEquipesService.ModifierEquipe(id,equipe);
    }

    @DeleteMapping("/SupprimerEquipe/{id}")
    public ResponseEntity<?> SupprimerEquipe(@PathVariable("id") String id) {
        return gestionDesEquipesService.SupprimerEquipe(id);
    }

}
