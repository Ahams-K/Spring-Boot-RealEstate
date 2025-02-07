package be.kdg.prog3.RealEstateServiceSystem.repository.Jpa2;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Profile("jpa")
public interface AgentRepo extends JpaRepository <Agent, UUID>{


    List<Agent> findByLicenceNumber(String licenceNumber);

    List<Agent> findByAgentNameContainingIgnoreCase(String name);

    @Query("SELECT a FROM Agent a JOIN a.properties p WHERE p.propertyID = :propertyID")
    List<Agent> findAgentsByPropertyId(UUID propertyID);
}
