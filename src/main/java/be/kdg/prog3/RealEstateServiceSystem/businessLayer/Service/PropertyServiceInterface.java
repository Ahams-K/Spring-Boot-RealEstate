package be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;
import be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels.PropertyViewModel;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyServiceInterface {
    Property addProperty(PropertyViewModel propertyViewModel);
    List<Property> getPropertiesList();
    Property updateProperty(Property property);
    void deleteProperty(UUID id);
    List<Property> filterPropertiesByCriteria(Double minPrice, Double maxPrice, PropertyType type, PropertyStatus status);
    Optional<Property> findById(UUID id);;
}
