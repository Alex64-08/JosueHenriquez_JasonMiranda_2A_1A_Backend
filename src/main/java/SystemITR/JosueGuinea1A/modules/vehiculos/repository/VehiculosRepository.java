package SystemITR.JosueGuinea1A.modules.vehiculos.repository;

import SystemITR.JosueGuinea1A.modules.vehiculos.entity.VehiculosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculosRepository extends JpaRepository<VehiculosEntity, Long> {
    boolean existByPlaca(String placa);
    Page<VehiculosEntity> findAll(Pageable pageable);
}
