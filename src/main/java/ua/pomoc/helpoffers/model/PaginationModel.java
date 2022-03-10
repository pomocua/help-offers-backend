package ua.pomoc.helpoffers.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(onConstructor = @__({@JsonCreator}))
public class PaginationModel extends AbstractModel {
   private Long pages;
   private Long page;
   private Long perPage;
}
