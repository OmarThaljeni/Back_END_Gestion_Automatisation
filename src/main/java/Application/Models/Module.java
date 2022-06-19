package Application.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "Module")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Module {

    @Id
    private String id;

    private String titre;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String description;

    @DBRef
    private Projet projet;


    @JsonIgnore @DBRef
    private List<Tache> tacheList;

    public Module(String titre, LocalDate dateDebut, LocalDate dateFin, String description, Projet projet) {
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.projet = projet;
    }
}
