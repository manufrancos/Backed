package com.manuel.proyecto.persistence

import com.manuel.proyecto.config.MainDatabaseConfig
import org.hibernate.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@Suppress("UNCHECKED_CAST", "DEPRECATION")
class MainFatherDao {

    @Autowired
    lateinit var database: MainDatabaseConfig

    protected fun getNewSession(): Session {
        return database.secondaryTransactionManager()!!.sessionFactory!!.openSession()
    }

    protected fun getNewTransaction(): Session {
        val s: Session = database.primaryTransactionManager()!!.sessionFactory!!.openSession()
        if (!s.transaction.isActive) {
            s.transaction.begin()
        }
        return s
    }

}