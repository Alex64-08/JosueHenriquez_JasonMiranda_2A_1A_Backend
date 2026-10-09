package SystemITR.JosueGuinea1A.modules.reservas.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservasDTO {

    private Long id;
    @NotBlank
    private String nombreReserva;
    @NotBlank
    private LocalDate fechaReserva;
    @NotBlank
    private int cantPasajeros;
    @NotBlank
    private int cantDias;
    @NotBlank
    private String estado;
    @NotBlank
    private int totalPago;

    private Long idCliente;
    private String nombreCliente;

    private Long idVehiculo;
    private String placa;
}
