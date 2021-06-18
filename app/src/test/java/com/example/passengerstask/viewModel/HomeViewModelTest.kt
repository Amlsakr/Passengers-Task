package com.example.passengerstask.viewModel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.passengerstask.data.getOrAwaitValue
import com.example.passengerstask.data.repository.MainRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class HomeViewModelTest{

    private lateinit var viewModel : HomeViewModel
    private lateinit var mainRepository: MainRepository
    @Before
    fun setup(){
        mainRepository = mock(MainRepository::class.java)
        viewModel = HomeViewModel(mainRepository)
    }
    @get:Rule
    var  instantExecutorRule = InstantTaskExecutorRule()
    @Test
    fun remoteliveDataHasValue(){
        runBlocking {
            viewModel.getJobsFromInternet()
            val value = viewModel.remote.getOrAwaitValue()
            assertNotNull(value)
        }
    }


}