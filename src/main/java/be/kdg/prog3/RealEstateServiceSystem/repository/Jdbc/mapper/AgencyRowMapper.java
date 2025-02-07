package be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AgencyRowMapper implements RowMapper<RealEstateAgency> {
    @Override
    public RealEstateAgency mapRow(ResultSet rs, int rowNum) throws SQLException {
        RealEstateAgency agency = new RealEstateAgency();
        agency.setAgencyID(rs.getObject("agency_ID", UUID.class));
        agency.setAgencyName(rs.getString("agency_Name"));
        agency.setAddress(rs.getString("address"));
        agency.setContactInfo(rs.getString("contact_Info"));
        agency.setCity(rs.getString("city"));
        agency.setImage(rs.getString("image"));
        return agency;
    }
}
