package br.pucpr.authserver.users

import br.pucpr.authserver.users.requests.UserRequest
import org.springframework.stereotype.Service

@Service
class UsersService(val repository: UsersRepository,
                   val roleRepository: RoleRepository) {
    fun save(req: UserRequest): User {
        val user = User(
                email = req.email!!,
                password = req.password!!,
                name = req.name!!
        )
        val userRole = roleRepository.findByName("USER")
                ?: throw IllegalStateException("Role 'USER' not found!")

        user.roles.add(userRole)
        return repository.save(user)
    }

    fun getById(id: Long) = repository.findById(id)

    fun findAll() = repository.findAll()
}