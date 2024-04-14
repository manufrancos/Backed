package com.manuel.proyecto.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class MainDatabaseConfig {

    @Value("\${postgres.driver}")
    private val dbDriver: String? = null

    @Value("\${postgres.username}")
    private val dbUsername: String? = null

    @Value("\${postgres.password}")
    private val dbPassword: String? = null

    @Value("\${postgres.datasource.primary.url}")
    private val dbPrimaryUrl: String? = null

    @Value("\${postgres.datasource.secondary.url}")
    private val dbSecondaryUrl: String? = null

    @Value("\${hibernate.dialect}")
    private val hibernateDialect: String? = null

    @Value("\${hibernate.show_sql}")
    private val hibernateShowSql: String? = null

    @Value("\${hibernate.current_session_context_class}")
    private val hibernateCurrentSessionContextClass: String? = null

    private val HIBERNATE_DML = "none"

    @Bean
    @Primary
    fun primaryDataSource() : DataSource{
        val dataSource =  DriverManagerDataSource()
        dataSource.setDriverClassName(dbDriver!!)
        dataSource.url = dbPrimaryUrl
        dataSource.username = dbUsername
        dataSource.password = dbPassword
        return dataSource
    }

    @Bean
    fun secondaryDataSource() : DataSource{
        val dataSource =  DriverManagerDataSource()
        dataSource.setDriverClassName(dbDriver!!)
        dataSource.url = dbSecondaryUrl
        dataSource.username = dbUsername
        dataSource.password = dbPassword
        return dataSource
    }

    @Bean
    @Primary
    fun primarySessionFactory(): LocalSessionFactoryBean {
        val sessionFactoryBean = LocalSessionFactoryBean()
        sessionFactoryBean.setDataSource(primaryDataSource())
        sessionFactoryBean.setPackagesToScan(packagesToScan)
        //sessionFactoryBean.setImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl())
        //sessionFactoryBean.setPhysicalNamingStrategy(PhysicalNamingStrategyStandardImpl())
        val hibernateProperties = Properties()
        hibernateProperties["hibernate.dialect"] = hibernateDialect
        hibernateProperties["hibernate.show_sql"] = hibernateShowSql
        hibernateProperties["hibernate.hbm2ddl.auto"] = HIBERNATE_DML
        hibernateProperties["hibernate.current_session_context_class"] = hibernateCurrentSessionContextClass
        hibernateProperties["hibernate.c3p0.max_size"] = 50
        hibernateProperties["hibernate.globally_quoted_identifiers"] = true
        sessionFactoryBean.hibernateProperties = hibernateProperties
        return sessionFactoryBean
    }

    @Bean
    fun secondarySessionFactory(): LocalSessionFactoryBean {
        val sessionFactoryBean = LocalSessionFactoryBean()
        sessionFactoryBean.setDataSource(secondaryDataSource())
        sessionFactoryBean.setPackagesToScan(packagesToScan)
        //sessionFactoryBean.setImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl())
        //sessionFactoryBean.setPhysicalNamingStrategy(PhysicalNamingStrategyStandardImpl())
        val hibernateProperties = Properties()
        hibernateProperties["hibernate.dialect"] = hibernateDialect
        hibernateProperties["hibernate.show_sql"] = hibernateShowSql
        hibernateProperties["hibernate.hbm2ddl.auto"] = HIBERNATE_DML
        hibernateProperties["hibernate.current_session_context_class"] = hibernateCurrentSessionContextClass
        hibernateProperties["hibernate.c3p0.max_size"] = 50
        hibernateProperties["hibernate.globally_quoted_identifiers"] = true
        sessionFactoryBean.hibernateProperties = hibernateProperties
        return sessionFactoryBean
    }

    @Bean
    @Primary
    fun primaryTransactionManager(): HibernateTransactionManager? {
        val transactionManager = HibernateTransactionManager()
        transactionManager.sessionFactory = primarySessionFactory().getObject()
        return transactionManager
    }

    @Bean
    fun secondaryTransactionManager(): HibernateTransactionManager? {
        val transactionManager = HibernateTransactionManager()
        transactionManager.sessionFactory = secondarySessionFactory().getObject()
        return transactionManager
    }

    companion object {
        const val packagesToScan = "com.manuel.proyecto"
    }
}