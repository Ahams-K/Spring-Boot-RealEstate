package be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.repository.AgentRepository;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper.AgentPropertyRowMapper;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper.AgentRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
@Profile("jdbc")
public class AgentJdbcRepository implements AgentRepository {
    private final JdbcTemplate jdbcTemplate;

    public AgentJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create Operation
    @Override
    public Agent insertAgent(Agent agent) {
        String sql = "INSERT INTO Agent (agent_id, agent_name, contact_info, licence_number, email) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                agent.getAgentID(),
                agent.getAgentName(),
                agent.getContactInfo(),
                agent.getLicenceNumber(),
                agent.getEmail()
        );
        String fetchAllProperties = """
        SELECT property_id FROM Property;
    """;
        List<UUID> allProperties = jdbcTemplate.queryForList(fetchAllProperties, UUID.class);
        if (!allProperties.isEmpty()) {
            UUID randomPropertyID = allProperties.get((int) (Math.random() * allProperties.size()));
            String agentProperty = "INSERT INTO Agent_Property (agent_id, property_id) VALUES (?, ?)";
            jdbcTemplate.update(agentProperty, agent.getAgentID(), randomPropertyID);
        } else {
            log.warn("No properties available to assign to agent {}", agent.getAgentID());
        }

        return agent;
    }

    // Read Operations
    @Override
    public List<Agent> getAgents() {
        String sql = """
         SELECT a.agent_id, a.agent_name, a.contact_info, a.licence_number, a.email,
                p.property_id, p.property_name, p.address, p.price, p.property_type,
                p.size, p.property_status, p.number_of_rooms, p.dateListed, p.image
                FROM Agent a
                LEFT JOIN Agent_Property ap ON a.agent_id = ap.agent_id
                LEFT JOIN Property p ON ap.property_id = p.property_id;
                                                                  
        """;
        return jdbcTemplate.query(sql, new AgentPropertyRowMapper());
    }

    @Override
    public Agent findAgentById(UUID agentID) {
        String sql = "SELECT * FROM Agent WHERE agent_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new AgentRowMapper(), agentID);
        } catch (Exception e) {
            log.error("Error finding agent by ID: {}", agentID, e);
            return null;
        }
    }

    @Override
    public List<Agent> findAgentForProperty(UUID propertyID) {
        String sql = """
            SELECT a.* 
            FROM Agent a
            JOIN Agent_Property ap ON a.agent_id = ap.agent_id
            WHERE ap.property_id = ?
        """;
        return jdbcTemplate.query(sql, new AgentRowMapper(), propertyID);
    }

    // Update Operation
    @Override
    public Agent updateAgent(Agent agent) {
        String sql = """
            UPDATE Agent
            SET agent_name = ?, contact_info = ?, licence_number = ?, email = ?
            WHERE agent_id = ?
        """;
        jdbcTemplate.update(sql,
                agent.getAgentName(),
                agent.getContactInfo(),
                agent.getLicenceNumber(),
                agent.getEmail(),
                agent.getAgentID()
        );
        return agent;
    }

    // Delete Operation
    @Override
    public void delete(UUID agentId) {
        String deleteAgentProperty = "DELETE FROM Agent_Property WHERE agent_id = ?";
        jdbcTemplate.update(deleteAgentProperty, agentId);

        String deleteAgent = "DELETE FROM Agent WHERE agent_id = ?";
        jdbcTemplate.update(deleteAgent, agentId);
    }
}
