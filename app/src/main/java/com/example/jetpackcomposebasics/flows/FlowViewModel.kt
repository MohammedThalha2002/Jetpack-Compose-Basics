package com.example.jetpackcomposebasics.flows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FlowViewModel : ViewModel(){

    val countDownFlow = flow<Int> {
        val startingTime = 10
        var currentTime = startingTime

        while(currentTime >= 0){
            emit(currentTime)
            currentTime--;
            delay(1000L)
        }
    }

    init {
        collectFlow()
        flowOperators()
    }

    private fun collectFlow(){
        viewModelScope.launch {
            // this will be executed for every emit occurs
            countDownFlow.collect{time ->
                println("The current time is $time")
            }

            // this also get triggered when emit occurs
            countDownFlow.collectLatest{time ->
                // it will run only for the new emit occurs. If this block is running
                // and a new emit occurs then the block will be skipped
                // and update the new emit state
                // eg: adding a delay
                delay(1500L)
                println("The current time is $time")
            }
        }
    }

    private fun flowOperators(){
        viewModelScope.launch {
            countDownFlow
                .filter { time ->
                    time %2 == 0 // 1) filtering the flow
                }
                .map { time ->
                    time * time // 2) Modifying the values using Map
                }
                .onEach {time ->
                    println(time) // 3) For executing any things
                }
                .collect{time ->
                println("The current time is $time")
            }
        }
    }

    // flatten operators - used to concat the flows
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun flattenOperators(){
        // App - showing list of mobile phones with basic details
        // but we also need to show more details on the phone which requires phone id
        // both are different api - one for only phone another for individual phone

        // FLOW 1
        var getAllPhones = flow<String> {
            val phones = listOf("samsung-123", "iphone-311", "redmi-3322", "hawaii-342")
            for (phone in phones){
                emit(phone)
                delay(1000L)
            }
        }

        // FLOW 2
        val getPhoneDetailsById = flow<String> {
            val id = "123"
            delay(1000L)
            emit("128 gb, snapdragon")
        }

        viewModelScope.launch {
            getAllPhones.flatMapLatest{phone ->
                val id = phone.split("-")[1]
                getPhoneDetailsById
            }.collect{value ->
                println(value)
            }
        }


    }


}