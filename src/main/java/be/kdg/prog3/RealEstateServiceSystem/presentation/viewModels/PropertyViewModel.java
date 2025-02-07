package be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Data
public class PropertyViewModel {

    private UUID propertyID;
    @NotEmpty(message = "Property name is required")
    private String propertyName;
    @NotBlank(message = "Property address is required")
    @Size(min = 10, max = 50, message = "property address is required")
    private String address;
    @NotNull(message = "Property price is required")
    @DecimalMax(value = "500000000000.00", message = "Property price must not exceed 500 billion")
    @DecimalMin(value = "0.01", message = "Property price must be greater than 0")
    private double price;
    @NotNull(message = "Property type is required")
    private PropertyType type;
    @DecimalMax(value = "500000.00", message = "Property size must not exceed 500,000 square meters")
    @DecimalMin(value = "1.00", message = "Property size must be at least 1 square meter")
    private double size;
    @NotNull(message = "Property status is required")
    private PropertyStatus status;
    @Min(value = 1, message = "Number of rooms must be at least 1")
    @Max(value = 3000, message = "Number of rooms must not exceed 3000")
    private int numberOfRooms;
    @PastOrPresent(message = "The date listed must be in the past or present")
    private LocalDate dateListed;
    private String image;

    private List<Agent> agents;



}
