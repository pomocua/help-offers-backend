package ua.pomoc.helpoffers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class City extends AbstractEntityModel {

    private String name;

    public City(Long id, String name) {
        super(id);
        this.name = name;
    }
}
