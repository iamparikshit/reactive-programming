package com.pariskhit.reactiveprogramming.services

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

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

    fun languageMonoFilter(maxLength: Int = maxLengthOfLanguage): Mono<String?> {
        return Mono.just(languages)
            .map { it.firstOrNull() }
            .filter { it?.length ?: 0 > maxLength }
            .map { it?.uppercase() }
            .log()
    }

    fun languageFluxFlatMap(): Flux<String> {
        return Flux.fromIterable(languages)
            .filter { it.length > maxLengthOfLanguage }
            .map { it.uppercase() }
            .flatMap { Flux.fromIterable(it.split("")) }
            .log()
    }

    fun languageMonoFlatMap(maxLength: Int = maxLengthOfLanguage): Mono<List<String>> {
        return Mono.just(languages)
            .map { it.firstOrNull() }
            .filter { it?.length ?: 0 > maxLength }
            .map { it?.uppercase() }
            .flatMap { it?.let { it1 -> Mono.just(it1.split("")) } }
            .log()
    }

    fun languageMonoFlatMapMany(maxLength: Int = maxLengthOfLanguage): Flux<String> {
        return Mono.just(languages)
            .map { it.firstOrNull() }
            .filter { it?.length ?: 0 > maxLength }
            .map { it?.uppercase() }
            .flatMapMany { it?.let { it1 -> Mono.just(it1) } }
            .log()
    }

    fun languageFluxTransform(): Flux<String> {
        fun filterData(input: Flux<String>): Flux<String> {
            return input.filter { it.length > maxLengthOfLanguage }
        }
        return Flux.fromIterable(languages)
            .map { it.uppercase() }
            .transform { filterData(it) }
            .concatMap { Flux.fromIterable(it.split("")) }
            .map { it.trim() }
            .log()
    }

    fun languageMonoTransform(): Mono<List<String>> {
        fun filterData(input: Mono<String?>): Mono<String?> {
            return input?.filter { it?.length ?: 0 > maxLengthOfLanguage }
        }

        return Mono.just(languages)
            .map { it.firstOrNull() }
            .transform { filterData(it) }
            .map { it?.uppercase() }
            .flatMap { it?.let { it1 -> Mono.just(it1.split("")) } }
            .log()
    }

    fun languageFluxDefaultIfEmpty(maxLength : Int = maxLengthOfLanguage): Flux<String> {
        fun filterData(input: Flux<String>): Flux<String> {
            return input.filter { it.length > maxLength }
        }
        return Flux.fromIterable(languages)
            .map { it.uppercase() }
            .transform { filterData(it) }
            .flatMap { Flux.fromIterable(it.split("")) }
            .defaultIfEmpty("NEW_LANGUAGE")
            .log()
    }

    fun languageMonoDefaultIfEmpty(maxLength : Int = maxLengthOfLanguage): Mono<List<String>> {
        fun filterData(input: Mono<String?>): Mono<String?> {
            return input?.filter { it?.length ?: 0 > maxLength }
        }

        return Mono.just(languages)
            .map { it.firstOrNull() }
            .transform { filterData(it) }
            .map { it?.uppercase() }
            .flatMap { it?.let { it1 -> Mono.just(it1.split("")) } }
            .defaultIfEmpty(listOf("DEFAULT_LANGUAGE"))
            .log()
    }

    fun languageFluxSwitchIfEmpty(maxLength : Int = maxLengthOfLanguage): Flux<String> {
        fun filterData(input: Flux<String>): Flux<String> {
            return input.filter { it.length > maxLength }
        }

        return Flux.fromIterable(languages)
            .map { it.uppercase() }
            .transform { filterData(it) }
            .flatMap { Flux.fromIterable(it.split("")) }
            .switchIfEmpty(Flux.just("DEFAULT_LANGUAGE"))
            .log()
    }


    fun languageMonoSwitchIfEmpty(maxLength : Int = maxLengthOfLanguage): Mono<List<String>> {
        fun filterData(input: Mono<String?>): Mono<String?> {
            return input?.filter { it?.length ?: 0 > maxLength }
        }

        return Mono.just(languages)
            .map { it.firstOrNull() }
            .transform { filterData(it) }
            .map { it?.uppercase() }
            .flatMap { it?.let { it1 -> Mono.just(it1.split("")) } }
            .switchIfEmpty(Mono.just(listOf("DEFAULT_LANGUAGE")))
            .log()
    }

    fun languageFluxConcat(maxLength : Int = maxLengthOfLanguage): Flux<String> {
        val oldLanguages = Flux.just("Spanish","German")
        val newLanguages =  Flux.just("English", "Japanese")

        fun filterData(input: Flux<String>): Flux<String> {
            return input.filter { it.length > maxLength }
        }

        return Flux.fromIterable(languages)
            .map { it.uppercase() }
            .transform { filterData(it) }
            .flatMap { Flux.fromIterable(it.split("")) }
            .switchIfEmpty(Flux.concat(oldLanguages,newLanguages))
            .log()
    }

    fun languageFluxConcatWith(maxLength : Int = maxLengthOfLanguage): Flux<String> {
        val newLanguages =  Flux.just("English", "Japanese")

        fun filterData(input: Flux<String>): Flux<String> {
            return input.filter { it.length > maxLength }
        }

        return Flux.fromIterable(languages)
            .map { it.uppercase() }
            .transform { filterData(it) }
            .flatMap { Flux.fromIterable(it.split("")) }
            .switchIfEmpty(Flux.just("Spanish","German"))
            .concatWith(newLanguages)
            .log()
    }

    fun languageMonoConcatWith(maxLength : Int = maxLengthOfLanguage): Flux<List<String>> {
        val newLanguages =  Mono.just(listOf("ENGLISH"))
        fun filterData(input: Mono<String?>): Mono<String?> {
            return input?.filter { it?.length ?: 0 > maxLength }
        }

        return Mono.just(languages)
            .map { it.firstOrNull() }
            .transform { filterData(it) }
            .map { it?.uppercase() }
            .flatMap { it?.let { it1 -> Mono.just(it1.split("")) } }
            .switchIfEmpty(Mono.just(listOf("DEFAULT_LANGUAGE")))
            .concatWith(newLanguages)
            .log()
    }

    fun languageFluxMerge(maxLength : Int = maxLengthOfLanguage): Flux<String> {
        val oldLanguages = Flux.just("Spanish", "German")
            .delayElements(Duration.ofMillis(90))
        val newLanguages =  Flux.just("English", "Japanese")

        fun filterData(input: Flux<String>): Flux<String> {
            return input.filter { it.length > maxLength }
        }

        return Flux.fromIterable(languages)
            .map { it.uppercase() }
            .transform { filterData(it) }
            .flatMap { Flux.fromIterable(it.split("")) }
            .switchIfEmpty(Flux.merge(oldLanguages,newLanguages))
            .log()
    }

    fun languageFluxMergeWith(maxLength : Int = maxLengthOfLanguage): Flux<String> {
        val oldLanguages = Flux.just("Spanish", "German")
            .delayElements(Duration.ofMillis(90))
        val newLanguages =  Flux.just("English", "Japanese")

        fun filterData(input: Flux<String>): Flux<String> {
            return input.filter { it.length > maxLength }
        }

        return Flux.fromIterable(languages)
            .map { it.uppercase() }
            .transform { filterData(it) }
            .flatMap { Flux.fromIterable(it.split("")) }
            .switchIfEmpty(oldLanguages)
            .mergeWith(newLanguages)
            .log()
    }

    fun languageMonoMergeWith(maxLength : Int = maxLengthOfLanguage): Flux<List<String>> {
        val newLanguages =  Mono.just(listOf("ENGLISH"))
        fun filterData(input: Mono<String?>): Mono<String?> {
            return input?.filter { it?.length ?: 0 > maxLength }
        }

        return Mono.just(languages)
            .map { it.firstOrNull() }
            .transform { filterData(it) }
            .map { it?.uppercase() }
            .flatMap { it?.let { it1 -> Mono.just(it1.split("")) } }
            .switchIfEmpty(Mono.just(listOf("DEFAULT_LANGUAGE")).delayElement(Duration.ofSeconds(1)))
            .mergeWith(newLanguages)
            .log()
    }

    fun languageFluxMergeWithSequential(maxLength : Int = maxLengthOfLanguage): Flux<String> {
        val oldLanguages = Flux.just("Spanish", "German")
            .delayElements(Duration.ofMillis(90))
        val newLanguages =  Flux.just("English", "Japanese")

        fun filterData(input: Flux<String>): Flux<String> {
            return input.filter { it.length > maxLength }
        }

        return Flux.fromIterable(languages)
            .map { it.uppercase() }
            .transform { filterData(it) }
            .flatMap { Flux.fromIterable(it.split("")) }
            .switchIfEmpty(Flux.mergeSequential(oldLanguages,newLanguages))
            .log()
    }

}