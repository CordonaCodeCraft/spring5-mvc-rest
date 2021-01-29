package guru.springfamework.api.v1.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class VendorDTO {

    private String name;

    private String vendorUrl;
}
