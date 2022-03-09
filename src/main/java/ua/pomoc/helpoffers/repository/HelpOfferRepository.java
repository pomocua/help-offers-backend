package ua.pomoc.helpoffers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.pomoc.helpoffers.domain.City;
import ua.pomoc.helpoffers.domain.HelpOffer;
import ua.pomoc.helpoffers.domain.InvitePeriod;

import java.util.List;

@Repository
public interface HelpOfferRepository extends JpaRepository<HelpOffer, Long> {
    @Query("SELECT ho FROM HelpOffer ho" +
            " JOIN City c ON c.id = ho.city.id" +
            " WHERE :cityId IS NULL OR ho.city.id IN (:cityId)" +
            " AND :places IS NULL OR ho.availablePlaces IN (:places)" +
            " AND :animals IS NULl OR ho.withAnimals IN (:animals)" +
            " AND :period IS NULL OR ho.invitePeriod IN (:period)")
    List<HelpOffer> findAllByParameters(
            @Param("cityId") Long cityId,
            @Param("places") Integer availablePlaces,
            @Param("animals") Boolean withAnimals,
            @Param("period") String invitePeriod);
}
