package be.kdg.prog3.RealEstateServiceSystem.exception;


import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalException {

    private final Logger logger = Logger.getLogger(GlobalException.class.getName());

    @ExceptionHandler(DataAccessException.class)
    public ModelAndView databaseError(DataAccessException ex){
        logger.severe("Database error" + ex.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("database-errors");
        mav.addObject("error", "A database error occurred. Please try again later.");
        return mav;
    }
    @ExceptionHandler(RealEstateException.class)
    public ModelAndView RealEstateException(RealEstateException ex) {
        logger.severe("RealEstateException Occured");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error-page");
        mav.addObject("error", ex.getMessage());
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView generalError(Exception ex) {
        logger.severe("General error occurred: " + ex.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error-page");
        mav.addObject("error", ex.getMessage());
        return mav;
    }
}
