package guru.springfamework.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity(name = "Vendor")
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Vendor vendor = (Vendor) other;

        return this.id != null &&
                this.getId().equals(vendor.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
