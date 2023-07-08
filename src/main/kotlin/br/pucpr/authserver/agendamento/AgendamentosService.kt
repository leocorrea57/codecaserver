package br.pucpr.authserver.agendamento

import br.pucpr.authserver.agendamento.requests.AgendamentoRequest
import br.pucpr.authserver.agendamento.requests.AgendamentoUpdateRequest
import br.pucpr.authserver.mecanica.Mecanica
import br.pucpr.authserver.mecanica.MecanicaRepository
import br.pucpr.authserver.mecanica.requests.MecanicaUpdateRequest
import br.pucpr.authserver.users.User
import br.pucpr.authserver.users.UsersService.Companion.log
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AgendamentosService(val repository: AgendamentosRepository,
                          val mecanicasRepository: MecanicaRepository
) {
    fun save(req: AgendamentoRequest): Agendamento {

        var mecanica: Mecanica

        val mec = mecanicasRepository.findById(req.mecId).let {
           mecanica = it.get()
        }

        val age = Agendamento(data = req.date, mecanica = mecanica)
//        val userRole = roleRepository.findByName("USER")
//                ?: throw IllegalStateException("Role 'USER' not found!")

        mecanica.agendamentos.add(age)
        //mecanicasRepository.save(mecanica)
        age.mecanica = mecanica
        log.warn("Agendamento incluido. id={} data={} mecanica={}" , age.agendamentoId, age.data, mecanica.name)
        return repository.save(age)
    }

    fun delete(id: Long): Boolean {
        val age = repository.findByIdOrNull(id) ?: return false
        log.warn("Agendamento excluido. id={} data={} mecanica={}" , age.agendamentoId, age.data, age.mecanica.name)
        repository.delete(age)
        return true
    }

    fun update(ageUpdate : AgendamentoUpdateRequest) : Boolean
    {
        val age = repository.findByIdOrNull(ageUpdate.ageId) ?: return false

        repository.setAgeInfoById(data = ageUpdate.date, id = ageUpdate.ageId)

        log.warn("Agendamento atualizado. id={} data={} mecanica={}", age.agendamentoId, age.data, age.mecanica.name)

        return true
    }

    fun findByMecName(mecName: String?): List<Agendamento>
    {
        if (mecName == null) return repository.findAll()
        else return repository.findAllByMecName(mecName)
    }

    fun getById(id: Long) = repository.findById(id)

    fun findAll() = repository.findAll()
}