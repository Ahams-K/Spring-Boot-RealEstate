package be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AgentViewModel {

    private UUID agentID;
    @NotBlank(message = "Agent name is required")
    @Size(min = 2, max = 30, message = "Agent name must be between 5 and 30 characters.")
    private String agentName;

    @NotBlank(message = "Agent contact information is required")
    private String contactInfo;

    @NotBlank(message = "Agent licence number is required")
    private String licenceNumber;

    @NotBlank(message = "Agent email is required")
    @Email(message = "Invalid email format")
    private String email;

    private List<Property> properties;



}
