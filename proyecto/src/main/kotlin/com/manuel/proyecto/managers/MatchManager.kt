package com.manuel.proyecto.managers

import com.manuel.proyecto.exception.BadRequest
import com.manuel.proyecto.model.enity.Match
import com.manuel.proyecto.persistence.MatchDao
import com.manuel.proyecto.persistence.TeamDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Suppress("UNCHECKED_CAST", "DEPRECATION")
class MatchManager {

    // Declara la clase que realiza la conexión con la BBDD
    @Autowired
    private lateinit var dao: MatchDao

    @Autowired
    private lateinit var teamDao: TeamDao

    fun getMatchOfTeam(idTeam: Int): List<Match> {
        val data = dao.getAllMatches()
        return data.filter { it.awayTeam!!.idTeam == idTeam || it.homeTeam!!.idTeam == idTeam }
    }

    fun createMatch(match: Match): Match {
        match.idMatch = null
        validateMatch(match)
        return dao.createMatch(match)
    }

    fun updateMatch(match: Match): Match {
        validateMatch(match)
        return dao.updateMatch(match)
    }

    fun deleteMatch(idMatch: Int): Boolean {
        return dao.deleteMatch(idMatch)
    }

    private fun validateMatch(match: Match){
        if (match.homeTeam == null || match.homeTeam!!.idTeam == null ||
            match.awayTeam == null || match.awayTeam!!.idTeam == null ||
            match.homeGoals == null || match.homeGoals!! <0 ||
            match.awayGoals == null || match.awayGoals!! <0 ||
            match.matchday ==null) {
            throw BadRequest("Partido invalido")
        }

        if(match.homeTeam!!.idTeam  == match.awayTeam!!.idTeam){
            throw BadRequest("El equipo tiene que ser diferente")
        }

        if( !teamDao.verifyIdTeam(match.homeTeam!!.idTeam!!) || !teamDao.verifyIdTeam(match.awayTeam!!.idTeam!!)){
            throw BadRequest("Equipos invalidos")
        }

    }
}
