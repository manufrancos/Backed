package com.manuel.proyecto.model.enity

import jakarta.persistence.*
import java.util.*

@Entity
class Team {

    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Column(name = "idTeam", nullable = false)
    @get:Id
    var idTeam: Int? = null

    @get:Column(name = "name",length = 100)
    @get:Basic
    var name: String? = null

    @get:Column(name = "city",length = 100)
    @get:Basic
    var city: String? = null

    constructor(idTeam: Int){
        this.idTeam = idTeam
    }

    constructor(){}
}