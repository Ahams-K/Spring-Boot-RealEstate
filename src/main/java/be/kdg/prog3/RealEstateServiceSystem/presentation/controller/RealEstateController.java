package be.kdg.prog3.RealEstateServiceSystem.presentation.controller;


import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.AgentService;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class RealEstateController {


    private final SessionController sessionController;
    private Logger logger = LoggerFactory.getLogger(RealEstateController.class);

    public RealEstateController( SessionController sessionController) {
        this.sessionController = sessionController;
    }

    @GetMapping("/")
    public String displayHome(){
        sessionController.trackPage("Home");
        return "index";
    }

}
