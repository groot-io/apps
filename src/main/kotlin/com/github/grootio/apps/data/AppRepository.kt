package com.github.grootio.apps.data

import com.github.grootio.apps.core.domain.App
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AppRepository : JpaRepository<App, String>