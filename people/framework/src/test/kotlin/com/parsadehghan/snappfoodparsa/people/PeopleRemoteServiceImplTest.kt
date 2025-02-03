import com.parsadehghan.gateway.StarWarsApiService
import com.parsadehghan.trysedalia.login.framework.PeopleRemoteServiceImpl
import com.parsadehghan.base.BaseDomain
import com.parsadehghan.base.error_handler.dataStateInternalErrorHandler
import com.parsadehghan.base.error_handler.dataStateRemoteErrorHandler
import com.parsadehghan.base.interactor.DataState
import com.parsadehghan.starwars.core.Character
import com.parsadehghan.starwars.core.StarWarsCharactersResponse
import com.parsadehghan.starwars.people.domian.GetCharacterListObject
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class PeopleRemoteServiceImplTest {

    private lateinit var productApiService: StarWarsApiService
    private lateinit var peopleRemoteService: PeopleRemoteServiceImpl

    @Before
    fun setup() {
        productApiService = mock()
        peopleRemoteService = PeopleRemoteServiceImpl(productApiService)
    }

    @Test
    fun `getPeoples with successful response should return DataState with GetCharacterListObject`() = runBlocking {
        // Given
        val baseDomain: BaseDomain = GetCharacterListObject.GetCharacterListRequest()
        val characterObject = mock<Character>()
        val mockResponse = Response.success(
            StarWarsCharactersResponse(
                totalCharacters = 1,
                nextPageUrl = "https://swapi.dev/api/people/?page=2",
                previousPageUrl = null,
                characters = listOf(characterObject)
            )
        )

        // Mock API response
        whenever(productApiService.getPeoples(null, null)).thenReturn(mockResponse)

        // When
        val result = peopleRemoteService.getPeoples(baseDomain)

        // Then
        val expectedCharacters = mockResponse.body()?.characters
        val actualCharacters = (result as DataState.Data).data as GetCharacterListObject.GetCharacterListResponse
        assertEquals(expectedCharacters, actualCharacters.characters)
    }

    @Test
    fun `getPeoples with unsuccessful response should return appropriate error DataState`() = runBlocking {
        // Given
        val baseDomain: BaseDomain = GetCharacterListObject.GetCharacterListRequest()
        val errorCode = 404
        val errorResponse = Response.error<StarWarsCharactersResponse>(errorCode, "Not Found".toResponseBody())

        // Mock API response
        whenever(productApiService.getPeoples(null, null)).thenReturn(errorResponse)

        // When
        val result = peopleRemoteService.getPeoples(baseDomain)

        // Then
        assertEquals(dataStateRemoteErrorHandler(errorCode), result)
    }

    @Test
    fun `getPeoples with network error should return appropriate error DataState`() = runBlocking {
        // Given
        val baseDomain: BaseDomain = GetCharacterListObject.GetCharacterListRequest()
        val exception = RuntimeException("Network error")

        // Mock API response
        whenever(productApiService.getPeoples(null, null)).thenThrow(exception)

        // When
        val result = peopleRemoteService.getPeoples(baseDomain)

        // Then
        assertEquals(dataStateInternalErrorHandler(0), result)
    }
}
