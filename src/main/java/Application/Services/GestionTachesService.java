package Application.Services;

import Application.DataAccessObject.GestionDesTachesDAO;
import Application.Models.Module;
import Application.Models.Tache;
import Application.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

@Service
public class GestionTachesService {

    @Autowired
    private GestionDesTachesDAO desTachesDAO;

    public Collection<Tache> RecuperListeTaches() {
        return desTachesDAO.RecuperListeTaches();
    }

    public Collection<Tache> RecuperListeUser(@PathVariable("id") String id) {
        return desTachesDAO.RecuperListeUser(id);
    }

    public ResponseEntity<?> AjouteTacheModule(@PathVariable("id") String id, @RequestBody Tache tache) {
        {
            return desTachesDAO.AjouteTacheModule(id, tache);
        }

    }
    public Collection<Tache> RecuperListeTachesModule(@PathVariable("id") String id) {
        return desTachesDAO.RecuperListeTachesModule(id);
    }

    public ResponseEntity<?> terminerTache(@PathVariable("id") String id, @RequestBody Tache tache) {
        return desTachesDAO.terminerTache(id,tache);
    }


    }
