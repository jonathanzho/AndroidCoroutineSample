package com.sample.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelLazy
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by ViewModelLazy(
        MainViewModel::class,
        { viewModelStore },
        { defaultViewModelProviderFactory }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Test case 1:
        //sampleSuspendFunc()

        // Test case 2:
        //viewModel.exampleMethodUsingAsync()

        // Test case 3:
        viewModel.sampleRunBlocking()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")

        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")

        super.onPause()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")

        super.onDestroy()
    }

    /*
    Test case 1:
    This is to show how suspend funcs work:
     */
    private fun sampleSuspendFunc() {
        Log.d(TAG, "sampleSuspendFunc")

        GlobalScope.launch {
            val time = measureTimeMillis {
                val one = sampleOne()
                val two = sampleTwo()
                Log.d(TAG, "sampleSuspendFunc: The answer is ${one + two}")
            }

            Log.d(TAG, "sampleSuspendFunc: Completed in $time ms")
        }

        // This should go before the above block:
        Log.d(TAG, "sampleSuspendFunc: EOF")
    }

    private suspend fun sampleOne(): Int {
        Log.d(TAG, "sampleOne" + System.currentTimeMillis())

        delay(1000L) // pretend we are doing something useful here

        return 10
    }

    private suspend fun sampleTwo(): Int {
        Log.d(TAG, "sampleTwo" + System.currentTimeMillis())

        delay(1000L) // pretend we are doing something useful here, too

        return 10
    }

    companion object {
        private const val TAG = "CRTNSMPL: MainActivity"
    }
}