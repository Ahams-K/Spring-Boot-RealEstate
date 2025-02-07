package be.kdg.prog3.RealEstateServiceSystem.presentation.controller;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.PropertyService;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.PropertyServiceInterface;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;
import be.kdg.prog3.RealEstateServiceSystem.exception.DatabaseException;
import be.kdg.prog3.RealEstateServiceSystem.exception.RealEstateException;
import be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels.PropertyViewModel;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyServiceInterface propertyService;
    private final SessionController sessionController;
    private Logger logger = LoggerFactory.getLogger(PropertyController.class);

    public PropertyController(PropertyServiceInterface propertyService, SessionController sessionController) {
        this.propertyService = propertyService;
        this.sessionController = sessionController;
    }

    @GetMapping
    public String showProperties(Model model) {
        sessionController.trackPage("Properties");
        model.addAttribute("Properties", propertyService.getPropertiesList());
        return "properties";
    }

    @GetMapping("/add")
    public String showAddPropertyForm(Model model) {
        sessionController.trackPage("Add Property Form");
        model.addAttribute("propertyViewModel", new PropertyViewModel());
        return "addproperty";
    }

    @PostMapping("/add")
    public String processAddProperty(@Valid @ModelAttribute PropertyViewModel propertyViewModel, BindingResult bindingResult, Model model){
        sessionController.trackPage("Add Property Form Submission");
        if (bindingResult.hasErrors()) {
            model.addAttribute("propertyViewModel", propertyViewModel);
            return "addproperty";
        }
        propertyService.addProperty(propertyViewModel);
        return "redirect:/properties";
    }

    @GetMapping("/{id}")
    public String showPropertyDetails(@PathVariable() UUID id, Model model) {
        sessionController.trackPage("Property Details");
        Property property = propertyService.findById(id).orElseThrow(() -> new RealEstateException("Property with: " + id + " not found"));
        model.addAttribute("property", property);
        return "property-details";
    }


    @PostMapping("/filter")
    public String filterProperties(Double minPrice, Double maxPrice, PropertyType type, PropertyStatus status, Model model) {
        sessionController.trackPage("Filter Properties");
        logger.info("Received filter request with minPrice: {}, maxPrice: {}, type: {}, status: {}", minPrice, maxPrice, type, status);
        model.addAttribute("Properties", propertyService.filterPropertiesByCriteria(minPrice, maxPrice, type, status));
        return "properties";
    }

    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable UUID id){
        sessionController.trackPage("Delete Property");
        propertyService.deleteProperty(id);
        return "redirect:/properties";
    }

    @PostMapping("/error")
    @ExceptionHandler(RealEstateException.class)
    public String handleRealEstateException(RealEstateException ex, Model model) {
        logger.error("RealEstateException occurred: ", ex);
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    @PostMapping("/database-error")
    @ExceptionHandler(DatabaseException.class)
    public String handleDatabaseException(DatabaseException ex, Model model) {
        logger.error("DatabaseException occurred: ", ex);
        model.addAttribute("error", ex.getMessage());
        return "database-errors";
    }
}
