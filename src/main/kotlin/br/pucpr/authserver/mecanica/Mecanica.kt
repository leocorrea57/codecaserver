package br.pucpr.authserver.mecanica

import br.pucpr.authserver.agendamento.Agendamento
import br.pucpr.authserver.mecanica.Response.MecanicaResponse

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "TblMecanica")
class Mecanica(

        @Id @GeneratedValue
        var mecanica_id: Long? = null,

        @Column(nullable = false)
        var name: String = "",

        @Email
        @Column(unique = true, nullable = false)
        var email: String,

        @Column(nullable = false)
        var description: String = "",

        @OneToMany(cascade = arrayOf(CascadeType.ALL) , mappedBy = "mecanica")
        val agendamentos: MutableSet<Agendamento> = mutableSetOf()
) {
    fun toResponse() = MecanicaResponse(mecanica_id!!, name, email, description, agendamentos)
}