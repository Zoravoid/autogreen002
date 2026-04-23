package com.iucosoft.mylinksspringboot.entities;

import com.iucosoft.mylinksspringboot.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
public class Player extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "player_id", unique = false, nullable = false)
    private Long playerId;

    @Column(name = "player", unique = false, nullable = false)
    private String player;

    @Column(name = "level", unique = false, nullable = false)
    private Long level;

    @Column(name = "difficulty1", unique = false, nullable = false)
    private Float difficulty1;

    @Column(name = "difficulty2", unique = false, nullable = false)
    private Float difficulty2;

    @Column(name = "difficulty3", unique = false, nullable = false)
    private Float difficulty3;

    @Column(name = "difficulty4", unique = false, nullable = false)
    private Float difficulty4;

    @Column(name = "difficulty5", unique = false, nullable = false)
    private Float difficulty5;

    @Column(name = "difficulty6", unique = false, nullable = false)
    private Float difficulty6;

    @Column(name = "difficulty7", unique = false, nullable = false)
    private Float difficulty7;

    @Column(name = "prediction_probability", unique = false, nullable = false)
    private Float predictionProbability;

    @Column(name = "result", unique = false, nullable = false)
    private Float result;

    @Column(name = "number_sense", unique = false, nullable = false)
    private Float numberSense;

    @Column(name = "counting", unique = false, nullable = false)
    private Float counting;

    @Column(name = "arithmetic", unique = false, nullable = false)
    private Float arithmetic;

    @Column(name = "visual_patterns", unique = false, nullable = false)
    private Float visualPatterns;

    @Column(name = "memory", unique = false, nullable = false)
    private Float memory;

    @Column(name = "stars", unique = false, nullable = true)
    private Float stars;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_stamp", nullable = false, length = 10)
    private Date timeStamp;
}
