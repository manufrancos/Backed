package com.manuel.proyecto.model.enity

import jakarta.persistence.*
import java.util.*

@Entity
class Player {

    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "idPlayer", nullable = false)
    @get:Id
    var idPlayer: Int? = null

    @get:Column(name = "name",length = 50)
    @get:Basic
    var name: String? = null

    @get:Column(name = "birthday")
    @get:Basic
    var birthday: Date? = null

    @get:JoinColumn(name = "team", foreignKey = ForeignKey(name = "FK_PLAYER_TEAM"))
    @get:ManyToOne
    var team: Team? = null
}