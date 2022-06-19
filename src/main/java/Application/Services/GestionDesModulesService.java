package Application.Services;


import Application.DataAccessObject.GestionDesModulesDAO;
import Application.Models.Module;
import Application.Models.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;

@Service
public class GestionDesModulesService {

    @Autowired
    private GestionDesModulesDAO gestionDesModulesDAO;

    public Collection<Module> RecuperListeModules() {
        return gestionDesModulesDAO.RecuperListeModules();
    }


    public ResponseEntity<?> AjouterModule(@PathVariable("id") String id, @Valid @RequestBody Module module) {
        return gestionDesModulesDAO.AjouterModule(id,module);
    }

    public Collection<Module> RecuperListeModulesProjet(@PathVariable("id") String id) {
        return gestionDesModulesDAO.RecuperListeModulesProjet(id);
    }






}
