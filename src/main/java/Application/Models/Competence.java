package Application.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Competence")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Competence {

    @Id
    private String id;

    private String description;

    private String titre;

    @DBRef
    private User user;

    public Competence(String description, String titre) {
        this.description = description;
        this.titre = titre;
    }
}
