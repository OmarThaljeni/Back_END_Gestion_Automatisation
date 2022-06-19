package Application.Controllers;

import Application.Models.Module;
import Application.Services.GestionDesModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/GestionProjet")
public class GestionModuleController {

    @Autowired
    private GestionDesModulesService gestionDesModulesService;


    @GetMapping("ListeModules")
    public Collection<Module> RecuperListeModules() {
        return gestionDesModulesService.RecuperListeModules();
    }

    @PostMapping("AjouterModule/{id}")
    public ResponseEntity<?> AjouterModule(@PathVariable("id") String id, @Valid @RequestBody Module module) {
        return gestionDesModulesService.AjouterModule(id,module);
    }

    @GetMapping("ListeModuleProjet/{id}")
    public Collection<Module> RecuperListeModulesProjet(@PathVariable("id") String id) {
        return gestionDesModulesService.RecuperListeModulesProjet(id);
    }

    }
