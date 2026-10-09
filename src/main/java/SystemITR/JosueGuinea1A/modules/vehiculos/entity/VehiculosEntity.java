package SystemITR.JosueGuinea1A.modules.vehiculos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "vehiculos")
public class VehiculosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Long id;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "marca")
    private String marca;

    @Column(name = "capacidad")
    private int capacidad;

    @Column(name = "precio_dia")
    private int precio;

    @Column(name = "placa")
    private String placa;
}
