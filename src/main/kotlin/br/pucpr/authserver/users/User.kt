package br.pucpr.authserver.users

import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
@Table(name = "TblUser")
open class User(
        @Id @GeneratedValue
        var id: Long? = null,

        @Email
        @Column(unique = true, nullable = false)
        var email: String,

        @Column(length = 50)
        var password: String,

        @Column(nullable = false)
        var name: String = "",

        @ManyToMany
        @JoinTable(
                name = "UserRole",
                joinColumns = [JoinColumn(name = "idUser")],
                inverseJoinColumns = [JoinColumn(name = "idRole")]
        )
        val roles: MutableSet<Role> = mutableSetOf()
)