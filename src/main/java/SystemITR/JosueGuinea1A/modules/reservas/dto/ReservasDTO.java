package SystemITR.JosueGuinea1A.modules.reservas.dto;

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

    private Long idVehiculo;
}
