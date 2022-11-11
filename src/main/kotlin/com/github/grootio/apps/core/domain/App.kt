package com.github.grootio.apps.core.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "app")
class App(val name: String, val language: String, val technology: String) {
    @Id
    val id: String = UUID.randomUUID().toString()
}