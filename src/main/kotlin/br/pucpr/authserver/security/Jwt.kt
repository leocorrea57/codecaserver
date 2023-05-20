package br.pucpr.authserver.security

import br.pucpr.authserver.users.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.jackson.io.JacksonSerializer
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

@Component
class Jwt {
    fun createToken(user: User) =
        UserToken(
            id = user.id ?: -1L,
            name = user.name,
            roles = user.roles.map { it.name }.toSortedSet()
        ).let {
            Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(SECRET.toByteArray()))
                .serializeToJsonWith(JacksonSerializer())
                .setIssuedAt(utcNow().toDate())
                .setExpiration(utcNow().plusHours(EXPIRE_HOURS).toDate())
                .setIssuer(ISSUER)
                .setSubject(it.id.toString())
                .addClaims(mapOf(USER_FIELD to it))
                .compact()
        }

    companion object {
        private const val PREFIX = "Bearer"
        private const val ISSUER = "Auth Server"
        private const val SECRET = "owp.z;8BhLq(l?2HM(5)u<x)Hg!A[J:h"
        private const val USER_FIELD = "user"
        private const val EXPIRE_HOURS = 24L

        private fun ZonedDateTime.toDate(): Date = Date.from(this.toInstant())
        private fun utcNow(): ZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC)
    }
}