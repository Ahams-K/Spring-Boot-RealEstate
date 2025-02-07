package be.kdg.prog3.RealEstateServiceSystem.repository;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;

import java.util.List;
import java.util.UUID;

public interface AgencyRepository {
    // Insert a new agency
    RealEstateAgency insertAgency(RealEstateAgency agency);

    // Retrieve all agencies
    List<RealEstateAgency> getAgencies();

    // Find an agency by its ID
    RealEstateAgency findAgencyById(UUID agencyID);

    // Update an existing agency
    RealEstateAgency updateAgency(RealEstateAgency agency);

    // Delete an agency by its ID
    void delete(UUID agencyID);
}
