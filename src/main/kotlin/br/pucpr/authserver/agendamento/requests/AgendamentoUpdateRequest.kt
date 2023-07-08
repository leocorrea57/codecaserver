package br.pucpr.authserver.agendamento.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AgendamentoUpdateRequest(

    @NotNull
    val ageId: Long?,

    @field:NotBlank
    val date: String

)