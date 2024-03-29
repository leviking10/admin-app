package sn.isi.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private int id;
    @NotNull(message = "Le nom ne doit pas etre null")
    private String nom;
    @NotNull(message = "Le prenom ne doit pas etre null")
    private String prenom;
    @NotNull(message = "L'email ne doit pas etre null")
    private String email;
    @NotNull(message = "Le mot de passe ne doit pas etre null")
    private String password;
    @NotNull
    private int etat;
    private Integer approleId;
}
