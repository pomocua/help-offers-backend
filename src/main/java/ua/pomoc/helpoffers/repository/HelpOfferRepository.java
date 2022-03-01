package ua.pomoc.helpoffers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.pomoc.helpoffers.domain.HelpOffer;

@Repository
public interface HelpOfferRepository extends CrudRepository<HelpOffer, Long> {
}
