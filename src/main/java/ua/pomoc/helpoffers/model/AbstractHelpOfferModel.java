package ua.pomoc.helpoffers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.pomoc.helpoffers.domain.InvitePeriod;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public abstract class AbstractHelpOfferModel extends AbstractModel {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String address;
    private Integer availablePlaces;
    private Boolean withAnimals;
    private InvitePeriod invitePeriod;
    private String comment;

    public AbstractHelpOfferModel(String fullName, String phoneNumber, String address, Integer availablePlaces, Boolean withAnimals, InvitePeriod invitePeriod, String comment) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.availablePlaces = availablePlaces;
        this.withAnimals = withAnimals;
        this.invitePeriod = invitePeriod;
        this.comment = comment;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }
}



