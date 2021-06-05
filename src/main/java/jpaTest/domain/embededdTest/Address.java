package jpaTest.domain.embededdTest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Embeddable

public class Address {
    private String street;
    private String zipcode;

    public Address(String street, String zipcode) {
        this.street = street;
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipcode);
    }
}
