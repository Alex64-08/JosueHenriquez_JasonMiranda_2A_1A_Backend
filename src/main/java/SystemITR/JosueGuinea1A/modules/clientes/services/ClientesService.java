package SystemITR.JosueGuinea1A.modules.clientes.services;

import SystemITR.JosueGuinea1A.exceptions.DataNotFoundException;
import SystemITR.JosueGuinea1A.exceptions.DuplicateDataException;
import SystemITR.JosueGuinea1A.modules.clientes.dto.ClientesDTO;
import SystemITR.JosueGuinea1A.modules.clientes.entity.ClientesEntity;
import SystemITR.JosueGuinea1A.modules.clientes.repository.ClientesRepository;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientesService {
    private final ClientesRepository repo;

    public ClientesDTO crearCliente(ClientesDTO dto) {
        if (repo.existsByemail(dto.getEmail())) {
            throw new DuplicateDataException("Este correo ya se ha registrado.");
        }

        ClientesEntity entity = ConvertirEntity(dto);
        ClientesEntity entitySave = repo.save(entity);

        return ConvertirDTO(entitySave);
    }

    private ClientesEntity ConvertirEntity(ClientesDTO dto) {
        ClientesEntity entity = new ClientesEntity();
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
        entity.setDireccion(dto.getDireccion());

        return entity;
    }

    private ClientesDTO ConvertirDTO(ClientesEntity entitySave) {
        ClientesDTO dto = new ClientesDTO();
        dto.setId(entitySave.getId());
        dto.setNombre(entitySave.getNombre());
        dto.setApellido((entitySave.getApellido()));
        dto.setTelefono(entitySave.getTelefono());
        dto.setEmail(entitySave.getEmail());
        dto.setDireccion(entitySave.getDireccion());

        return dto;
    }

    public List<ClientesDTO> obtenerTodo() {
        List<ClientesEntity> entidades = repo.findAll();
        List<ClientesDTO> dtos = new ArrayList<>();

        for (ClientesEntity entity : entidades) {
            dtos.add(ConvertirDTO(entity));
        }

        return dtos;
    }

    public ClientesDTO obtenerId(Long id) {
        Optional<ClientesEntity> entidadOptional = repo.findById(id);

        if (entidadOptional.isPresent()) {
            return ConvertirDTO(entidadOptional.get());
        }

        throw new DataNotFoundException("El ID de cliente no existe.");
    }

    public ClientesDTO actualizarCliente (Long id, ClientesDTO dto) {
        ClientesEntity entity = repo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("No se encontró un regitro con ese ID."));

        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
        entity.setDireccion(dto.getDireccion());
        ClientesEntity entityUpdated = repo.save(entity);
        return ConvertirDTO(entityUpdated);
    }

    public boolean eliminarCliente (Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
