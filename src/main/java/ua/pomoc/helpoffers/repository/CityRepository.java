package ua.pomoc.helpoffers.repository;

import org.springframework.core.annotation.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.pomoc.helpoffers.domain.City;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    Optional<City> findByName(String name);
}
