package SystemITR.JosueGuinea1A.modules.reservas.services;

import SystemITR.JosueGuinea1A.exceptions.DataNotFoundException;
import SystemITR.JosueGuinea1A.modules.clientes.entity.ClientesEntity;
import SystemITR.JosueGuinea1A.modules.clientes.repository.ClientesRepository;
import SystemITR.JosueGuinea1A.modules.reservas.dto.ReservasDTO;
import SystemITR.JosueGuinea1A.modules.reservas.entity.ReservasEntity;
import SystemITR.JosueGuinea1A.modules.reservas.repository.ReservasRepository;
import SystemITR.JosueGuinea1A.modules.vehiculos.entity.VehiculosEntity;
import SystemITR.JosueGuinea1A.modules.vehiculos.repository.VehiculosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class ReservasServices {
    private final ReservasRepository repo;
    private final ClientesRepository repoClientes;
    private final VehiculosRepository repoVehiculo;

    public ReservasDTO crearReservacion(ReservasDTO dto) {
        ReservasEntity entity = ConvertEntity(dto);
        ReservasEntity entitySave = repo.save(entity);
        return ConvertirDTO(entitySave);
    }

    private ReservasDTO ConvertirDTO(ReservasEntity entitySave) {
        ReservasDTO dto = new ReservasDTO();
        dto.setId(entitySave.getId());
        dto.setNombreReserva(entitySave.getNombreReserva());
        dto.setFechaReserva(entitySave.getFechaReserva());
        dto.setCantDias(entitySave.getCantDias());
        dto.setCantPasajeros(entitySave.getCantPasajeros());
        dto.setTotalPago(entitySave.getTotalPago());

        if(entitySave.getIdCliente() != null) {
            dto.setIdCliente(entitySave.getIdCliente().getId());
        } else {
            dto.setId(null);
        }

        if(entitySave.getIdVehiculo() != null) {
            dto.setIdVehiculo(entitySave.getIdVehiculo().getId());
        } else {
            dto.setId(null);
        }

        return dto;
    }

    private ReservasEntity ConvertEntity(ReservasDTO dto) {
        ReservasEntity entity = new ReservasEntity();
        entity.setNombreReserva(dto.getNombreReserva());
        entity.setFechaReserva(dto.getFechaReserva());
        entity.setCantDias(dto.getCantDias());
        entity.setCantPasajeros(dto.getCantPasajeros());
        entity.setTotalPago(dto.getTotalPago());

        if (dto.getIdCliente() != null) {
            ClientesEntity clientesEntity = repoClientes.findById(
                    dto.getIdCliente())
                            .orElseThrow(() -> new DataNotFoundException("Datos no encontrados."));
                    entity.setIdCliente(clientesEntity);
        }

        if (dto.getIdVehiculo() != null) {
            VehiculosEntity clientesEntity = repoVehiculo.findById(
                    dto.getIdVehiculo())
                            .orElseThrow(() -> new DataNotFoundException("Datos no encontrados."));
            entity.setIdVehiculo(clientesEntity);
        }

        return entity;
    }

    public List<ReservasDTO> obtenerTodo() {
        List<ReservasEntity> entidades = repo.findAll();
        List<ReservasDTO> dtos = new ArrayList<>();

        for (ReservasEntity entity : entidades) {
            dtos.add(ConvertirDTO(entity));
        }

        return dtos;
    }

    public ReservasDTO obtenerId(Long id) {
        Optional<ReservasEntity> entidadOptional = repo.findById(id);

        if (entidadOptional.isPresent()) {
            return ConvertirDTO(entidadOptional.get());
        }

        throw new DataNotFoundException("El ID del vehículo no existe.");
    }

//    public ReservasDTO actualizarReserva (Long id, ReservasDTO dto) {
//        ReservasEntity entity = repo.findById(id)
//                .orElseThrow(() -> new DataNotFoundException("No se encontró un regitro con ese ID."));
//
//        entity.setNombreReserva(dto.getNombreReserva());
//        entity.setFechaReserva(dto.getFechaReserva());
//        entity.setCantDias(dto.getCantDias());
//        entity.setCantPasajeros(dto.getCantPasajeros());
//        entity.setTotalPago(dto.getTotalPago());
//
//
//        ReservasEntity entityUpdated = repo.save(entity);
//        return ConvertirDTO(entityUpdated);
//    }

    public boolean eliminarReserva (Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
