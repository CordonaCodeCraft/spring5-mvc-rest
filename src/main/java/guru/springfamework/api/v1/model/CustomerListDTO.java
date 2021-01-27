package guru.springfamework.api.v1.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustomerListDTO {
    List<CustomerDTO> customers;
}
