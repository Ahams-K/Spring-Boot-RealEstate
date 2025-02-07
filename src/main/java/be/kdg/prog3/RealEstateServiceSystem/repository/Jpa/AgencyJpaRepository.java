package be.kdg.prog3.RealEstateServiceSystem.repository.Jpa;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;
import be.kdg.prog3.RealEstateServiceSystem.repository.AgencyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
@Transactional
@Profile("prod")
public class AgencyJpaRepository implements AgencyRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public RealEstateAgency insertAgency(RealEstateAgency agency) {
        entityManager.persist(agency);
        return agency;
    }

    @Override
    public List<RealEstateAgency> getAgencies() {
        String jpql ="SELECT r FROM RealEstateAgency r";
        return entityManager.createQuery(jpql, RealEstateAgency.class).getResultList();
    }

    @Override
    public RealEstateAgency findAgencyById(UUID agencyID) {
        return entityManager.find(RealEstateAgency.class, agencyID);
    }

    @Override
    public RealEstateAgency updateAgency(RealEstateAgency agency) {
        return entityManager.merge(agency);
    }

    @Override
    public void delete(UUID agencyID) {
        RealEstateAgency agency = findAgencyById(agencyID);
        if (agency != null) {
            entityManager.remove(agency);
        }
    }
}
