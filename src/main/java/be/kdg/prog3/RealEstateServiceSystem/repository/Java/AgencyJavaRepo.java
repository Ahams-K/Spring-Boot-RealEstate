package be.kdg.prog3.RealEstateServiceSystem.repository.Java;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;
import be.kdg.prog3.RealEstateServiceSystem.repository.AgencyRepository;
import be.kdg.prog3.RealEstateServiceSystem.repository.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Profile("default")
@Repository
public class AgencyJavaRepo implements AgencyRepository {
    private final DataFactory factory;

    @Autowired
    public AgencyJavaRepo(DataFactory factory) {
        this.factory = factory;

    }

    // Insert a new agency
    @Override
    public RealEstateAgency insertAgency(RealEstateAgency agency) {
        factory.getAgencies().add(agency);
        return agency;
    }

    // Retrieve all agencies
    @Override
    public List<RealEstateAgency> getAgencies() {
        return factory.getAgencies();
    }

    // Find an agency by its ID
    @Override
    public RealEstateAgency findAgencyById(UUID agencyID) {
        return factory.getAgencies().stream()
                .filter(agency -> agency.getAgencyID().equals(agencyID))
                .findFirst()
                .orElse(null);
    }

    // Update an existing agency
    @Override
    public RealEstateAgency updateAgency(RealEstateAgency agency) {
        if (agency == null || agency.getAgencyID() == null) {
            factory.getLogger().error("Agency or Agency ID is null, cannot update agency");
            throw new IllegalArgumentException("Agency or Agency ID is null");
        }

        for (int i = 0; i < factory.getAgencies().size(); i++) {
            if (factory.getAgencies().get(i).getAgencyID().equals(agency.getAgencyID())) {
                factory.getAgencies().set(i, agency);
                factory.getLogger().info("Agency with ID {} updated successfully", agency.getAgencyID());
                return agency;
            }
        }

        factory.getLogger().error("Agency with ID {} not found, cannot update", agency.getAgencyID());
        throw new IllegalArgumentException("Agency not found");
    }

    // Delete an agency by its ID
    @Override
    public void delete(UUID agencyID) {
        if (agencyID == null) {
            factory.getLogger().error("Agency ID is null, cannot delete agency");
            throw new IllegalArgumentException("Agency ID is null");
        }

        boolean removed = factory.getAgencies().removeIf(agency -> agency.getAgencyID().equals(agencyID));
        if (!removed) {
            factory.getLogger().error("Agency with ID {} not found, cannot delete", agencyID);
        } else {
            factory.getLogger().info("Agency with ID {} deleted successfully", agencyID);
        }
    }
}
