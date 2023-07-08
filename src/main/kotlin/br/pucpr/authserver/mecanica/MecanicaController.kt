package br.pucpr.authserver.mecanica

import br.pucpr.authserver.mecanica.Response.MecanicaResponse
import br.pucpr.authserver.mecanica.requests.MecanicaRequest
import br.pucpr.authserver.mecanica.requests.MecanicaUpdateRequest
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/mecanicas")
class MecanicaController(val service: MecanicaService) {

    @GetMapping
    fun listMecanicas() = service.findAll().map { it.toResponse() }

    @Transactional
    @PostMapping
    fun createMecanica(@RequestBody @Validated req: MecanicaRequest) =
            service.save(req)
                    .toResponse()
                    .let { ResponseEntity.status(CREATED).body(it) }


    @Transactional
    @PostMapping("/update")
    fun updateMecanica(@RequestBody @Validated req: MecanicaUpdateRequest) =
        service.update(req)
            .toString()
            .let { ResponseEntity.status(CREATED).body(it) }


    @GetMapping("/{id}")
    fun getMecanica(@PathVariable("id") id: Long) =
            service.getById(id).orElse(null)
                    ?.let { ResponseEntity.ok(it.toResponse()) }
                    ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> =
        if (service.delete(id)) ResponseEntity.ok().build()
        else ResponseEntity.notFound().build()

    private fun Mecanica.toResponse() = MecanicaResponse(mecanica_id!!, name, email, description, agendamentos)
}