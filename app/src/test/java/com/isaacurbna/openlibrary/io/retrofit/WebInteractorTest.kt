package com.isaacurbna.openlibrary.io.retrofit

import org.junit.Assert
import org.junit.Test

class WebInteractorTest {

    val webInteractor = WebInteractor()

    @Test
    fun search() {
        val observable = webInteractor.search("tolkien")
        Assert.assertNotNull(observable)
    }
}