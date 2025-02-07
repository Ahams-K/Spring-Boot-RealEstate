package be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;
import be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels.AgencyViewModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgencyServiceInterface {
    RealEstateAgency addAgency(AgencyViewModel viewModel);

    List<RealEstateAgency> getAgencyList();

    RealEstateAgency updateAgency(RealEstateAgency agency);

    void deleteAgency(UUID id);

    Optional<RealEstateAgency> findAgencyById(UUID id);
}
