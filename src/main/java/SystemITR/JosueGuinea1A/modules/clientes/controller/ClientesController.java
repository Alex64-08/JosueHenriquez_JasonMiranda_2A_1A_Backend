package SystemITR.JosueGuinea1A.modules.clientes.controller;

import SystemITR.JosueGuinea1A.modules.clientes.dto.ClientesDTO;
import SystemITR.JosueGuinea1A.modules.clientes.services.ClientesService;
import SystemITR.JosueGuinea1A.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientesDTO>>> obtenerClientes() {
        try {
            List<ClientesDTO> lista = service.obtenerTodo();
            ApiResponse<List<ClientesDTO>> response = new ApiResponse<>(true,"Clientes consultados con exito.",lista);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<List<ClientesDTO>> respuestaError = new ApiResponse<>(false,"No se han encontrado los registros de los clientes.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientesDTO>> obtenerClientesID(@PathVariable Long id) {
        try {
            ClientesDTO dto = service.obtenerId(id);
            ApiResponse<ClientesDTO> response = new ApiResponse<>(true,"Cliente consultado con exito.",dto);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<ClientesDTO> respuestaError = new ApiResponse<>(false,"No se ha encontrado el registro del cliente.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> EliminarCliente(@PathVariable Long id) {
        boolean eliminado = service.eliminarCliente(id);

        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientesDTO>> ActualizarCliente(@PathVariable Long id, ClientesDTO dto) {
        try {
            ClientesDTO registro = service.actualizarCliente(id, dto);
            ApiResponse<ClientesDTO> response = new ApiResponse<>(true,"Cliente eliminado con exito.",registro);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<ClientesDTO> respuestaError = new ApiResponse<>(false,"No se ha podido eliminar el registro del cliente.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }


}
