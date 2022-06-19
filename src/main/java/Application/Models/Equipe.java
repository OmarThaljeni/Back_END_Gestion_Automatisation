package Application.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Equipe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Equipe {
    @Id
    private String id;

    private String nomEquipe;

    @DBRef
    private Projet projet;

    private List<User> equipeList;

    public Equipe(String nomEquipe, Projet projet, List<User> equipeList) {
        this.nomEquipe = nomEquipe;
        this.projet = projet;
        this.equipeList = equipeList;
    }
}
