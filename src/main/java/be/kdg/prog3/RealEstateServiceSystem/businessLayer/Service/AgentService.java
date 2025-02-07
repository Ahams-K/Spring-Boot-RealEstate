package be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels.AgentViewModel;
import be.kdg.prog3.RealEstateServiceSystem.repository.AgentRepository;
import be.kdg.prog3.RealEstateServiceSystem.repository.Java.AgentJavaRepo;
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
public class AgentService implements AgentServiceInterface {

    private final Logger logger = LoggerFactory.getLogger(AgentService.class);

    private final AgentRepository repo;

@Autowired
    public AgentService(AgentRepository repo){
        this.repo = repo;
    }

    @Override
    public Agent addAgent(AgentViewModel viewModel){
        logger.info("Adding new agent");
        Agent agent = convertAgentViewModelToAgent(viewModel);
        if (agent.getAgentID() == null) {
            agent.setAgentID(UUID.randomUUID()); // Ensure UUID is set
        }
        return repo.insertAgent(agent);
    }

    @Override
    public List<Agent> getAgentList(){
        logger.info("Getting all agents");
        return repo.getAgents();
    }

    @Override
    public Agent updateAgent(Agent agent) {
        return null;
    }

    @Override
    public void deleteAgent(UUID id) {
        repo.delete(id);
    }


    public List<Agent> getAllAgents() {
        logger.info("Retrieving all agents for stream filtering");
        return getAgentList();
    }

    public Optional<Agent> findAgentById(UUID id) {
        logger.debug("Finding agent by ID: {}", id);
        return repo.getAgents()
                .stream()
                .filter(a -> a.getAgentID().equals(id))
                .findFirst();

    }


    private Agent convertAgentViewModelToAgent(AgentViewModel agentViewModel) {
      Agent agent = new Agent();
      agent.setAgentName(agentViewModel.getAgentName());
      agent.setContactInfo(agentViewModel.getContactInfo());
      agent.setEmail(agentViewModel.getEmail());
      agent.setLicenceNumber(agentViewModel.getLicenceNumber());
      return agent;
    }

    private AgentViewModel convertToViewModel(Agent agent){
     AgentViewModel agentViewModel = new AgentViewModel();
     agentViewModel.setAgentID(agent.getAgentID());
     agentViewModel.setAgentName(agent.getAgentName());
     agentViewModel.setContactInfo(agent.getContactInfo());
     agentViewModel.setEmail(agent.getEmail());
     agentViewModel.setLicenceNumber(agent.getLicenceNumber());
     return agentViewModel;
    }
}
