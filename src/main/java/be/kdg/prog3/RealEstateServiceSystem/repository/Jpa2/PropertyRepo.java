package be.kdg.prog3.RealEstateServiceSystem.repository.Jpa2;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Profile("jpa")
public interface PropertyRepo extends JpaRepository<Property, UUID> {


    @Query("SELECT p FROM Property p WHERE p.status = :status AND p.price BETWEEN :minPrice AND :maxPrice AND p.type = :type")
    List<Property> filterProperties(
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("type")PropertyType type,
            @Param("status") PropertyStatus status
    );
}
