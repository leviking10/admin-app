package sn.isi.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppRolesDto {
    private int id;
    @NotNull(message = "Le nom ne doit pas etre null")
    private String nom;

}
