package SystemITR.JosueGuinea1A.modules.vehiculos.controller;
import SystemITR.JosueGuinea1A.modules.vehiculos.dto.VehiculosDTO;
import SystemITR.JosueGuinea1A.modules.vehiculos.services.VehiculosService;
import SystemITR.JosueGuinea1A.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/vehiculos")
public class VehiculosController {
    private final VehiculosService service;

    public VehiculosController(VehiculosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VehiculosDTO>> nuevoVehiculos (@Valid @RequestBody VehiculosDTO json) {
        try {
            VehiculosDTO dto = service.crearVehiculo(json);
            ApiResponse<VehiculosDTO> response = new ApiResponse<>(true,"Vehiculos registrado.",dto);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<VehiculosDTO> respuestaError = new ApiResponse<>(false,"Error al registrar cliente.",json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VehiculosDTO>>> obtenerVehiculoss() {
        try {
            List<VehiculosDTO> lista = service.obtenerTodo();
            ApiResponse<List<VehiculosDTO>> response = new ApiResponse<>(true,"Vehiculoss consultados con exito.",lista);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<List<VehiculosDTO>> respuestaError = new ApiResponse<>(false,"No se han encontrado los registros de los clientes.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VehiculosDTO>> obtenerVehiculossID(@PathVariable Long id) {
        try {
            VehiculosDTO dto = service.obtenerId(id);
            ApiResponse<VehiculosDTO> response = new ApiResponse<>(true,"Vehiculos consultado con exito.",dto);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<VehiculosDTO> respuestaError = new ApiResponse<>(false,"No se ha encontrado el registro del cliente.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> EliminarVehiculos(@PathVariable Long id) {
        boolean eliminado = service.eliminarVehiculo(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VehiculosDTO>> ActualizarVehiculos(@PathVariable Long id, VehiculosDTO dto) {
        try {
            VehiculosDTO registro = service.actualizarVehiculo(id, dto);
            ApiResponse<VehiculosDTO> response = new ApiResponse<>(true,"Vehiculos eliminado con exito.",registro);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<VehiculosDTO> respuestaError = new ApiResponse<>(false,"No se ha podido eliminar el registro del cliente.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}
