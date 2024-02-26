package sn.isi.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appuser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 150)
    private String nom;
    @Column(nullable = false, length = 200)
    private String prenom;
    @Column(nullable = false, length = 200)
    private String email;
    @Column(nullable = false, length = 200)
    private String password;
    private int etat;
    @ManyToOne
    @JoinColumn(name = "approle_id")
    private AppRolesEntity appRoleEntity;
    @OneToMany(mappedBy = "appUserEntity")
    private List<ProduitEntity> produitEntities;
}
