package be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels;


import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class AgencyViewModel {
    private UUID agencyID;
    private String agencyName;
    private String address;
    private String contactInfo;
    private Set<Agent> agents;
    private String city;
    private String image;
}
