package com.manuel.proyecto.persistence

import com.manuel.proyecto.model.enity.Team
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository

@Suppress("UNCHECKED_CAST", "DEPRECATION")
@Repository
@Transactional
class TeamDao : MainFatherDao() {

    fun getAllTeams(): ArrayList<Team> {
        getNewSession().use { s ->
            val criteriaBuilder = s.criteriaBuilder
            val criteriaQuery = criteriaBuilder.createQuery(Team::class.java)
            val root = criteriaQuery.from(Team::class.java)
            criteriaQuery.select(root)
            val query = s.createQuery(criteriaQuery)
            return query.list() as ArrayList<Team>
        }
    }

    fun createTeam(team: Team): Team {
        getNewTransaction().use { s ->
            s.save(team)
            s.transaction.commit()
            return team
        }
    }

    fun updateTeam(team: Team): Team {
        getNewTransaction().use { s ->
            s.update(team)
            s.transaction.commit()
            return team
        }
    }

    fun deleteTeam(team: Team): Boolean {
        getNewTransaction().use { s ->
            s.remove(team)
            s.transaction.commit()
            return true
        }
    }

}
