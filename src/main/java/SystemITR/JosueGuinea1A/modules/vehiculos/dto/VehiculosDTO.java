package SystemITR.JosueGuinea1A.modules.vehiculos.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VehiculosDTO {

    private Long id;
    @NotBlank
    private String modelo;
    @NotBlank
    private String marca;
    @NotBlank
    private int capacidad;
    @NotBlank
    private int precio;
    @NotBlank
    private String placa;
}
