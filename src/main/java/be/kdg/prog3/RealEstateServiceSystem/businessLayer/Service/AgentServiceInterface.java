package be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels.AgentViewModel;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgentServiceInterface {
    Agent addAgent(AgentViewModel agentViewModel);
    List<Agent> getAgentList();
    void deleteAgent(UUID id);
    Optional<Agent> findAgentById(UUID id);
    Agent updateAgent(Agent agent);
    List<Agent> getAllAgents();
}
