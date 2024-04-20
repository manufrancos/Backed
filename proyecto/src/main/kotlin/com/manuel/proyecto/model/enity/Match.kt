package com.manuel.proyecto.model.enity

import jakarta.persistence.*
import java.util.*

@Entity
class Match {

    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "idMatch", nullable = false)
    @get:Id
    var idMatch: Int? = null

    @get:JoinColumn(name = "homeTeam", foreignKey = ForeignKey(name = "FK_MATCH_HOME_TEAM"))
    @get:ManyToOne
    var homeTeam: Team? = null

    @get:JoinColumn(name = "awayTeam", foreignKey = ForeignKey(name = "FK_MATCH_AWAY_TEAM"))
    @get:ManyToOne
    var awayTeam: Team? = null

    @get:Column(name = "homeGoals",nullable = false)
    @get:Basic
    var homeGoals: Int? = null

    @get:Column(name = "awayGoals",nullable = false)
    @get:Basic
    var awayGoals: Int? = null

    @get:Column(name = "matchDate")
    @get:Basic
    var matchday: Date? = null

    constructor(idMatch: Int) {
        this.idMatch = idMatch
    }

    constructor(){}

}
