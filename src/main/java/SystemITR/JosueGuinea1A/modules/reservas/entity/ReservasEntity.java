package SystemITR.JosueGuinea1A.modules.reservas.entity;

import SystemITR.JosueGuinea1A.modules.clientes.entity.ClientesEntity;
import SystemITR.JosueGuinea1A.modules.vehiculos.entity.VehiculosEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "reservas")
public class ReservasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @Column(name = "nombre_reserva")
    private String nombreReserva;

    @Column(name = "fecha_reserva")
    private LocalDate fechaReserva;

    @Column(name = "cantidad_pasajeros")
    private int cantPasajeros;

    @Column(name = "cantidad_dia")
    private int cantDias;

    @Column(name = "estado")
    private String estado;

    @Column(name = "total_pago")
    private int totalPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private ClientesEntity idCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo")
    private VehiculosEntity idVehiculo;
}
