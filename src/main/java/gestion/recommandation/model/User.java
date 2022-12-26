package gestion.recommandation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String username;
    private String prenom;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Recommandation> recommandations;


    public User(Long id, String nom, String username, String prenom, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.username = username;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }
}
