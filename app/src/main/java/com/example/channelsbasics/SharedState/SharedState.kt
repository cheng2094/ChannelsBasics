package com.example.channelsbasics

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//Esto es un problema porque no se logra ejecutar la cantidad de veces deseada, para resolver esto se usa el Atomic

fun main(){
    runBlocking {
        var counter = 0
        withContext(Dispatchers.Default){
            massiveRun { counter++ }
        }
        println("Counter = $counter")
    }
}

suspend fun  massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n){
                launch {
                    repeat(k){
                        action()
                    }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}