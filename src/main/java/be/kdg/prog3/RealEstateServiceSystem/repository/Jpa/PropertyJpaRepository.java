package be.kdg.prog3.RealEstateServiceSystem.repository.Jpa;


import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;
import be.kdg.prog3.RealEstateServiceSystem.repository.PropertyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
@Profile("prod")
public class PropertyJpaRepository implements PropertyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Create operation
    @Override
    public Property insertProperty(Property property) {
        entityManager.merge(property);
        return property;
    }

    @Override
    public List<Property> getProperties() {
        String jpql = "SELECT p FROM Property p";
        return entityManager.createQuery(jpql, Property.class).getResultList();
    }

    @Override
    public List<Property> getPropertyByName(String propertyName) {
        String jpql = "SELECT p FROM Property p WHERE p.propertyName = :propertyName";
        return entityManager.createQuery(jpql, Property.class)
                .setParameter("propertyName", propertyName)
                .getResultList();
    }

    @Override
    public Property findPropertyById(UUID propertyID) {
        return entityManager.find(Property.class, propertyID);
    }

    @Override
    public List<Property> getPropertyForAgent(UUID agentID){
        String jpql = "SELECT p FROM Property p JOIN p.agents a WHERE a.agentID = :agentID";
        return entityManager.createQuery(jpql, Property.class)
                .setParameter("agentID", agentID)
                .getResultList();
    }

    @Override
    public Property updateProperty(Property property) {
        return entityManager.merge(property);
    }

    @Override
    public void delete(UUID propertyID) {
        Property property = findPropertyById(propertyID);
        if (property != null) {
            entityManager.remove(property);
        }
    }

    @Override
    public List<Property> filterProperties(Double minPrice, Double maxPrice, PropertyType type, PropertyStatus status) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM Property p WHERE 1=1");

        if (minPrice != null) jpql.append(" AND p.price >= :minPrice");
        if (maxPrice != null) jpql.append(" AND p.price <= :maxPrice");
        if (type != null) jpql.append(" AND p.type = :type");
        if (status != null) jpql.append(" AND p.status = :status");

        var query = entityManager.createQuery(jpql.toString(), Property.class);

        if (minPrice != null) query.setParameter("minPrice", minPrice);
        if (maxPrice != null) query.setParameter("maxPrice", maxPrice);
        if (type != null) query.setParameter("type", type);
        if (status != null) query.setParameter("status", status);

        return query.getResultList();
    }

}
