package jpaTest.domain;

import jpaTest.domain.embededdTest.Address;
import jpaTest.domain.embededdTest.Period;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //기간
    @Embedded
    private Period period;

    //주소
    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "zipcode",column = @Column(name = "work_zipcode")),
            @AttributeOverride(name = "street",column = @Column(name = "work_street"))
    })
    private Address workAddress;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;


}
