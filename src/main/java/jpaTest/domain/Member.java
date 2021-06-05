package jpaTest.domain;

import jpaTest.domain.embededdTest.Address;
import jpaTest.domain.embededdTest.Period;
import lombok.*;

import javax.persistence.*;
import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.List;


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


    //임베디드 타입을 값타입 리스트로 사용할 경우 테이블을 새로 만들어버리는데
    //이 테이블에는 아이디 값이 없고 , 임베디드 타입의 모든 필드를 기본키로 쓰는 테이블을 만들어낸다.
    //컬렉션의 형태이기 때문에 만약 중간에서 빼낸다면 null이 들어갈 수 도 있는 문제가 있어
    //내부적으로 값타입 컬랙션의 변경이나 제거시에 테이블의 모든데이터를 지우고 해당테이블에 있던것을 다시 집어넣는다
    //때문에 값타입 컬랙션 같은 경우는 그 값타입을 필드로 갖는 엔티티를 만들어
    //한번 랩핑해서 사용하고  사용하는 쪽에서는 1대다 맵핑을 해줘서 사용하는 것이좋다 .
    //1대 다 맵핑의 특성상 반대편에 외래키가 있기 때문에 업데이트 문이 나간다.
    //값타입용도로 사용하는 것이기 떄문에 라이프사이클을 부모객체에서 관리하는 것이 좋다 .
    //값타입 리스트안의 값을 제가할 시에 list.get.remove() <-메서드가 equals로 찾아내기떄문에 equals hash코드를 구현하는 것이 좋다
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private List<AddressHistory> addressHistories = new ArrayList<>();

    //값타입 컬랙션을 사용하기 위한 방법이다.
    //테이블을 만들어낸다.
    //생명주기가 자동으로 관리된다.
    @ElementCollection
    @CollectionTable(name= "FAVORITE_FOOD",joinColumns = @JoinColumn(name = "member_id"))
    private List<String> favoriteFoods = new ArrayList<>();



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


}
