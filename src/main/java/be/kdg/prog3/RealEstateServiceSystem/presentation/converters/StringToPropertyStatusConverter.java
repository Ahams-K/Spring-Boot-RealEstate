package be.kdg.prog3.RealEstateServiceSystem.presentation.converters;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import org.springframework.core.convert.converter.Converter;

public class StringToPropertyStatusConverter implements Converter<String, PropertyStatus> {
    @Override
    public PropertyStatus convert(String source) {
         if (source == null || source.trim().isEmpty()) {
             return null;
         } try {
             return PropertyStatus.valueOf(source.toUpperCase());
         } catch (IllegalArgumentException e) {
             throw new IllegalArgumentException("Invalid PropertyStatus value: " + source, e);

        }
    }
}
