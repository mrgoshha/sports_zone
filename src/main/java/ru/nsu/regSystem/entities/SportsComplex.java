package ru.nsu.regSystem.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sports_complexes")
public class SportsComplex {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "countSeats")
    private Integer countSeats;

    @OneToMany(mappedBy = "sportsComplex", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<SportsEvent> sportsEvent;
}
