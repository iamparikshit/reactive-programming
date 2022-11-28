package com.pariskhit.reactiveprogramming.services

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class FluxAndMonoServices {
    private val languages = listOf("Spanish", "French", "English", "Portuguese", "Russian", "German")
    private val maxLengthOfLanguage = 6

    fun languageFlux(): Flux<String> {
        return Flux.fromIterable(languages).log()
    }

    fun languageMono(): Mono<List<String>> {
        return Mono.just(listOf("Japanese", "Chinese")).log()
    }

    fun languageFluxMap(): Flux<String> {
        return Flux.fromIterable(languages)
            .map { it.uppercase() }
            .log()
    }

    fun languageMonoMap(): Mono<String?> {
        return Mono.just(languages)
            .map { it.firstOrNull()?.uppercase() }
            .log()
    }

    fun languageFluxFilter(): Flux<String> {
        return Flux.fromIterable(languages)
            .filter { it.length > maxLengthOfLanguage }
            .map { it.uppercase() }
            .log()
    }

    fun languageMonoFilter(maxLength : Int  = maxLengthOfLanguage): Mono<String?> {
        return Mono.just(languages)
            .map { it.firstOrNull() }
            .filter { it?.length?: 0 > maxLength }
            .map { it?.uppercase() }
            .log()
    }

}