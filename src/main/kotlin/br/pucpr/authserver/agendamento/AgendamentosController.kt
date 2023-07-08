package br.pucpr.authserver.agendamento

import br.pucpr.authserver.agendamento.requests.AgendamentoRequest
import br.pucpr.authserver.agendamento.requests.AgendamentoUpdateRequest
import br.pucpr.authserver.agendamento.response.AgendamentoResponse
import br.pucpr.authserver.mecanica.requests.MecanicaUpdateRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter


import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/agendamentos")
class AgendamentosController(val service: AgendamentosService) {

//    @GetMapping
//    fun listAgendamentos() = service.findAll().map { it.toResponse() }

    @Operation(
        summary = "Lista agendamentos por nome de mecanica",
        parameters = [
            Parameter(
                name = "name",
                example = "AutoSuper"
            )]
    )
    @GetMapping()
    fun listAgendamentosPorMecNome(@RequestParam("name") name: String?) =
        service.findByMecName(name)
            .map { it.toResponse() }

    @Transactional
    @PostMapping
    fun createAgendamento(@RequestBody @Validated req: AgendamentoRequest) =
            service.save(req)
                    .toResponse()
                    .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @Transactional
    @PostMapping("/update")
    fun updateAgendamento(@RequestBody @Validated req: AgendamentoUpdateRequest) =
        service.update(req)
            .toString()
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping("/{id}")
    fun getAgendamento(@PathVariable("id") id: Long) =
            service.getById(id).orElse(null)
                    ?.let { ResponseEntity.ok(it.toResponse()) }
                    ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> =
        if (service.delete(id)) ResponseEntity.ok().build()
        else ResponseEntity.notFound().build()

    private fun Agendamento.toResponse() = agendamentoId?.let { AgendamentoResponse(it,mecanica.name,data!!) }
}