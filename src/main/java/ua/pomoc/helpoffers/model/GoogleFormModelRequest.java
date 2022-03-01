package ua.pomoc.helpoffers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.pomoc.helpoffers.domain.InvitePeriod;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class GoogleFormModelRequest extends AbstractModel {

    @JsonProperty("Pełne imię i nazwisko ")
    private String fullName;
    @JsonProperty("Miejscowość")
    private String city;
    @JsonProperty("Adres mieszkania ")
    private String address;
    @JsonProperty("Numer telefonu ")
    private String phoneNumber;
    @JsonProperty("Ile łóżek jest w mieszkaniu dla uchodźców? ")
    private Integer availablePlaces;
    @JsonProperty("Czy można mieszkać ze zwierzętami? ")
    private Boolean withAnimals;
    @JsonProperty("Jak długo są gotowi przyjąć uchodźcę? ")
    private InvitePeriod invitePeriod;
    @JsonProperty("Osobiste komentarze ")
    private String comment;

}
