package br.pucpr.authserver.mecanica.requests

import br.pucpr.authserver.mecanica.Response.MecanicaResponse
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class MecanicaUpdateRequest(

    @NotNull
    val mecId: Long?,

    @field:NotBlank
    val name: String?,

    @field:Email
    val email: String?,

    @field:NotBlank
    val description: String?
)