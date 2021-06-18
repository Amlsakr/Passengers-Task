package com.example.passengerstask.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.passengerstask.data.locale.AirLineDao
import com.example.passengerstask.data.locale.AirLineRoomDataBase
import com.example.passengerstask.data.model.AirLineItem
import com.example.passengerstask.data.retrofit.AirLineApiHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var jobsApiHelper: AirLineApiHelper
    private lateinit var db: AirLineRoomDataBase
    private lateinit var mainRepository : MainRepository
    private lateinit var dao : AirLineDao

    @Before()
    fun setup() {
        db = mock(AirLineRoomDataBase::class.java)
         mainRepository = MainRepository(jobsApiHelper,db)
        dao = mock(AirLineDao::class.java)
    }

    @Test
    fun `getAll()with success then return list of item`() {
        runBlocking {

            val airLineItem = mock(AirLineItem::class.java)
            val result = listOf(airLineItem)
            `when`(dao.getAll()).thenReturn(result)
            val response = dao.getAll()

            Assert.assertNotNull(response)

            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun `getAll()with empty then return empty list of item`() {
        runBlocking {

            val result = emptyList<AirLineItem>()
            `when`(dao.getAll()).thenReturn(result)
            val response = dao.getAll()

            Assert.assertEquals(response.size, 0)
        }
    }

    @Test
    fun `insert()with confirm item in list`() {

        runBlocking {
         //   val mainRepository = MainRepository(jobsApiHelper,db)
            val airLineItem = mock(AirLineItem::class.java)
            dao.insert(airLineItem)
            val result = listOf(airLineItem)
            `when`(dao.getAll()).thenReturn(result)
            val response = dao.getAll()
            Assert.assertEquals(response.contains(airLineItem) , true)
        }
    }

    @Test
    fun `getAirline()with success then return success response`(){
        runBlocking {
            val airLineItem = mock(AirLineItem::class.java)
            val result = listOf(airLineItem)
            `when`(mainRepository.getJobsFomInternet()).thenReturn(Response.success(result))
            val response = mainRepository.getJobsFomInternet()
            var success : List<AirLineItem>? = null
            var error : String? = null

            if( response.isSuccessful){
                success = response.body()
            }else {
                error = response.errorBody().toString()
            }
            //Assert Authentication
            Assert.assertNotNull(success)
            Assert.assertNull(error)
            Assert.assertEquals(success!!, result)
        }
    }
}