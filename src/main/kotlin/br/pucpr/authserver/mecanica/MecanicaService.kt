package br.pucpr.authserver.mecanica

//import br.pucpr.authserver.exception.BadRequestException
//import br.pucpr.authserver.security.Jwt
//import br.pucpr.authserver.users.requests.LoginRequest
//import br.pucpr.authserver.users.requests.UserRequest
//import br.pucpr.authserver.users.responses.LoginResponse
//import org.slf4j.LoggerFactory
import br.pucpr.authserver.agendamento.AgendamentosRepository
import br.pucpr.authserver.mecanica.requests.MecanicaRequest
import br.pucpr.authserver.mecanica.requests.MecanicaUpdateRequest
import br.pucpr.authserver.users.UsersService.Companion.log

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MecanicaService(val repository: MecanicaRepository, val ageRepository: AgendamentosRepository) {
    fun save(req: MecanicaRequest): Mecanica {
        val mec = Mecanica(
                email = req.email!!,
                description = req.description!!,
                name = req.name!!
        )
        log.warn("Mecanica adicionada. id={} name={}", mec.mecanica_id, mec.name)
        return repository.save(mec)
    }

    fun delete(id: Long): Boolean {
        val mec = repository.findByIdOrNull(id) ?: return false
        log.warn("Mecanica removida. id={} name={}", mec.mecanica_id, mec.name)
        repository.delete(mec)
        return true
    }

    fun update(mecUpdate : MecanicaUpdateRequest) : Boolean
    {
        val mec = repository.findByIdOrNull(mecUpdate.mecId) ?: return false

        repository.setMecInfoById(name = mecUpdate.name, email = mecUpdate.email, description = mecUpdate.description, id = mecUpdate.mecId)

        log.warn("Mecanica atualizada. id={} name={}", mec.mecanica_id, mecUpdate.name)

        return true
    }

    fun getById(id: Long) = repository.findById(id)

    fun findAll() = repository.findAll()
}