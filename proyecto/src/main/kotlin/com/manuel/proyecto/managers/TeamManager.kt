package com.manuel.proyecto.managers

import com.manuel.proyecto.exception.BadRequest
import com.manuel.proyecto.model.enity.Team
import com.manuel.proyecto.persistence.TeamDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Suppress("UNCHECKED_CAST", "DEPRECATION")
class TeamManager {

    //Declara la clase que realiza la conexión con la BBDD
    @Autowired
    private lateinit var dao: TeamDao

    fun getAllTeams(): ArrayList<Team> {
        val data = dao.getAllTeams()
        data.sortBy { it.idTeam }
        return data
    }

    fun createTeam(team: Team): Team {
        team.idTeam = null
        if (team.name == null || team.city == null){
            throw  BadRequest("Equipo invalido")
        }
        return dao.createTeam(team)
    }

    fun updateTeam(team: Team): Team {
        if (team.name == null || team.city == null){
            throw  BadRequest("Equipo invalido")
        }
        return dao.updateTeam(team)
    }

    fun deleteTeam(team: Team): Boolean {
        if(!dao.verifyIdTeam(team.idTeam!!)){
            throw  BadRequest("Equipo invalido")
        }
        return dao.deleteTeam(team)
    }

}
