package com.manuel.proyecto.controllers.restpublic

import com.manuel.proyecto.managers.MatchManager
import com.manuel.proyecto.model.enity.Match
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Controlador rest CRUD que gestiona los partido de la BB.DD
 */
@RestController
class MatchController {

    //Declara la clase que hace la lógica
    @Autowired
    lateinit var manager: MatchManager

    /***
     * Metodo GET que retorna todos los partido de la BB.DD
     */
    @GetMapping("/matches")
    fun getMatches(): List<Match> {
        return manager.getAllMatches()
    }

    /***
     * Metodo POST que inserta un partido en la BB.DD
     */
    @PostMapping("/match")
    fun createMatch(@RequestBody match: Match): Match {
        return manager.createMatch(match)
    }

    /***
     * Metodo PUT que actualiza un partido en la BB.DD
     */
    @PutMapping("/match")
    fun updateMatch(@RequestBody match: Match): Match {
        return manager.updateMatch(match)
    }

    /***
     * Metodo DELETE que elimina un partido en la BB.DD
     */
    @DeleteMapping("/match/{idMatch}")
    fun deleteMatch(@PathVariable idMatch: Int): Boolean {
        return manager.deleteMatch(idMatch)
    }
}
