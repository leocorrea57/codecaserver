package br.pucpr.authserver.agendamento.requests

import br.pucpr.authserver.mecanica.Mecanica
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class AgendamentoRequest(

        @field:NotBlank
        val date: String,

        @field:NotNull
        val mecId : Long
)