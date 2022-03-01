package ua.pomoc.helpoffers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.pomoc.helpoffers.domain.City;
import ua.pomoc.helpoffers.domain.HelpOffer;
import ua.pomoc.helpoffers.domain.InvitePeriod;

@Repository
public interface HelpOfferRepository extends CrudRepository<HelpOffer, Long> {
    Iterable<HelpOffer> findAllByCityAndAvailablePlacesAndWithAnimalsAndInvitePeriod(City city, Integer availablePlaces, Boolean withAnimals, InvitePeriod invitePeriod);
}
