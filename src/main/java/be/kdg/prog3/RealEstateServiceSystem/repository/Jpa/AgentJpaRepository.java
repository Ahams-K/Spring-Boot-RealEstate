package be.kdg.prog3.RealEstateServiceSystem.repository.Jpa;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.repository.AgentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
@Profile("prod")
public class AgentJpaRepository implements AgentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Create Operation
    @Override
    public Agent insertAgent(Agent agent) {
        entityManager.merge(agent);
        return agent;
    }


    // Read Operations
    @Override
    public List<Agent> getAgents() {
       String jpql = "SELECT a FROM Agent a";
       return entityManager.createQuery(jpql, Agent.class).getResultList();
    }

    @Override
    public Agent findAgentById(UUID agentID) {
        return entityManager.find(Agent.class, agentID);
    }

    @Override
    public List<Agent> findAgentForProperty(UUID propertyID) {
        String jpql = "SELECT a FROM Agent a JOIN a.properties p WHERE p.propertyID = :propertyID";
        return entityManager.createQuery(jpql, Agent.class)
                .setParameter("propertyID", propertyID)
                .getResultList();
    }


    // Update Operation
    @Override
    public Agent updateAgent(Agent agent) {
        return entityManager.merge(agent);
    }



    // Delete Operation
    @Override
    public void delete(UUID agentId) {
        Agent agent = findAgentById(agentId);
        if (agent!= null) {
            entityManager.remove(agent);
        }
    }
}
