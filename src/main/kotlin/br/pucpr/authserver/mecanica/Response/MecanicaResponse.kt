package br.pucpr.authserver.mecanica.Response

import br.pucpr.authserver.agendamento.Agendamento

data class MecanicaResponse(
        val id: Long,
        val name: String,
        val email: String,
        val description: String,
        val agendamento: MutableSet<Agendamento>
)