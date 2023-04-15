package br.pucpr.authserver.users

import org.springframework.stereotype.Component

@Component
class UsersRepository {
    val users = mutableListOf<User>()

    fun save(user: User): User {
        user.id = (users.size + 1).toLong()
        users.add(user)
        return user
    }

    fun getById(id: Long) = users.firstOrNull { it.id == id }

    fun findAll(): List<User> = users
}