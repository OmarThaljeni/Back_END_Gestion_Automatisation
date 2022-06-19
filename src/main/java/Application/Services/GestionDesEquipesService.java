package Application.Services;

import Application.DataAccessObject.GestionDesEquipesDAO;
import Application.Models.Equipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;

@Service
public class GestionDesEquipesService {
    @Autowired
    private GestionDesEquipesDAO gestionDesEquipesDAO;

    public Collection<Equipe> RecupererlisterEquipe() {
        return gestionDesEquipesDAO.RecupererlisterEquipe();
    }

    public ResponseEntity<?> AjouterEquipe(@RequestBody Equipe equipe) {
        return gestionDesEquipesDAO.AjouterEquipe(equipe);
    }
    public ResponseEntity<?> ModifierEquipe(@PathVariable("id") String id, @RequestBody Equipe equipe) {
        return gestionDesEquipesDAO.ModifierEquipe(id,equipe);
    }

    public ResponseEntity<?> SupprimerEquipe(@PathVariable("id") String id) {
        return gestionDesEquipesDAO.SupprimerEquipe(id);
    }


}
