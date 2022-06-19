package Application.DataAccessObject;


import Application.Models.Module;
import Application.Models.Tache;
import Application.Repository.ModuleRepository;
import Application.Repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class GestionDesTachesDAO {

    @Autowired
    private TacheRepository tacheRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private JavaMailSender emailSender;


    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("thaljeniomar@gmail.com ");
        message.setTo("thaljeniomar@gmail.com");
        message.setSubject("subject");
        message.setText("text");
        emailSender.send(message);
    }


    public Collection<Tache> RecuperListeTaches() {
        return tacheRepository.findAll();
    }


    public Collection<Tache> RecuperListeTachesModule(@PathVariable("id") String id) {
        return tacheRepository.findByModule_Id(id);
    }


    public Collection<Tache> RecuperListeUser(@PathVariable("id") String id) {
        return tacheRepository.findByUser_Id(id);
    }


    public ResponseEntity<?> AjouteTacheModule(@PathVariable("id") String id, @RequestBody Tache tache) {
        {
            Optional<Module> optionalModule = moduleRepository.findById(id);
            Tache t = new Tache(tache.getTitre(), tache.getDateDebut(), tache.getDateFin(), tache.getDescription(), "En cours", tache.getPriorite(), tache.getUser());
            tacheRepository.save(t);
            if (optionalModule.isPresent()) {
                Module m = optionalModule.get();
                if (m.getTacheList().size() > 0) {
                    List<Tache> tacheList = m.getTacheList();
                    tacheList.add(t);
                    m.setTacheList(tacheList);
                    moduleRepository.save(m);
                    t.setModule(m);
                } else {
                    List<Tache> tacheList = new ArrayList<>();
                    tacheList.add(t);
                    m.setTacheList(tacheList);
                    moduleRepository.save(m);
                    t.setModule(m);
                }
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("thaljeniomar@gmail.com");
                message.setTo("thaljeniomar@gmail.com");
                message.setSubject("Vous éte affecté a un tache" + t.getDescription() + " Qui fini le  " + t.getDateFin());
                message.setText("Mercii!!!");
                emailSender.send(message);
            }
            return new ResponseEntity<>(tacheRepository.save(t), HttpStatus.OK);
        }
    }


    public ResponseEntity<?> terminerTache(@PathVariable("id") String id, @RequestBody Tache tache) {
        Optional <Tache> tacheOptional = tacheRepository.findById(id);
        if(tacheOptional.isPresent())
            {
                Tache t = tacheOptional.get();
                t.setEtatTache(tache.getEtatTache());
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("thaljeniomar@gmail.com");
                message.setTo("thaljeniomar@gmail.com");
                message.setSubject("J'ai terminé mon tache !!!");
                message.setText("Mercii!!!");
                emailSender.send(message);
                return new ResponseEntity<>(tacheRepository.save(t), HttpStatus.OK);
            }
        return  ResponseEntity.ok("Tache ajouté !!");
    }

}