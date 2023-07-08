package br.pucpr.authserver.mecanica.requests

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class MecanicaRequest(
        @field:NotBlank
        val name: String?,

        @field:Email
        val email: String?,

        @field:NotBlank
        val description: String?
)