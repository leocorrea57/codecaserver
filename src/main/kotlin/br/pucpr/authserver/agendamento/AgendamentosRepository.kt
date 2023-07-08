package br.pucpr.authserver.agendamento

import br.pucpr.authserver.mecanica.Mecanica
import br.pucpr.authserver.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface AgendamentosRepository : JpaRepository<Agendamento, Long>{

    @Modifying
    @Query("update Agendamento u set u.data = ?1, u.agendamentoId = ?2")
    fun setAgeInfoById(data: String?, id: Long?)

    @Query(
        value = "select u from Agendamento u" +
                " where u.mecanica.name = :mec" +
                " order by u.mecanica.name"
    )
    fun findAllByMecName(mec: String): List<Agendamento>
}