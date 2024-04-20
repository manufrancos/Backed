package com.manuel.proyecto.managers

import com.manuel.proyecto.exception.BadRequest
import com.manuel.proyecto.model.enity.Match
import com.manuel.proyecto.persistence.MatchDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Suppress("UNCHECKED_CAST", "DEPRECATION")
class MatchManager {

    // Declara la clase que realiza la conexión con la BBDD
    @Autowired
    private lateinit var dao: MatchDao

    fun getAllMatches(): List<Match> {
        return dao.getAllMatches()
    }

    fun createMatch(match: Match): Match {
        return dao.createMatch(match)
    }

    fun updateMatch(match: Match): Match {
        return dao.updateMatch(match)
    }

    fun deleteMatch(idMatch: Int): Boolean {
        return dao.deleteMatch(idMatch)
    }
}
