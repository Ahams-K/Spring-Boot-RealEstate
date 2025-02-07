package be.kdg.prog3.RealEstateServiceSystem.repository.Java;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;
import be.kdg.prog3.RealEstateServiceSystem.repository.DataFactory;
import be.kdg.prog3.RealEstateServiceSystem.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Profile("default")
@Repository
public class PropertyJavaRepo implements PropertyRepository {

    private final DataFactory dataFactory;

    @Autowired
    public PropertyJavaRepo(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }

    @Override
    public Property insertProperty(Property property) {
        if (property.getPropertyID() == null) {
            property.setPropertyID(UUID.randomUUID()); // Ensure UUID is set before adding
        }
        dataFactory.getProperties().add(property);
        return property;
    }

    @Override
    public List<Property> getProperties() {
        return dataFactory.getProperties();
    }

    @Override
    public List<Property> getPropertyByName(String propertyName) {
        return dataFactory.getProperties().stream()
                .filter(property -> property.getPropertyName().equalsIgnoreCase(propertyName))
                .collect(Collectors.toList());
    }

    @Override
    public Property findPropertyById(UUID propertyID) {
        return dataFactory.getProperties().stream()
                .filter(property -> property.getPropertyID().equals(propertyID))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Property> getPropertyForAgent(UUID agentId) {
        return dataFactory.getAgents().stream()
                .filter(agent -> agent.getAgentID().equals(agentId))
                .flatMap(agent -> agent.getProperties().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Property updateProperty(Property property) {
        return null;
    }

    @Override
    public void delete(UUID propertyId) {
        if (propertyId == null) {
            dataFactory.getLogger().error("Property ID is null, cannot delete property");
            throw new IllegalArgumentException("Property ID is null");
        }
        boolean removed = dataFactory.getProperties().removeIf(property -> property.getPropertyID().equals(propertyId));
        if (!removed) {
            dataFactory.getLogger().error("Property with ID {} not found, cannot delete property", propertyId);
        } else {
            dataFactory.getLogger().info("Property with ID {} deleted successfully", propertyId);
        }
    }

    @Override
    public List<Property> filterProperties(Double minPrice, Double maxPrice, PropertyType type, PropertyStatus status) {
        return dataFactory.getProperties().stream()
                .filter(property -> (minPrice == null || property.getPrice() >= minPrice) &&
                        (maxPrice == null || property.getPrice() <= maxPrice) &&
                        (type == null || property.getType() == type) &&
                        (status == null || property.getStatus() == status))
                .collect(Collectors.toList());
    }
}
