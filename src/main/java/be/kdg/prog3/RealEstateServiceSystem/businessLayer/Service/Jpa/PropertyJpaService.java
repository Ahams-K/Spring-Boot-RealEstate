package be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.Jpa;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.PropertyServiceInterface;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;
import be.kdg.prog3.RealEstateServiceSystem.exception.DatabaseException;
import be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels.PropertyViewModel;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jpa2.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("jpa")
@Service
public class PropertyJpaService implements PropertyServiceInterface {

    private final PropertyRepo propertyRepo;

    @Autowired
    public PropertyJpaService(PropertyRepo propertyRepo) {
        this.propertyRepo = propertyRepo;
    }

    @Override
    public Property addProperty(PropertyViewModel propertyViewModel) {
        try {
            Property property = new Property();
            property.setPropertyID(UUID.randomUUID());
            property.setPropertyName(propertyViewModel.getPropertyName());
            property.setPrice(propertyViewModel.getPrice());
            property.setType(propertyViewModel.getType());
            property.setStatus(propertyViewModel.getStatus());
            property.setDateListed(propertyViewModel.getDateListed());
            property.setAddress(propertyViewModel.getAddress());
            property.setSize(propertyViewModel.getSize());
            property.setNumberOfRooms(propertyViewModel.getNumberOfRooms());
            return propertyRepo.save(property);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Error saving property: Data integrity violation", e);
        } catch (Exception e) {
            throw new DatabaseException("Unexpected error occurred while saving property", e);
        }
    }

    @Override
    public List<Property> getPropertiesList() {
        return propertyRepo.findAll();
    }

    @Override
    public Property updateProperty(Property property) {
        if (!propertyRepo.existsById(property.getPropertyID())) {
            throw new DatabaseException("Property not found");
        }
        try {
            return propertyRepo.save(property);
        } catch (Exception e) {
            throw new DatabaseException("Error updating property", e);
        }
    }

    @Override
    public void deleteProperty(UUID id) {
        try {
            propertyRepo.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException("Error deleting property", e);
        }
    }

    @Override
    public List<Property> filterPropertiesByCriteria(Double minPrice, Double maxPrice, PropertyType type, PropertyStatus status) {
       return propertyRepo.filterProperties(minPrice, maxPrice, type, status);
    }

    @Override
    public Optional<Property> findById(UUID id) {
        return propertyRepo.findById(id);
    }
}
