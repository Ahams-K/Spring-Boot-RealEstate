package be.kdg.prog3.RealEstateServiceSystem.repository.Java;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.repository.AgentRepository;
import be.kdg.prog3.RealEstateServiceSystem.repository.DataFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Profile("default")
@Repository
public class AgentJavaRepo implements AgentRepository {

    private final Logger logger = LoggerFactory.getLogger(AgentJavaRepo.class);
    private final DataFactory dataFactory;

    @Autowired
    public AgentJavaRepo(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }

    @Override
    public Agent insertAgent(Agent agent) {
        if (agent.getAgentID() == null) {
            agent.setAgentID(UUID.randomUUID()); // Ensure UUID is set before adding
        }
        dataFactory.getAgents().add(agent);
        return agent;
    }

    @Override
    public List<Agent> getAgents() {
        return dataFactory.getAgents();
    }

    @Override
    public Agent findAgentById(UUID agentID) {
        return dataFactory.getAgents().stream()
                .filter(agent -> agent.getAgentID().equals(agentID))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Agent> findAgentForProperty(UUID propertyID) {
          return dataFactory.getAgents().stream()
                .filter(agent -> agent.getProperties().stream()
                        .anyMatch(property -> property.getPropertyID().equals(propertyID)))
                .collect(Collectors.toList());
    }

    @Override
    public Agent updateAgent(Agent agent) {
        return null;
    }

    @Override
    public void delete(UUID agentId) {
        if (agentId == null) {
            logger.error("Agent ID is null, cannot delete agent");
            throw new IllegalArgumentException("Agent ID is null");
        }
        boolean removed = dataFactory.getAgents().removeIf(agent -> agent.getAgentID().equals(agentId));
        if (!removed) {
            logger.error("Agent with ID {} not found, cannot delete agent", agentId);
        } else {
            logger.info("Agent with ID {} deleted successfully", agentId);
        }
    }
}
