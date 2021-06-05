package jpaTest.domain;

import jpaTest.domain.embededdTest.Address;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class AddressHistory {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    public AddressHistory(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressHistory that = (AddressHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address);
    }
}
