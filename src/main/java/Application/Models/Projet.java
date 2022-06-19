package Application.Models;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "Projet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Projet {

    @Id
    private String id;

    @NotNull
    private String titre;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String description;

    private String etatModule;

    private String etatEquipe;

    @DBRef
    private User user;

    private String password;

    @JsonIgnore @DBRef
    private List<Module> moduleList;


    public Projet(String titre, LocalDate dateDebut, LocalDate dateFin, String description, User user, List<Module> moduleList) {
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.user = user;
        this.moduleList = moduleList;
    }
}
