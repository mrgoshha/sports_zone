package ru.nsu.regSystem.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sports_event")
public class SportsEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String name;

    private String startDateAndTime;

    private Integer numberOfHours;

    private Integer price;

    @Column(name = "countSeats")
    private Integer countSeats = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    private Trainers trainer;

    @ManyToOne(fetch = FetchType.EAGER)
    private SportsComplex sportsComplex;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "events", cascade = CascadeType.ALL)
    private List<User> visitors;




}