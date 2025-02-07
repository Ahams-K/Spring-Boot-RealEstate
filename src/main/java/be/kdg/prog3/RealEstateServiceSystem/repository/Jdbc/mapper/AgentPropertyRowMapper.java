package be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AgentPropertyRowMapper implements RowMapper<Agent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Map<UUID, Agent> agentMap = new HashMap<>();

    @Override
    public Agent mapRow(ResultSet rs, int rowNum) throws SQLException {
        UUID agentId = UUID.fromString(rs.getString("agent_id"));
        Agent agent = agentMap.computeIfAbsent(agentId, id -> {
            try {
                return new AgentRowMapper().mapRow(rs, rowNum);
            } catch (SQLException e) {
                throw new DatabaseException("Error mapping row to Agent", e);
            }
        });

        if (rs.getString("property_id") != null) {
            Property property = new PropertyRowMapper().mapRow(rs, rowNum);
            agent.addProperty(property);
        }

        return agent;
    }
}
