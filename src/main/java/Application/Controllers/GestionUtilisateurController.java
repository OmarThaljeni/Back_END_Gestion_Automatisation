package Application.Controllers;


import Application.Models.Requests.RegisterRequest;
import Application.Models.User;
import Application.Services.GestionUtilisateurServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/GestionProjet")
public class GestionUtilisateurController {

    @Autowired
    private GestionUtilisateurServices gestionUtilisateurServices;

    @GetMapping("/Liste_Users")
    public Collection<User> RecupererlisterUsers() {
        return gestionUtilisateurServices.RecupererlisterUsers();
    }

    @PostMapping("Ajouter_User")
    public ResponseEntity<?> AjouterUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return gestionUtilisateurServices.AjouterUser(registerRequest);
    }

    @PutMapping("Modifier_User/{id}")
    public ResponseEntity<?> ModifierUser(@PathVariable("id") String id, @RequestBody RegisterRequest registerRequest) {
        return gestionUtilisateurServices.ModifierUser(id,registerRequest);
    }


    @DeleteMapping("Supprimer_User/{id}")
    public ResponseEntity<?> SupprimerUser(@PathVariable("id") String id) {
        return this.gestionUtilisateurServices.SupprimerUser(id);
    }

    @GetMapping("/listeMembreEquipe")
    public Collection<User> RecupererlisterMembreEquipe() {
        return gestionUtilisateurServices.RecupererlisterMembreEquipe();
    }


    @GetMapping("/listeChefProjet")
    public Collection<User> RecupererlisteChefProjet() {
        return gestionUtilisateurServices.RecupererlisteChefProjet();
    }

}
