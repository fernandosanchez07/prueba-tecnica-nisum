package co.com.nisum.usuarios.infraestructure.exitpoints.h2database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UsuarioEntity {

    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String  nombre;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
