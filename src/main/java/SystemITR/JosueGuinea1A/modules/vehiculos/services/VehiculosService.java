package SystemITR.JosueGuinea1A.modules.vehiculos.services;

import SystemITR.JosueGuinea1A.exceptions.DataNotFoundException;
import SystemITR.JosueGuinea1A.exceptions.DuplicateDataException;


import SystemITR.JosueGuinea1A.modules.vehiculos.dto.VehiculosDTO;
import SystemITR.JosueGuinea1A.modules.vehiculos.entity.VehiculosEntity;
import SystemITR.JosueGuinea1A.modules.vehiculos.repository.VehiculosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculosService {

    private final VehiculosRepository repo;

    public VehiculosDTO crearVehiculo(VehiculosDTO dto) {
        if (repo.existByPlaca(dto.getPlaca())) {
            throw new DuplicateDataException("Está placa ya está asociada con un vehículo.");
        }

        VehiculosEntity entity = ConvertirEntity(dto);
        VehiculosEntity entitySave = repo.save(entity);

        return ConvertirDTO(entitySave);
    }

    private VehiculosEntity ConvertirEntity(VehiculosDTO dto) {
        VehiculosEntity entity = new VehiculosEntity();
        entity.setModelo(dto.getModelo());
        entity.setMarca(dto.getMarca());
        entity.setCapacidad(dto.getCapacidad());
        entity.setPrecio(dto.getPrecio());
        entity.setPlaca(dto.getPlaca());

        return entity;
    }

    private VehiculosDTO ConvertirDTO(VehiculosEntity entitySave) {
        VehiculosDTO dto = new VehiculosDTO();
        dto.setId(entitySave.getId());
        dto.setModelo(entitySave.getModelo());
        dto.setMarca(entitySave.getMarca());
        dto.setCapacidad(entitySave.getCapacidad());
        dto.setPrecio(entitySave.getPrecio());
        dto.setPlaca(entitySave.getPlaca());

        return dto;
    }

    public List<VehiculosDTO> obtenerTodo() {
        List<VehiculosEntity> entidades = repo.findAll();
        List<VehiculosDTO> dtos = new ArrayList<>();

        for (VehiculosEntity entity : entidades) {
            dtos.add(ConvertirDTO(entity));
        }

        return dtos;
    }

    public VehiculosDTO obtenerId(Long id) {
        Optional<VehiculosEntity> entidadOptional = repo.findById(id);

        if (entidadOptional.isPresent()) {
            return ConvertirDTO(entidadOptional.get());
        }

        throw new DataNotFoundException("El ID del vehículo no existe.");
    }

    public VehiculosDTO actualizarVehiculo (Long id, VehiculosDTO dto) {
        VehiculosEntity entity = repo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("No se encontró un regitro con ese ID."));

        entity.setModelo(dto.getModelo());
        entity.setMarca(dto.getMarca());
        entity.setCapacidad(dto.getCapacidad());
        entity.setPrecio(dto.getPrecio());
        entity.setPlaca(dto.getPlaca());
        VehiculosEntity entityUpdated = repo.save(entity);
        return ConvertirDTO(entityUpdated);
    }

    public boolean eliminarVehiculo (Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
