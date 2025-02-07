package be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AgentRowMapper implements RowMapper<Agent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Agent mapRow(ResultSet rs, int rowNum) throws SQLException {
        Agent agent = new Agent();
        agent.setAgentID(rs.getObject("agent_id", UUID.class));
        agent.setAgentName(rs.getString("agent_Name"));
        agent.setContactInfo(rs.getString("contact_Info"));
        agent.setLicenceNumber(rs.getString("licence_Number"));
        agent.setEmail(rs.getString("email"));
        return agent;
    }
}
