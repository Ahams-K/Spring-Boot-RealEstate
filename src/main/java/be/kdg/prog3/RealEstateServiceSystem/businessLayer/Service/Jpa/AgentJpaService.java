package be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.Jpa;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.AgentServiceInterface;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.exception.DatabaseException;
import be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels.AgentViewModel;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jpa2.AgencyRepo;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jpa2.AgentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("jpa")
@Service
public class AgentJpaService implements AgentServiceInterface {

    private final AgentRepo agentRepo;

    @Autowired
    public AgentJpaService(AgentRepo agentRepo) {
        this.agentRepo = agentRepo;
    }

    @Override
    public Agent addAgent(AgentViewModel agentViewModel) {
        try {
            Agent agent = new Agent();
            agent.setAgentID(UUID.randomUUID());
            agent.setAgentName(agentViewModel.getAgentName());
            agent.setContactInfo(agentViewModel.getContactInfo());
            agent.setLicenceNumber(agentViewModel.getLicenceNumber());
            agent.setEmail(agentViewModel.getEmail());
            return agentRepo.save(agent);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Error saving agent: Data integrity violation", e);
        } catch (Exception e) {
            throw new DatabaseException("Unexpected error occurred while saving agent", e);
        }
    }

    @Override
    public List<Agent> getAgentList() {
        return agentRepo.findAll();
    }

    @Override
    public void deleteAgent(UUID id) {
        agentRepo.deleteById(id);
    }

    @Override
    public Optional<Agent> findAgentById(UUID id) {
        return agentRepo.findById(id);
    }

    @Override
    public Agent updateAgent(Agent agent) {
        if(!agentRepo.existsById(agent.getAgentID())){
            throw new IllegalArgumentException("Agent not found");
        }
        return agentRepo.save(agent);
    }

    @Override
    public List<Agent> getAllAgents() {
        return agentRepo.findAll();
    }

    public List<Agent> getAgentsByPropertyId(UUID propertyID) {
        return agentRepo.findAgentsByPropertyId(propertyID);
    }

    public List<Agent> searchAgentsByName(String name) {
        return agentRepo.findByAgentNameContainingIgnoreCase(name);
    }

    public List<Agent> searchAgentsByLicenceNumber(String licenceNumber) {
        return agentRepo.findByLicenceNumber(licenceNumber);
    }
}
