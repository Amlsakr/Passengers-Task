package com.example.passengerstask.data.retrofit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.passengerstask.data.model.AirLineItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AirLineHelperImplementationTest{



    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    private lateinit var  airlineApiService: AirLineApiService
    private lateinit var  airLineHelperImplementation: AirLineHelperImplementation
    @Before
    fun setup (){
        airLineHelperImplementation = AirLineHelperImplementation(airlineApiService)
    }
    @Test
    fun `getAirline()with success then return success response`(){
     runBlocking {
         val airLineItem = mock(AirLineItem::class.java)
         val result = listOf(airLineItem)
         `when`(airLineHelperImplementation.getPassengers()).thenReturn(Response.success(result))
         val response = airLineHelperImplementation.getPassengers()
         var success : List<AirLineItem>? = null
         var error : String? = null

        if( response.isSuccessful){
            success = response.body()
        }else {
            error = response.errorBody().toString()
        }
         //Assert Authentication
         assertNotNull(success)
         assertNull(error)
         assertEquals(success!!, result)
     }
    }


    @Test
    fun `getAirline()with failure then return error message`(){
        runBlocking {
            val result = RuntimeException("Unknown Error")
            `when`(airLineHelperImplementation.getPassengers()).thenThrow(result)
         var response : Response<List<AirLineItem>>? = null
            var success : List<AirLineItem>? = null
            var error : String? = null
            try {
                airLineHelperImplementation.getPassengers()
            }catch (e: RuntimeException){
                error = "Unknown Error"
            }
   //             success = response.body()
            //Assert Authentication
            assertNull(success)
            assertNotNull(error)
           assertEquals(error, result.message)
        }
    }


}
