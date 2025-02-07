package be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AgencyAgentMapper implements RowMapper<RealEstateAgency> {
    private final Map<UUID, RealEstateAgency> agencyMap = new HashMap<>();

    @Override
    public RealEstateAgency mapRow(ResultSet rs, int rowNum) throws SQLException {
        UUID agencyId = UUID.fromString(rs.getString("agency_ID"));

        // Retrieve or create the RealEstateAgency
        RealEstateAgency agency = agencyMap.computeIfAbsent(agencyId, id -> {
            try {
                return new AgencyRowMapper().mapRow(rs, rowNum);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Map Agent and add it to the Agency if it's not already present
        if (rs.getString("agent_ID") != null) {
            Agent agent = new AgentRowMapper().mapRow(rs, rowNum);
            if (agency.getAgents().stream().noneMatch(a -> a.getAgentID().equals(agent.getAgentID()))) {
                agency.getAgents().add(agent);
            }
        }

        return agency;
    }
}

