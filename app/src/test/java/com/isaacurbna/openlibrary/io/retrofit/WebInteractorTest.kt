package com.isaacurbna.openlibrary.io.retrofit

import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class WebInteractorTest {

    @Mock
    private lateinit var webInteractor: WebInteractor

    @Before
    fun setup() {
        webInteractor = WebInteractor()
    }

    @Test
    fun whenSearchQueryIsNotNullNetworkRequestGoesOut() {
        // TODO: implement this test add test cases
        /*val observable = webInteractor.search("tolkien")
        Assert.assertNotNull(observable)*/
    }
}