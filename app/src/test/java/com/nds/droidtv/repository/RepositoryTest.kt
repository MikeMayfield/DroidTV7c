package com.nds.droidtv.repository

import InstantExecutorExtension
import android.content.Context
import com.nds.droidtv.models.DeviceContext
import com.nds.droidtv.models.SearchContext
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock

@ExtendWith(InstantExecutorExtension::class)
internal class RepositoryTest {
    val mockContext: Context = mock(Context::class.java)

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `getSearchContext returns a SearchContext instance`() {
        //GIVEN

        //WHEN
        val searchContext = Repository.searchContext

        //THEN
        assertNotNull(searchContext)
        assert(searchContext is SearchContext)
    }

    @Test
    fun `getDeviceContext returns a DeviceContext instance`() {
        //GIVEN

        //WHEN
        val deviceContext = Repository.deviceContext

        //THEN
        assertNotNull(deviceContext)
        assert(deviceContext is DeviceContext)
    }

    @Test
    fun `getDeviceContext_casting_castingIsEnabled returns false when not implemented`() {
        //GIVEN

        //WHEN
        val casting = Repository.deviceContext.casting
        val castingEnabled = casting.castingIsEnabled.value!!

        //THEN
        assertNotNull(casting)
        assertFalse(castingEnabled)
    }
}