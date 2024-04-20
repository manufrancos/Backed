package com.manuel.proyecto.persistence

import com.manuel.proyecto.model.enity.Player
import jakarta.transaction.Transactional
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Repository

@Suppress("UNCHECKED_CAST", "DEPRECATION")
@Repository
@Transactional
class PlayerDao : MainFatherDao(){

    fun getAllPlayers(): ArrayList<Player> {
        getNewSession().use { s->
            val criteriaBuilder = s.criteriaBuilder
            val criteriaQuery = criteriaBuilder.createQuery(Player::class.java)
            val root = criteriaQuery.from(Player::class.java)
            criteriaQuery.select(root)
            val query = s.createQuery(criteriaQuery)
            return query.list() as ArrayList<Player>
        }
    }

    fun createPlayer(player: Player): Player {
        getNewTransaction().use { s ->
            s.save(player)
            s.transaction.commit()
            return player
        }
    }

    fun updatePlayer(player: Player): Player {
        getNewTransaction().use { s ->
            s.update(player)
            s.transaction.commit()
            return player
        }
    }

    fun deletePlayer(player: Player): Boolean {
        getNewTransaction().use { s ->
            s.remove(player)
            s.transaction.commit()
            return true
        }
    }

    fun verifyIdTeam(idTeam: Int): Boolean {
        getNewSession().use { s ->
            //TODO intentar hacer
            return true
        }
    }

    fun verifyIdPlayer(idPlayer: Int): Boolean {
        getNewSession().use { s ->
            val criteriaBuilder = s.criteriaBuilder
            val criteriaQuery = criteriaBuilder.createQuery(Player::class.java)
            val root = criteriaQuery.from(Player::class.java)
            criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get<String>(""), id))
            val query = s.createQuery(criteriaQuery)
            val player =  query.uniqueResult() as Player?
            return player != null
        }
    }


}