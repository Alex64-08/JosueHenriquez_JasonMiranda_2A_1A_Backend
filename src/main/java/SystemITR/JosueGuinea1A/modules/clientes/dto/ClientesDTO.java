package SystemITR.JosueGuinea1A.modules.clientes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientesDTO {

    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String telefono;
    @NotBlank
    private String email;
    @NotBlank
    private String direccion;
}
