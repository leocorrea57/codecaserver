package br.pucpr.authserver.mecanica

import br.pucpr.authserver.agendamento.Agendamento
import br.pucpr.authserver.agendamento.AgendamentosRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

class MecanicaBootstrap {
    @Component
    class MecanicaBootstrap(
        val mecRepository: MecanicaRepository,
        val ageRepository: AgendamentosRepository
    ) : ApplicationListener<ContextRefreshedEvent> {
        override fun onApplicationEvent(event: ContextRefreshedEvent) {
            val mec = Mecanica(name = "Super Auto", description = "Auto peças e mecânica", email = "autosuper@gmail.com")
            if (mecRepository.count() == 0L) {
                mecRepository.save(mec)
            }
            if (ageRepository.count() == 0L) {
                val agendamento = Agendamento(data = "04/05/22 14:00", mecanica = mec)
                mec.agendamentos.add(agendamento)
                ageRepository.save(agendamento)
            }
        }
    }
}