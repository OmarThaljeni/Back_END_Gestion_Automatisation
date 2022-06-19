package Application.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Tache")
@NoArgsConstructor
@AllArgsConstructor @Getter @Setter
public class Tache {

    @Id
    private String id;

    private String titre;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String description;

    private String etatTache;

    private int priorite;

    @DBRef
    private Module module;

    @DBRef
    private User user;

    public Tache(String titre, LocalDate dateDebut, LocalDate dateFin, String description, String etatTache, int priorite, User user) {
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.etatTache = etatTache;
        this.priorite = priorite;
        this.user = user;
    }

}
