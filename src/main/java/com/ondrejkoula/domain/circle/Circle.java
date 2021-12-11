package com.ondrejkoula.domain.circle;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "circles")
public class Circle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    protected String status;

    @Column(name = "sets_count")
    private Integer setsCount;

    @Column(name = "circles_in_count")
    private Integer circlesInCount;

    @Column(name = "prepare_time")
    private Integer prepareTime;

    @Column(name = "work_time")
    private Integer workTime;

    @Column(name = "rest_time")
    private Integer restTime;

    @Column(name = "time_between_sets")
    private Integer timeBetweenSets;

    @Column(name = "breathe_out_time")
    private Integer breatheOutTime;
}
