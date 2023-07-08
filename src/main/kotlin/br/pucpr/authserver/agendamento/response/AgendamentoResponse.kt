package br.pucpr.authserver.agendamento.response

import br.pucpr.authserver.mecanica.Mecanica

data class AgendamentoResponse(
        val id: Long,
        val mec : String,
        val date : String
)