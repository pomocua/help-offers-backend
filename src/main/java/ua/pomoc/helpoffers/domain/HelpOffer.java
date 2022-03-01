package ua.pomoc.helpoffers.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.pomoc.helpoffers.model.BooleanType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "help_offer")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class HelpOffer extends AbstractEntityModel {

    private String fullName;
    private String phoneNumber;
    @ManyToOne
    private City city;
    private String address;
    private Integer availablePlaces;
    private Boolean withAnimals;
    private InvitePeriod invitePeriod;
    private String comment;

}