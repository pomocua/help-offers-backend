package ua.pomoc.helpoffers.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.pomoc.helpoffers.domain.City;
import ua.pomoc.helpoffers.model.dto.CityModel;

import java.util.Collection;
import java.util.List;

@Mapper
public interface CityModelMapper {

    CityModelMapper MAPPER = Mappers.getMapper(CityModelMapper.class);

    List<CityModel> toModel(Collection<City> city);

    default CityModel toModel(City city) {
        return new CityModel(city.getId(), city.getName());
    }

}
