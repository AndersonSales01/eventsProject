package com.example.anderson.eventsproject.usecases

import com.example.anderson.eventsproject.RetrofitConfig
import com.example.anderson.eventsproject.data.network.EventApi
import com.example.anderson.eventsproject.data.repository.EventRepositoryImpl
import com.example.anderson.eventsproject.domain.model.CheckIn
import com.example.anderson.eventsproject.domain.model.Event
import com.example.anderson.eventsproject.domain.repo.IEventRepo
import com.example.anderson.eventsproject.domain.usecases.EfetuateCheckIn
import com.example.anderson.eventsproject.domain.usecases.GetEventDetails
import com.example.anderson.eventsproject.domain.usecases.GetEvents
import com.example.anderson.eventsproject.domain.usecases.ValidateEmail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import javax.inject.Inject


@ExperimentalCoroutinesApi
class UseCasesTest {

    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var endPoint: EventApi

    @MockK
    lateinit  var getEvent :  GetEvents
    lateinit var repo: IEventRepo
    lateinit  var eventDetails: GetEventDetails
    lateinit  var efetuateCheckIn: EfetuateCheckIn
    lateinit  var validateEmail: ValidateEmail

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)

        endPoint =  RetrofitConfig.getInstance().create(EventApi::class.java)
       // endPoint = mockk()
        repo = EventRepositoryImpl(endPoint)
        this.getEvent = GetEvents(repo)
        eventDetails = GetEventDetails(repo)
        efetuateCheckIn =EfetuateCheckIn(repo)
        validateEmail = ValidateEmail()
    }

    @After
    fun AfterUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `valid usecase GetEvents`() = runBlocking {
//        coEvery { repo.getEvents() } returns listOf(
//            Event(
//                1534784400, "aa",
//                "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
//                "-51.2148497", "-30.037878", 59.99f, "Feira de adoção de animais na Redenção", 1
//            )
//        )

        var list = getEvent.execute()

        print(list.size)

        assertFalse(list.isEmpty());

        //coVerify {getEvent.execute() }

      //  val list = getEvent.execute()
       // assertNotNull(list)
    }

    @Test
    fun `test usecase getEventDetails sucess`() = runBlocking {

        var event = eventDetails.execute(1)
        print(event?.title)
       assertEquals("Feira de adoção de animais na Redenção", event?.title)

    }

    @Test
    fun `test usecase getEventDetails fail`() = runBlocking {

        var event = eventDetails.execute(2)
        print(event?.title)
        assertFalse( event?.title == "Feira de adoção de animais na Redenção")

    }

    @Test
    fun `test usecase efetuate checkin sucess`() = runBlocking {

        var result = efetuateCheckIn.execute(CheckIn("teste","teste@hotmail.com","2" ))

        assertTrue(result)
    }


    @Test
    fun `test usecase valid email sucess`() {
       var result = validateEmail.execute("testd@hotmail.com")
        assertTrue(result)
    }

    @Test
    fun `test usecase valid email incompleted fail`() {
        var result = validateEmail.execute("testd@hotmail")
        assertFalse(result)
    }



}