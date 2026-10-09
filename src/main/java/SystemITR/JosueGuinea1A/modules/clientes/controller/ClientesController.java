package SystemITR.JosueGuinea1A.modules.clientes.controller;

import SystemITR.JosueGuinea1A.modules.clientes.dto.ClientesDTO;
import SystemITR.JosueGuinea1A.modules.clientes.services.ClientesService;
import SystemITR.JosueGuinea1A.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @CrossOrigin
@RequestMapping("/api/clientes")
public class ClientesController {
    private final ClientesService service;

    public ClientesController(ClientesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientesDTO>> nuevoCliente (@Valid @RequestBody ClientesDTO json) {
        try {
            ClientesDTO dto = service.crearCliente(json);
            ApiResponse<ClientesDTO> response = new ApiResponse<>(true,"Cliente registrado.",dto);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<ClientesDTO> respuestaError = new ApiResponse<>(false,"Error al registrar cliente.",json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }


}
