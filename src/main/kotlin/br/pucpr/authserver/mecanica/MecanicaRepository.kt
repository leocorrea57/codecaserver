package br.pucpr.authserver.mecanica

import br.pucpr.authserver.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface MecanicaRepository : JpaRepository<Mecanica, Long>
{
    @Modifying
    @Query("update Mecanica u set u.name = ?1, u.email = ?2, u.description = ?3 where u.mecanica_id = ?4")
    fun setMecInfoById(name: String?, email: String?, description: String?, id: Long?)

}