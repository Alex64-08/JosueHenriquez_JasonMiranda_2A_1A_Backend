package SystemITR.JosueGuinea1A.modules.reservas.services;

import SystemITR.JosueGuinea1A.modules.clientes.repository.ClientesRepository;
import SystemITR.JosueGuinea1A.modules.reservas.repository.ReservasRepository;
import SystemITR.JosueGuinea1A.modules.vehiculos.repository.VehiculosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ReservasServices {
    private final ReservasRepository repo;
    private final ClientesRepository repoClientes;
    private final VehiculosRepository repoVehiculo;
}
