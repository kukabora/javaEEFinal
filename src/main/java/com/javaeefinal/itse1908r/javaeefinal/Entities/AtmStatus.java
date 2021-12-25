package com.javaeefinal.itse1908r.javaeefinal.Entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "atmstatuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtmStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

}