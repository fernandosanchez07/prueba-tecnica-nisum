package co.com.nisum.usuarios.infraestructure.exitpoints.h2database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact_phone")
public class ContactoTelefonoEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;
    @Column(name = "id_user")
    private UUID idUser;
    @Column(name = "number_phone")
    private String numberPhone;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
}
