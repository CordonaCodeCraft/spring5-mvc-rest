package guru.springfamework.api.v1.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String customerUrl;

}
