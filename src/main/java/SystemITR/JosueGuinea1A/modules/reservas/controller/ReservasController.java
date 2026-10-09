package SystemITR.JosueGuinea1A.modules.reservas.controller;

import SystemITR.JosueGuinea1A.modules.reservas.dto.ReservasDTO;
import SystemITR.JosueGuinea1A.modules.reservas.services.ReservasServices;
import SystemITR.JosueGuinea1A.modules.vehiculos.dto.VehiculosDTO;
import SystemITR.JosueGuinea1A.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/reservas")
public class ReservasController {

    private final ReservasServices service;

    public ReservasController(ReservasServices services) {
        this.service = services;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReservasDTO>> nuevoReservas (@Valid @RequestBody ReservasDTO json) {
        try {
            ReservasDTO dto = service.crearReservacion(json);
            ApiResponse<ReservasDTO> response = new ApiResponse<>(true,"Reservas registrado.",dto);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<ReservasDTO> respuestaError = new ApiResponse<>(false,"Error al registrar cliente.",json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReservasDTO>>> obtenerReservas() {
        try {
            List<ReservasDTO> lista = service.obtenerTodo();
            ApiResponse<List<ReservasDTO>> response = new ApiResponse<>(true,"Reservass consultados con exito.",lista);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<List<ReservasDTO>> respuestaError = new ApiResponse<>(false,"No se han encontrado los registros de los clientes.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReservasDTO>> obtenerReservasID(@PathVariable Long id) {
        try {
            ReservasDTO dto = service.obtenerId(id);
            ApiResponse<ReservasDTO> response = new ApiResponse<>(true,"Reservas consultado con exito.",dto);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<ReservasDTO> respuestaError = new ApiResponse<>(false,"No se ha encontrado el registro del cliente.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> EliminarReservas(@PathVariable Long id) {
        boolean eliminado = service.eliminarReserva(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse<ReservasDTO>> ActualizarReservas(@PathVariable Long id, ReservasDTO dto) {
//        try {
//            ReservasDTO registro = service.(id, dto);
//            ApiResponse<ReservasDTO> response = new ApiResponse<>(true,"Reservas eliminado con exito.",registro);
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            ApiResponse<ReservasDTO> respuestaError = new ApiResponse<>(false,"No se ha podido eliminar el registro del cliente.");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
//        }
//    }
}
