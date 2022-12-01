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
    fun `should return flux of string with filter operator`() {
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

    @Test
    fun `should return flux of string with flatmap operator`() {
        val languageFlux = fluxAndMonoServices.languageFluxFlatMap()

        StepVerifier.create(languageFlux)
            .expectNextCount(39)
            .verifyComplete()
    }

    @Test
    fun `should return mono of string with flatMap operator`() {
        val language = fluxAndMonoServices.languageMonoFlatMap()

        StepVerifier.create(language)
            .expectNextCount(1)
            .verifyComplete()
    }

    @Test
    fun `should return mono of string with flatMapMany operator`() {
        val language = fluxAndMonoServices.languageMonoFlatMapMany()

        StepVerifier.create(language)
            .expectNext("SPANISH")
            .verifyComplete()
    }

    @Test
    fun `should return flux of string with transform operator`() {
        val languageFlux = fluxAndMonoServices.languageFluxTransform()

        StepVerifier.create(languageFlux)
            .expectNextCount(39)
            .verifyComplete()
    }

    @Test
    fun `should return mono of string with transform operator`() {
        val language = fluxAndMonoServices.languageMonoTransform()

        StepVerifier.create(language)
            .expectNextCount(1)
            .verifyComplete()
    }

    @Test
    fun `should return flux of string with defaultIfEmpty operator`() {
        val languageFlux = fluxAndMonoServices.languageFluxDefaultIfEmpty(12)

        StepVerifier.create(languageFlux)
            .expectNext("NEW_LANGUAGE")
            .verifyComplete()
    }

    @Test
    fun `should return mono of string with defaultIfEmpty operator`() {
        val language = fluxAndMonoServices.languageMonoDefaultIfEmpty(11)

        StepVerifier.create(language)
            .expectNextCount(1)
            .verifyComplete()
    }

    @Test
    fun `should return flux of string with switchIfEmpty operator`() {
        val languageFlux = fluxAndMonoServices.languageFluxSwitchIfEmpty(12)

        StepVerifier.create(languageFlux)
            .expectNext("DEFAULT_LANGUAGE")
            .verifyComplete()
    }

    @Test
    fun `should return mono of string with switchIfEmpty operator`() {
        val language = fluxAndMonoServices.languageMonoSwitchIfEmpty(11)

        StepVerifier.create(language)
            .expectNext(listOf("DEFAULT_LANGUAGE"))
            .verifyComplete()
    }

    @Test
    fun `should return flux of string with concat operator`() {
        val languageFlux = fluxAndMonoServices.languageFluxConcat(12)

        StepVerifier.create(languageFlux)
            .expectNext("Spanish","German","English","Japanese")
            .verifyComplete()
    }

    @Test
    fun `should return flux of string with concatWith operator`() {
        val languageFlux = fluxAndMonoServices.languageFluxConcatWith(12)

        StepVerifier.create(languageFlux)
            .expectNext("Spanish","German","English","Japanese")
            .verifyComplete()
    }

    @Test
    fun `should return mono of string with concatWith operator`() {
        val language = fluxAndMonoServices.languageMonoConcatWith(11)

        StepVerifier.create(language)
            .expectNext(listOf("DEFAULT_LANGUAGE"), listOf("ENGLISH"))
            .verifyComplete()
    }

    @Test
    fun `should return flux of string with merge operator`() {
        val languageFlux = fluxAndMonoServices.languageFluxMerge(12)

        StepVerifier.create(languageFlux)
            .expectNext("English","Japanese","Spanish","German")
            .verifyComplete()
    }

    @Test
    fun `should return flux of string with mergeWith operator`() {
        val languageFlux = fluxAndMonoServices.languageFluxMergeWith(12)

        StepVerifier.create(languageFlux)
            .expectNext("English","Japanese","Spanish","German")
            .verifyComplete()
    }

    @Test
    fun `should return flux for mono of string with mergeWith operator`() {
        val language = fluxAndMonoServices.languageMonoMergeWith(11)

        StepVerifier.create(language)
            .expectNext(listOf("ENGLISH"),listOf("DEFAULT_LANGUAGE"))
            .verifyComplete()
    }

    @Test
    fun `should return flux of string with mergeSequntial operator`() {
        val languageFlux = fluxAndMonoServices.languageFluxMergeWithSequential(12)

        StepVerifier.create(languageFlux)
            .expectNext("Spanish","German","English","Japanese")
            .verifyComplete()
    }
}