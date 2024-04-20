package com.manuel.proyecto.controllers.restpublic

import com.manuel.proyecto.managers.TeamManager
import com.manuel.proyecto.model.enity.Team
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Controlador rest CRUD que gestiona los equipos de la BB.DD
 */
@RestController
class TeamController {

    //Declara la clase que hace la lógica
    @Autowired
    lateinit var manager: TeamManager

    /***
     * Metodo GET que retorna todos los equipos de la BB.DD
     */
    @GetMapping("/teams")
    fun getTeams(): ArrayList<Team> {
        return manager.getAllTeams()
    }

    /***
     * Metodo POST que inserta un equipo en la BB.DD
     */
    @PostMapping("/team")
    fun createTeam(@RequestBody team: Team): Team {
        return manager.createTeam(team)
    }

    /***
     * Metodo PUT que actualiza un equipo en la BB.DD
     */
    @PutMapping("/team")
    fun updateTeam(@RequestBody team: Team): Team {
        return manager.updateTeam(team)
    }

    /***
     * Metodo DELETE que elimina un equipo en la BB.DD
     */
    @DeleteMapping("/team/{idTeam}")
    fun deleteTeam(@PathVariable idTeam: Int): Boolean {
        return manager.deleteTeam(Team(idTeam))
    }

}
