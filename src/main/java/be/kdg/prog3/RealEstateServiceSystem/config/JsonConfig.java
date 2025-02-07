package be.kdg.prog3.RealEstateServiceSystem.config;

import be.kdg.prog3.RealEstateServiceSystem.presentation.converters.JsonDateConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class JsonConfig {
    @Bean
    public Gson gson() {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapter(LocalDate.class, new JsonDateConverter());
        return b.setPrettyPrinting().create();
    }

}
