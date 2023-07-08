package br.pucpr.authserver.agendamento

import br.pucpr.authserver.mecanica.Mecanica
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "TblAgendamento")
class Agendamento(
        @Id @GeneratedValue
        val agendamentoId: Long? = null,

        @Column(nullable = false)
        val data: String = "",

        @JsonIgnore
        @ManyToOne(cascade = arrayOf(CascadeType.MERGE))
        @JoinColumn(
                name = "mecanica_id",
        )
        var mecanica: Mecanica

//        @ManyToMany(mappedBy = "roles")
//        val users: MutableSet<User> = mutableSetOf()
)