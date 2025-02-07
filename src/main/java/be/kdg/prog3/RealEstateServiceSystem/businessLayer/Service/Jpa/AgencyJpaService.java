package be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.Jpa;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.AgencyServiceInterface;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;
import be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels.AgencyViewModel;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jpa2.AgencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("jpa")
@Service
public class AgencyJpaService implements AgencyServiceInterface {

    private final AgencyRepo agencyRepo;

    @Autowired
    public AgencyJpaService(AgencyRepo agencyRepo) {
        this.agencyRepo = agencyRepo;
    }

    @Override
    public RealEstateAgency addAgency(AgencyViewModel agencyviewModel) {
        RealEstateAgency agency = new RealEstateAgency();
        agency.setAgencyID((UUID.randomUUID()));
        agency.setAgencyName(agencyviewModel.getAgencyName());
        agency.setAddress(agencyviewModel.getAddress());
        agency.setContactInfo(agencyviewModel.getContactInfo());
        agency.setCity(agencyviewModel.getCity());
        return agencyRepo.save(agency);
    }

    @Override
    public List<RealEstateAgency> getAgencyList() {
        return agencyRepo.findAll();
    }

    @Override
    public RealEstateAgency updateAgency(RealEstateAgency agency) {
        if(!agencyRepo.existsById(agency.getAgencyID())){
            throw new IllegalArgumentException("Agency not found");
        }
        return agencyRepo.save(agency);
    }

    @Override
    public void deleteAgency(UUID id) {
        agencyRepo.deleteById(id);
    }

    @Override
    public Optional<RealEstateAgency> findAgencyById(UUID id) {
        return agencyRepo.findById(id);
    }
}
