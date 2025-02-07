package be.kdg.prog3.RealEstateServiceSystem.repository;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;

import java.util.List;
import java.util.UUID;

public interface PropertyRepository {
    // Create Operation
    Property insertProperty(Property property);

    // Read Operations
    List<Property> getProperties();

    List<Property> getPropertyByName(String propertyName);

    Property findPropertyById(UUID propertyID);

    List<Property> getPropertyForAgent(UUID AgentId);

    // Update Operation
    Property updateProperty(Property property);

    void delete(UUID id);

    List<Property> filterProperties(Double minPrice, Double maxPrice, PropertyType type, PropertyStatus status);
}
