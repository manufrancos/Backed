package com.manuel.proyecto.controllers.restpublic

import com.manuel.proyecto.managers.PlayerManager
import com.manuel.proyecto.model.enity.Player
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Controlador rest CRUD que gestiona los jugadores de la BB.DD
 */
@RestController
class PlayerController {

    //Declara la clase que hace la logica
    @Autowired
    lateinit var manager: PlayerManager

    /***
     * Metodo GET que retorna todos los jugadoes de la BB.DD
     */
    @GetMapping("/players")
    fun getPlayers(): ArrayList<Player> {
        return manager.getAllPlayers()
    }

    /***
     * Metodo POST que inserta un jugador en la BB.DD
     */
    @PostMapping("/player")
    fun createPlayer(@RequestBody player: Player): Player {
        return manager.createPlayer(player)
    }

    /***
     * Metodo PUT que actualiza un jugador en la BB.DD
     */
    @PutMapping("/player")
    fun updatePlayer(@RequestBody player: Player): Player {
        return manager.updatePlayer(player)
    }

    /***
     * Metodo DELETE que elimina un jugador en la BB.DD
     */
    @DeleteMapping("/player/{idPlayer}")
    fun deletePlayer(@PathVariable idPlayer: Int): Boolean {
        return manager.deletePlayer(Player(idPlayer))
    }

}