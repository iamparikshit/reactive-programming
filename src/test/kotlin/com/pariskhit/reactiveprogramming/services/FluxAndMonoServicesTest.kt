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
            .expectNext("Spanish")
            .verifyComplete()
    }
}