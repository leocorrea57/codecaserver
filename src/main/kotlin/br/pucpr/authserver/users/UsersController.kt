package br.pucpr.authserver.users

import br.pucpr.authserver.users.requests.UserRequest
import br.pucpr.authserver.users.responses.UserResponse
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UsersController(val service: UsersService) {

    @GetMapping
    fun listUsers() = service.findAll().map { it.toResponse() }

    @Transactional
    @PostMapping
    fun createUser(@RequestBody @Validated req: UserRequest) =
            service.save(req)
                    .toResponse()
                    .let { ResponseEntity.status(CREATED).body(it) }

    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: Long) =
            service.getById(id).orElse(null)
                    ?.let { ResponseEntity.ok(it.toResponse()) }
                    ?: ResponseEntity.notFound().build()

    private fun User.toResponse() = UserResponse(id!!, name, email)
}