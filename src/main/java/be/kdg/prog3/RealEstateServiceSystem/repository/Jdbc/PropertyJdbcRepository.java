package be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper.AgentRowMapper;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper.PropertyRowMapper;
import be.kdg.prog3.RealEstateServiceSystem.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
@Profile("jdbc")
public class PropertyJdbcRepository implements PropertyRepository {
    private final JdbcTemplate jdbcTemplate;

    public PropertyJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // Create Operation
    @Override
    public Property insertProperty(Property property) {

        String sql = "INSERT INTO Property(PROPERTY_ID, PROPERTY_NAME, IMAGE, ADDRESS, PRICE, PROPERTY_TYPE, SIZE, NUMBER_OF_ROOMS, PROPERTY_STATUS, DATELISTED) VALUES (?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                property.getPropertyID(),
                property.getPropertyName(),
                property.getImage(),
                property.getAddress(),
                property.getPrice(),
                property.getType().name(),
                property.getSize(),
                property.getNumberOfRooms(),
                property.getStatus().name(),
                property.getDateListed()
        );
        String fetchAllAgents = """
                SELECT agent_id FROM Agent
                """;
        List<UUID> allAgents = jdbcTemplate.queryForList(fetchAllAgents, UUID.class);
        if(!allAgents.isEmpty()){
            UUID randomAgentID = allAgents.get((int) (Math.random() * allAgents.size()));
            String propertyAgent = "INSERT INTO Agent_Property (property_id, agent_id) VALUES(?, ?)";
            jdbcTemplate.update(propertyAgent, property.getPropertyID(), randomAgentID);
        } else {
            log.warn("could not find agent {}", property.getPropertyID());
        }
        return property;
    }


    // Read Operations

    @Override
    public List<Property> getProperties() {
        // Step 1: Query the properties
        List<Property> properties = jdbcTemplate.query(
                "SELECT * FROM Property", new PropertyRowMapper()
        );

        // Step 2: Query the agents associated with these properties
        String agentQuery = "SELECT AGENT.* FROM AGENT_PROPERTY " +
                "JOIN AGENT ON AGENT_PROPERTY.AGENT_ID = AGENT.AGENT_ID " +
                "WHERE AGENT_PROPERTY.PROPERTY_ID = ?";

        // Step 3: For each property, get the associated agent(s)
        for (Property property : properties) {
            List<Agent> agents = jdbcTemplate.query(
                    agentQuery,
                    new Object[]{property.getPropertyID()},
                    new AgentRowMapper()  // Assuming AgentRowMapper exists to map agent data
            );
            // Step 4: Set the associated agents to the property
            property.setAgents(agents);  // Assuming Property has a setAgents method
        }

        return properties;
    }


    @Override
    public List<Property> getPropertyByName(String propertyName) {
        return jdbcTemplate.query(
                "SELECT * FROM Property WHERE PROPERTY_NAME = ?",
                new Object[]{propertyName},
                new PropertyRowMapper()
        );
    }

    @Override
    public Property findPropertyById(UUID propertyID) {
        return null;
    }


    @Override
    public List<Property> getPropertyForAgent(UUID AgentId){
        return jdbcTemplate.query("SELECT * FROM PROPERTY" +
                "JOIN AGENT_PROPERTY ON PROPERTY.PROPERTY_ID = AGENT_PROPERTY.PROPERTY_ID" +
                "WHERE AGENT_PROPERTY.PROPERTY_ID = ?", new Object[]{AgentId}, new PropertyRowMapper());
    }


    // Update Operation
    @Override
    public Property updateProperty(Property property) {
        jdbcTemplate.update("UPDATE Property SET PROPERTY_NAME = ?, IMAGE = ?, ADDRESS = ?, PRICE = ?, PROPERTY_TYPE = ?, SIZE = ?, NUMBER_OF_ROOMS = ?, PROPERTY_STATUS = ?, DATELISTED = ? WHERE PROPERTY_ID = ?",
                property.getPropertyName(),
                property.getImage(),
                property.getAddress(),
                property.getPrice(),
                property.getType().name(),
                property.getSize(),
                property.getNumberOfRooms(),
                property.getStatus().name(),
                property.getDateListed(),
                property.getPropertyID()
        );
        return property;
    }

    // Delete Operation

    @Override
    public void delete(UUID id) {
        String deletePropertyAgent = "DELETE FROM Agent_Property WHERE property_id = ?";
        jdbcTemplate.update(deletePropertyAgent, id);

        String deleteProperty = "DELETE FROM Property WHERE PROPERTY_id = ?";
        jdbcTemplate.update(deleteProperty, id);
    }

    @Override
    public List<Property> filterProperties(Double minPrice, Double maxPrice, PropertyType type, PropertyStatus status) {
        String sql = "SELECT * FROM Property WHERE 1=1";
        StringBuilder query = new StringBuilder(sql);
        if (minPrice != null) query.append(" AND PRICE >= ").append(minPrice);
        if (maxPrice != null) query.append(" AND PRICE <= ").append(maxPrice);
        if (type != null) query.append(" AND PROPERTY_TYPE = '").append(type.name()).append("'");
        if (status != null) query.append(" AND PROPERTY_STATUS = '").append(status.name()).append("'");
        return jdbcTemplate.query(query.toString(), new PropertyRowMapper());
    }
}
