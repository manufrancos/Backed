package com.manuel.proyecto

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.core.task.TaskExecutor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@SpringBootApplication(exclude = [HibernateJpaAutoConfiguration::class, UserDetailsServiceAutoConfiguration::class])
@EnableAsync
@EnableScheduling
@EnableCaching
class ProyectoApplication{
	@Bean("threadPoolTaskExecutor")
	fun getAsyncExecutor(): TaskExecutor? {
		val executor = ThreadPoolTaskExecutor()
		executor.corePoolSize = 20
		executor.maxPoolSize = 1000
		executor.setWaitForTasksToCompleteOnShutdown(true)
		executor.setThreadNamePrefix("Async-")
		return executor
	}

	@PostConstruct
	fun init() {
		System.setProperty("java.net.preferIPv4Stack", "true")
	}

}

fun main(args: Array<String>) {
	runApplication<ProyectoApplication>(*args)
}
