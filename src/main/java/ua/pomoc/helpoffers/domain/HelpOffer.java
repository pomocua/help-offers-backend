package ua.pomoc.helpoffers.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
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
    @JsonBackReference
    @ManyToOne
    private City city;
    private String address;
    private Integer availablePlaces;
    private Boolean withAnimals;
    private InvitePeriod invitePeriod;
    private String comment;

}