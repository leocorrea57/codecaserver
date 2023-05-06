package br.pucpr.authserver.users

import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class UsersBootstrap(
        val roleRepository: RoleRepository,
        val userRepository: UsersRepository
) : ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val adminRole = Role(name = "ADMIN")
        if (roleRepository.count() == 0L) {
            roleRepository.save(adminRole)
            roleRepository.save(Role(name = "USER"))
        }
        if (userRepository.count() == 0L) {
            val admin = User(
                    email = "admin@authserver.com",
                    password = "admin",
                    name = "Auth Server Administrator",
            )
            admin.roles.add(adminRole)
            userRepository.save(admin)
        }
    }
}