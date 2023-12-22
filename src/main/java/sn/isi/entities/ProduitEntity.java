package sn.isi.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="produit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProduitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 200)
    private String nom;
    @Column(nullable = false)
    private double qtStock;
    @ManyToOne
    private AppUserEntity appUserEntity;
}
