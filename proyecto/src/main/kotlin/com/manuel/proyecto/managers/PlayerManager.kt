package com.manuel.proyecto.managers

import com.manuel.proyecto.exception.BadRequest
import com.manuel.proyecto.model.enity.Player
import com.manuel.proyecto.persistence.PlayerDao
import com.manuel.proyecto.persistence.TeamDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Suppress("UNCHECKED_CAST", "DEPRECATION")
class PlayerManager {

    //Declara la clase que realiza la conexon con BBDD
    @Autowired
    private lateinit var dao: PlayerDao

    @Autowired
    private lateinit var teamDao: TeamDao

    fun getAllPlayers(): ArrayList<Player> {
        return dao.getAllPlayers()
    }

    fun createPlayer(player: Player): Player {
        player.idPlayer = null
        if(player.name.isNullOrEmpty() || player.birthday == null || player.team == null || player.team?.idTeam == null){
            throw BadRequest("Jugador invalido")
        }

        if(!teamDao.verifyIdTeam(player.team!!.idTeam!!)){
            throw BadRequest("Equipo invalido")
        }

        return dao.createPlayer(player)
    }

    fun updatePlayer(player: Player): Player {
        if(player.idPlayer != null && player.name.isNullOrEmpty() || player.birthday == null || player.team == null || player.team?.idTeam == null){
            throw BadRequest("Jugador invalido")
        }

        if(!teamDao.verifyIdTeam(player.team!!.idTeam!!)){
            throw BadRequest("Equipo invalido")
        }
        return dao.updatePlayer(player)
    }

    fun deletePlayer(player: Player): Boolean {
        if(!dao.verifyIdPlayer(player.idPlayer!!)){
            throw BadRequest("Jugador invalido")
        }
        return dao.deletePlayer(player)
    }

}