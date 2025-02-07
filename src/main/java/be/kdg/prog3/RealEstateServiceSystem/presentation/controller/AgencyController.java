package be.kdg.prog3.RealEstateServiceSystem.presentation.controller;


import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.AgencyService;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.AgencyServiceInterface;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/agencies")
public class AgencyController {

    private final AgencyServiceInterface agencyService;
    private final SessionController sessionController;

    @Autowired
    public AgencyController(AgencyServiceInterface agencyService, SessionController sessionController) {
        this.agencyService = agencyService;
        this.sessionController = sessionController;
    }

    @GetMapping
    public String showAgencies(Model model) {
        sessionController.trackPage("Agencies");
        model.addAttribute("Agencies", agencyService.getAgencyList());
        return "agencies";
    }

    @GetMapping("/{id}")
    public String viewAgencyDetails(@PathVariable UUID id, Model model) {
        sessionController.trackPage("Agency Info");
        RealEstateAgency agency = agencyService.findAgencyById(id).orElseThrow(() -> new IllegalArgumentException("Invalid agency Id: " + id));
        model.addAttribute("agency", agency);
        return "agency-details";
    }

}
