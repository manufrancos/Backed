package com.manuel.proyecto.managers

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
        return dao.getAllTeams()
    }

    fun createTeam(team: Team): Team {
        // TODO: verificar que el equipo tiene los datos correctos
        return dao.createTeam(team)
    }

    fun updateTeam(team: Team): Team {
        // TODO: verificar que el equipo tiene los datos correctos
        return dao.updateTeam(team)
    }

    fun deleteTeam(team: Team): Boolean {
        return dao.deleteTeam(team)
    }

}
