package be.kdg.prog3.RealEstateServiceSystem.repository;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;

import java.util.List;
import java.util.UUID;

public interface AgentRepository {
    // Create Operation
    Agent insertAgent(Agent agent);

    // Read Operations
    List<Agent> getAgents();

    Agent findAgentById(UUID agentID);

    List<Agent> findAgentForProperty(UUID propertyID);

    // Update Operation
    Agent updateAgent(Agent agent);

    // Delete Operation
    void delete(UUID agentId);
}
