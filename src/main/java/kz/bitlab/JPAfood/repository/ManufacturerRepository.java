package kz.bitlab.JPAfood.repository;

import jakarta.transaction.Transactional;
import kz.bitlab.JPAfood.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
