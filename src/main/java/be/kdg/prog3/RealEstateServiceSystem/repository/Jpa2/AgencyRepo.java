package be.kdg.prog3.RealEstateServiceSystem.repository.Jpa2;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Profile("jpa")
@Repository
public interface AgencyRepo extends JpaRepository<RealEstateAgency, UUID> {






    // Custom query with @Query annotation
    @Query("SELECT a FROM RealEstateAgency a WHERE a.city = :city")
    List<RealEstateAgency> findByCity(String city);
}
