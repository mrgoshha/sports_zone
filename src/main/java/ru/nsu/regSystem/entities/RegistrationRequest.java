package ru.nsu.regSystem.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
    private SportsEvent event;

    private boolean status;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void onCreate() { dateOfCreated = LocalDateTime.now(); }
}
