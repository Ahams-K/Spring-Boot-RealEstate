package be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;
import be.kdg.prog3.RealEstateServiceSystem.repository.AgencyRepository;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper.AgencyAgentMapper;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper.AgencyRowMapper;
import be.kdg.prog3.RealEstateServiceSystem.repository.Jdbc.mapper.AgentRowMapper;
import io.micrometer.core.instrument.binder.db.MetricsDSLContext;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Profile("jdbc")
@Repository
public class AgencyJdbcRepository implements AgencyRepository {

    private final JdbcTemplate jdbcTemplate;

    public AgencyJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RealEstateAgency insertAgency(RealEstateAgency agency) {
        return null;
    }

    @Override
    public List<RealEstateAgency> getAgencies() {
        String sql = "SELECT * FROM REALESTATEAGENCY a LEFT JOIN AGENT ag ON a.AGENCY_ID = ag.AGENCY_ID";
        return jdbcTemplate.query(sql, new AgencyAgentMapper());
    }

    @Override
    public RealEstateAgency findAgencyById(UUID agencyID) {
        String sql = "SELECT * FROM REALESTATEAGENCY a LEFT JOIN AGENT ag ON a.AGENCY_ID = ag.AGENCY_ID WHERE a.AGENCY_ID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new AgencyAgentMapper(), agencyID);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public RealEstateAgency updateAgency(RealEstateAgency agency) {
        String sql = "UPDATE REALESTATEAGENCY SET AGENCY_NAME = ?, ADDRESS = ?, CONTACT_INFO = ? WHERE AGENCY_ID = ?";
        jdbcTemplate.update(sql,
                agency.getAgencyName(),
                agency.getAddress(),
                agency.getContactInfo(),
                agency.getAgencyID()
        );
        return agency;
    }

    @Override
    public void delete(UUID agencyID) {
        String sql = "DELETE FROM REALESTATEAGENCY WHERE AGENCY_ID =?";
        jdbcTemplate.update(sql, agencyID);
    }
}
