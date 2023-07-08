package br.pucpr.authserver.Mecanica

import br.pucpr.authserver.agendamento.AgendamentosRepository
import br.pucpr.authserver.exception.BadRequestException
import br.pucpr.authserver.mecanica.Mecanica
import br.pucpr.authserver.mecanica.MecanicaRepository
import br.pucpr.authserver.mecanica.MecanicaService
import br.pucpr.authserver.security.Jwt
import br.pucpr.authserver.users.RolesRepository
import br.pucpr.authserver.users.Stubs.userStub
import br.pucpr.authserver.users.UsersRepository
import br.pucpr.authserver.users.UsersService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

internal class MecanicaServiceTest {
    private val mecanicasRepositoryMock = mockk<MecanicaRepository>()
    private val agendamentosRepositoryMock = mockk<AgendamentosRepository>()


    private val service = MecanicaService(mecanicasRepositoryMock, agendamentosRepositoryMock)

    @Test
    fun `Delete should return false if the mec does not exists`() {
        every { mecanicasRepositoryMock.findByIdOrNull(1) } returns null
        service.delete(1) shouldBe false
    }

    @Test
    fun `Delete must return true if the mec is deleted`() {
        val mec = Mecanica(name = "AutoMais", description = "AutoMais", email = "automais@gmail.com")
        every { mecanicasRepositoryMock.findByIdOrNull(1) } returns mec
        justRun { mecanicasRepositoryMock.delete(mec) }
        service.delete(1) shouldBe true
    }
}