package com.sample.coroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel : ViewModel() {
    /*
    Test case 2:
    async
     */
    fun tc2ExampleMethodUsingAsync() {
        Log.d(TAG, "tc2ExampleMethodUsingAsync")

        Log.d(TAG,"tc2ExampleMethodUsingAsync: First statement of Async")

        viewModelScope.launch {
                val one = async { tc2SampleOne() }
                val two = async { tc2SampleTwo() }
            if (one.await() && two.await()) {
                Log.d(TAG, "tc2ExampleMethodUsingAsync: Both returned true")
            } else {
                Log.d(TAG, "tc2ExampleMethodUsingAsync: Someone returned false")
            }
        }

        Log.d(TAG, "tc2ExampleMethodUsingAsync: Last statement of Async")
    }

    private suspend fun tc2SampleOne(): Boolean {
        Log.d(TAG, "tc2SampleOne")

        delay(4000L)

        return true
    }

    private suspend fun tc2SampleTwo(): Boolean {
        Log.d(TAG, "tc2SampleTwo")

        delay(3000L)

        return false
    }

    /*
    Test case 3:
    runBlocking
     */
    fun tc3SampleRunBlocking() {
        Log.d(TAG, "tc3SampleRunBlocking")

        Log.d(TAG, "tc3SampleRunBlocking: First statement of runBlocking")

        runBlocking {
            delay(3000L)

            Log.d(TAG, "tc3SampleRunBlocking: Middle  statement of runBlocking")
        }

        Log.d(TAG, "tc3SampleRunBlocking: Last statement of runBlocking")

    }

    companion object {
        private const val TAG = "CRTNSMPL: MainViewModel"
    }
}
