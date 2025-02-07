package be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;
import be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels.AgencyViewModel;
import be.kdg.prog3.RealEstateServiceSystem.repository.AgencyRepository;
import be.kdg.prog3.RealEstateServiceSystem.repository.Java.AgencyJavaRepo;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jpa2.AgencyRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile({"default","jdbc","prod"})
@Service
public class AgencyService implements AgencyServiceInterface {

    private final Logger logger = LoggerFactory.getLogger(AgencyService.class);
    private final AgencyRepository repo;

    @Autowired
    public AgencyService(AgencyRepository repo) {
        this.repo = repo;
    }

    @Override
    public RealEstateAgency addAgency(AgencyViewModel viewModel){
        logger.info("Adding new agency");
        RealEstateAgency agency = convertAgencyViewModelToAgency(viewModel);
        return repo.insertAgency(agency);
    }

    @Override
    public List<RealEstateAgency> getAgencyList(){
        logger.info("Getting all agencies");
        return repo.getAgencies();
    }

    @Override
    public RealEstateAgency updateAgency(RealEstateAgency agency){
        return null;
    }

    @Override
    public void deleteAgency(UUID id){
        repo.delete(id);
    }

    @Override
    public Optional<RealEstateAgency> findAgencyById(UUID id){
        logger.debug("Finding agency by ID {}", id);
        return repo.getAgencies()
                .stream()
                .filter(a -> a.getAgencyID().equals(id))
                .findFirst();
    }

    private RealEstateAgency convertAgencyViewModelToAgency(AgencyViewModel agencyViewModel){
        RealEstateAgency agency = new RealEstateAgency();
        agency.setAgencyID(agencyViewModel.getAgencyID());
        agency.setAgencyName(agencyViewModel.getAgencyName());
        agency.setAddress(agencyViewModel.getAddress());
        agency.setContactInfo(agencyViewModel.getContactInfo());
        return agency;
    }
}
