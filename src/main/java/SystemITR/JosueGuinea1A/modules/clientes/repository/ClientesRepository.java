package SystemITR.JosueGuinea1A.modules.clientes.repository;

import SystemITR.JosueGuinea1A.modules.clientes.entity.ClientesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesEntity, Long> {
    boolean existsByemail(String email);
    Page<ClientesEntity> findAll(Pageable pageable);
}
