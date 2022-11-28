package com.pariskhit.reactiveprogramming.services

import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

class FluxAndMonoServicesTest{

    private val fluxAndMonoServices = FluxAndMonoServices()

    @Test
     fun `should return flux of string`() {
         val languageFlux = fluxAndMonoServices.languageFlux()

        StepVerifier.create(languageFlux)
             .expectNext("Spanish","French", "English", "Portuguese","Russian","German")
             .verifyComplete()

    }

    @Test
    fun `should return mono of string`() {
        val language = fluxAndMonoServices.languageMono()

        StepVerifier.create(language)
            .expectNext(listOf("Japanese", "Chinese"))
            .verifyComplete()
    }

    @Test
    fun `should return flux of string with Map operator`() {
        val languageFlux = fluxAndMonoServices.languageFluxMap()

        StepVerifier.create(languageFlux)
            .expectNext("SPANISH","FRENCH", "ENGLISH", "PORTUGUESE","RUSSIAN","GERMAN")
            .verifyComplete()
    }

    @Test
    fun `should return mono of string with map operator`() {
        val language = fluxAndMonoServices.languageMonoMap()

        StepVerifier.create(language)
            .expectNext("SPANISH")
            .verifyComplete()
    }

    @Test
    fun `should retrun flux of string with filter operator`() {
        val languageFlux = fluxAndMonoServices.languageFluxFilter()

        StepVerifier.create(languageFlux)
            .expectNext("SPANISH","ENGLISH", "PORTUGUESE","RUSSIAN")
            .verifyComplete()
    }

    @Test
    fun `should return mono of string with filter operator`() {
        val language = fluxAndMonoServices.languageMonoFilter()

        StepVerifier.create(language)
            .expectNext("SPANISH")
            .verifyComplete()
    }

    @Test
    fun `should return nothing when it doesn't filter with give criteria`() {
        val language = fluxAndMonoServices.languageMonoFilter(7)

        StepVerifier.create(language)
            .expectNext()
            .verifyComplete()
    }
}