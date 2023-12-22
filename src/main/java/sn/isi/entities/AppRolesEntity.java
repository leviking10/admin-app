package sn.isi.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "approle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppRolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 100)
    private String nom;
    @ManyToMany(mappedBy = "appRoleEntities")
    private List<AppUserEntity> appUserEntity;
}
