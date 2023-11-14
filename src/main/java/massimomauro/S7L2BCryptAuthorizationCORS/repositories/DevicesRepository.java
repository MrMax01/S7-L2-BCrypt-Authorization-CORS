package massimomauro.S7L2BCryptAuthorizationCORS.repositories;


import massimomauro.S7L2BCryptAuthorizationCORS.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevicesRepository extends JpaRepository<Device, Integer> {
}
