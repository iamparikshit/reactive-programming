package com.pariskhit.reactiveprogramming.services

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class FluxAndMonoServices {
    private val languages = listOf("Spanish", "French", "English", "Portuguese", "Russian", "German")

    fun languageFlux() : Flux<String>{
        return Flux.fromIterable(languages).log()
    }

    fun languageMono() : Mono<String> {
        return Mono.just("Spanish").log()
    }
}