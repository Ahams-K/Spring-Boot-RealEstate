package be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper;


import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PropertyRowMapper implements RowMapper<Property> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public Property mapRow(ResultSet rs, int rowNum) throws SQLException {
        Property property = new Property();
        property.setPropertyID(UUID.fromString(rs.getString("property_id")));
        property.setPropertyName(rs.getString("property_Name"));
        property.setImage(rs.getString("image"));
        property.setAddress(rs.getString("address"));
        property.setPrice(rs.getDouble("price"));
        property.setType(PropertyType.valueOf(rs.getString("property_type")));  // Assuming PropertyType is an enum
        property.setSize(rs.getDouble("size"));
        property.setStatus(PropertyStatus.valueOf(rs.getString("property_status")));  // Assuming PropertyStatus is an enum
        property.setNumberOfRooms(rs.getInt("number_Of_Rooms"));
        property.setDateListed(rs.getDate("dateListed").toLocalDate());
        return property;
    }
}
