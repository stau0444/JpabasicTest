package jpaTest.domain.SuperCTest;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "M")
public class Movie  extends Item{

    private String director;

    private String actor;

}
