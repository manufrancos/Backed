package com.manuel.proyecto.persistence

import com.manuel.proyecto.model.enity.Match
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository

@Repository
@Transactional
class MatchDao : MainFatherDao() {

    fun getAllMatches(): List<Match> {
        getNewSession().use { session ->
            val criteriaBuilder = session.criteriaBuilder
            val criteriaQuery = criteriaBuilder.createQuery(Match::class.java)
            val root = criteriaQuery.from(Match::class.java)
            criteriaQuery.select(root)
            val query = session.createQuery(criteriaQuery)
            return query.list() as List<Match>
        }
    }

    fun createMatch(match: Match): Match {
        getNewTransaction().use { session ->
            session.save(match)
            session.transaction.commit()
            return match
        }
    }

    fun updateMatch(match: Match): Match {
        getNewTransaction().use { session ->
            session.update(match)
            session.transaction.commit()
            return match
        }
    }

    fun deleteMatch(idMatch: Int): Boolean {
        getNewTransaction().use { session ->
            val matchToDelete = session.load(Match::class.java, idMatch)
            session.delete(matchToDelete)
            session.transaction.commit()
            return true
        }
    }
}

