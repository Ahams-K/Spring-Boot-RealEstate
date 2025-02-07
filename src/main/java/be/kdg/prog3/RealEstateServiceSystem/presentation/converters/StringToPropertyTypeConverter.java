package be.kdg.prog3.RealEstateServiceSystem.presentation.converters;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToPropertyTypeConverter implements Converter<String, PropertyType> {

    @Override
    public PropertyType convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        try {
            return PropertyType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid propertyTpe");
        }
    }
}

