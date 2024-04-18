package com.manuel.proyecto.managers

import com.manuel.proyecto.model.enity.Player
import com.manuel.proyecto.persistence.PlayerDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Suppress("UNCHECKED_CAST", "DEPRECATION")
class PlayerManager {

    //Declara la clase que realiza la conexon con BBDD
    @Autowired
    private lateinit var dao: PlayerDao

    fun getAllPlayers(): ArrayList<Player> {
        return dao.getAllPlayers()
    }

    fun createPlayer(player: Player): Player {
        //TODO verificar que el player tiene los datos correctos
        return dao.createPlayer(player)
    }

    fun updatePlayer(player: Player): Player {
        //TODO verificar que el player tiene los datos correctos
        return dao.updatePlayer(player)
    }

    fun deletePlayer(player: Player): Boolean {
        return dao.deletePlayer(player)
    }

}