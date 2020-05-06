package com.nds.droidtv.models

import InstantExecutorExtension
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nds.droidtv.models.SearchContext.SearchCategory
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.rules.TestRule

@ExtendWith(InstantExecutorExtension::class)
internal class SearchContextTest {
    private lateinit var searchContext: SearchContext

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
        searchContext = SearchContext()
    }

    @Test
    fun `performSearch Returns proper results when just category changed`() {
        //GIVEN

        //WHEN
        searchContext.performSearch(SearchCategory.ALL, "", false, false)
        val allResult = searchContext.seriesList.value!!
        searchContext.performSearch(SearchCategory.CLASSIC, "", false, false)
        val classicResult = searchContext.seriesList.value!!
        searchContext.performSearch(SearchCategory.DVR, "", false, false)
        val dvrResult = searchContext.seriesList.value!!
        searchContext.performSearch(SearchCategory.PLAYABLE, "", false, false)
        val playableResult = searchContext.seriesList.value!!
        searchContext.performSearch(SearchCategory.PRIMETIME, "", false, false)
        val primetimeResult = searchContext.seriesList.value!!
        searchContext.performSearch(SearchCategory.NEW_SERIES, "", false, false)
        val newSeriesResult = searchContext.seriesList.value!!

        //THEN
        assertEquals(5, allResult.size)
        assertEquals(1, classicResult.size)
        assertEquals(1, dvrResult.size)
        assertEquals(1, playableResult.size)
        assertEquals(4, primetimeResult.size)
        assertEquals(5, allResult.size)
    }

    @Test
    fun `performSearch Returns proper results when favorites filter changed`() {
        //GIVEN

        //WHEN
        searchContext.performSearch(SearchCategory.ALL, "", false, false)
        val allResult = searchContext.seriesList.value!!
        searchContext.performSearch(SearchCategory.ALL, "", true, false)
        val favoritesResult = searchContext.seriesList.value!!

        //THEN
        assertEquals(5, allResult.size)
        assertEquals(3, favoritesResult.size)
    }

    @Test
    fun `performSearch Returns proper results when playable filter changed`() {
        //GIVEN

        //WHEN
        searchContext.performSearch(SearchCategory.ALL, "", false, false)
        val allResult = searchContext.seriesList.value!!
        searchContext.performSearch(SearchCategory.ALL, "", false, true)
        val playableResults = searchContext.seriesList.value!!

        //THEN
        assertEquals(5, allResult.size)
        assertEquals(1, playableResults.size)
    }

    @Test
    fun `performSearch Returns proper results when both favorites and playable filters changed`() {
        //GIVEN

        //WHEN
        searchContext.performSearch(SearchCategory.ALL, "", false, false)
        val allResult = searchContext.seriesList.value!!
        searchContext.performSearch(SearchCategory.ALL, "", false, true)
        val playableResults = searchContext.seriesList.value!!
        searchContext.performSearch(SearchCategory.ALL, "", true, false)
        val favoritesResults = searchContext.seriesList.value!!
        searchContext.performSearch(SearchCategory.ALL, "", true, true)
        val favoritesAndPlayable = searchContext.seriesList.value!!

        //THEN
        assertEquals(5, allResult.size)
        assertEquals(1, playableResults.size)
        assertEquals(3, favoritesResults.size)
        assertEquals(1, favoritesAndPlayable.size)
    }

    @Test
    fun `changing filter triggers a search`() {
        //GIVEN

        //WHEN
        searchContext.category = SearchCategory.ALL
        searchContext.playableFilterIsEnabled = false
        searchContext.favoritesFilterIsEnabled = false
        val noFiltersResult = searchContext.seriesList.value!!

        searchContext.playableFilterIsEnabled = true
        val playableIgnoreFavoriteResult = searchContext.seriesList.value!!

        searchContext.favoritesFilterIsEnabled = true
        val playableFavoriteResult = searchContext.seriesList.value!!

        searchContext.playableFilterIsEnabled = false
        val ignorePlayableFavoriteResult = searchContext.seriesList.value!!

        searchContext.category = SearchCategory.PLAYABLE
        searchContext.playableFilterIsEnabled = false
        searchContext.favoritesFilterIsEnabled = false
        val impliedPlayableFavoriteResult = searchContext.seriesList.value!!

        //THEN
        assertEquals(5, noFiltersResult.size)
        assertEquals(1, playableIgnoreFavoriteResult.size)
        assertEquals(1, playableFavoriteResult.size)
        assertEquals(3, ignorePlayableFavoriteResult.size)
        assertEquals(1, impliedPlayableFavoriteResult.size)
    }
}