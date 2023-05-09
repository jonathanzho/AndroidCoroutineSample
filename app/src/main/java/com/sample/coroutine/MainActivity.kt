package com.sample.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
        tc1SampleSuspendFunc()
        Thread.sleep(10000L)

        // Test case 2:
        viewModel.tc2ExampleMethodUsingAsync()
        Thread.sleep(10000L)

        // Test case 3:
        viewModel.tc3SampleRunBlocking()
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
    This is to show how suspend fun works:
     */
    private fun tc1SampleSuspendFunc() {
        Log.d(TAG, "tc1SampleSuspendFunc")

        GlobalScope.launch {
            val time = measureTimeMillis {
                val one = tc1SampleOne()
                val two = tc1SampleTwo()
                Log.d(TAG, "tc1SampleSuspendFunc: The answer is ${one + two}")
            }

            Log.d(TAG, "tc1SampleSuspendFunc: Completed in $time ms")
        }

        // This should go before the above block:
        Log.d(TAG, "tc1SampleSuspendFunc: EOF")
    }

    private suspend fun tc1SampleOne(): Int {
        Log.d(TAG, "tc1SampleOne: currentTimeMillis=" + System.currentTimeMillis())

        delay(1000L) // pretend we are doing something useful here

        return 1000
    }

    private suspend fun tc1SampleTwo(): Int {
        Log.d(TAG, "tc1SampleTwo: currentTimeMillis=" + System.currentTimeMillis())

        delay(2000L) // pretend we are doing something useful here, too

        return 2000
    }

    companion object {
        private const val TAG = "CRTNSMPL: MainActivity"
    }
}