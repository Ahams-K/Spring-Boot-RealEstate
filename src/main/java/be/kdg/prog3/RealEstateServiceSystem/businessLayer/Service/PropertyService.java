package be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;
import be.kdg.prog3.RealEstateServiceSystem.exception.RealEstateException;
import be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels.PropertyViewModel;
import be.kdg.prog3.RealEstateServiceSystem.repository.Java.PropertyJavaRepo;
import be.kdg.prog3.RealEstateServiceSystem.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile({"default","jdbc","prod"})
@Service
public class PropertyService implements PropertyServiceInterface {
    private final Logger logger = LoggerFactory.getLogger(PropertyService.class);
    private final PropertyRepository repo;

@Autowired
    public PropertyService(PropertyRepository repo){
        this.repo = repo;
    }

    @Override
    public Property addProperty(PropertyViewModel propertyViewModel){
        logger.info("Adding new property");

        Property property = convertPropertyViewModelToProperty(propertyViewModel);
        if (property.getPropertyID() == null) {
            property.setPropertyID(UUID.randomUUID()); // Ensure UUID is set
        }
        return repo.insertProperty(property);
    }

    @Override
    public List<Property> getPropertiesList(){
        logger.info("Getting properties");
        return repo.getProperties();
    }

    @Override
    public Property updateProperty(Property property) {
        return null;
    }

    @Override
    public void deleteProperty(UUID id) {
        repo.delete(id);
    }


    @Override
    public List<Property> filterPropertiesByCriteria(Double minPrice, Double maxPrice, PropertyType type, PropertyStatus status) {
        logger.info("Filtering properties by price range: {} - {}, type: {}, status: {}", minPrice, maxPrice, type, status);
        return repo.getProperties().stream()
                .filter(property -> (minPrice == null || property.getPrice() >= minPrice))
                .filter(property -> (maxPrice == null || property.getPrice() <= maxPrice))
                .filter(property -> (type == null || property.getType() == type))
                .filter(property -> (status == null || property.getStatus() == status))
                .toList();
    }

    @Override
    public Optional<Property> findById(UUID id) {
        logger.debug("Finding property by ID: " + id);
        return Optional.ofNullable(repo.getProperties()
                .stream()
                .filter(p -> p.getPropertyID().equals(id))
                .findFirst()
                .orElseThrow(() -> new RealEstateException("Property with ID " + id + " not found.")));
    }


    public Property convertPropertyViewModelToProperty(PropertyViewModel propertyViewModel){
        Property property = new Property();
        property.setPropertyID(propertyViewModel.getPropertyID());
        property.setPropertyName(propertyViewModel.getPropertyName());
        property.setAddress(propertyViewModel.getAddress());
        property.setPrice(propertyViewModel.getPrice());
        property.setType(propertyViewModel.getType());
        property.setSize(propertyViewModel.getSize());
        property.setStatus(propertyViewModel.getStatus());
        property.setNumberOfRooms(propertyViewModel.getNumberOfRooms());
        property.setDateListed(propertyViewModel.getDateListed());
        return property;
    }
}
