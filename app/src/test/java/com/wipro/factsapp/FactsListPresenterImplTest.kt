package com.wipro.factsapp

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.wipro.factsapp.adapter.FactViewHolder
import com.wipro.factsapp.model.Fact
import com.wipro.factsapp.model.FactsResponse
import junit.framework.TestCase
import org.junit.Assert
import org.mockito.Mockito


class FactsListPresenterImplTest : TestCase() {

    private val view = Mockito.mock(FactsListView::class.java)
    private val context = Mockito.mock(Context::class.java)
    private val connectivityManager = Mockito.mock(ConnectivityManager::class.java)

    private val presenter = FactsListPresenterImpl(view)

    public override fun setUp() {
        Mockito.doReturn(connectivityManager).`when`(context)
            .getSystemService(Context.CONNECTIVITY_SERVICE)
    }

    fun test_RestoreState_showsLoading_whenSavedInstanceStateIsNull() {
        presenter.restoreState(null, context)
        Mockito.verify(view).showLoading()
    }

    fun test_RestoreState_showsError_whenSavedInstanceStateIsNotNull_andErrorStringIsPresent() {
        val bundle = Mockito.mock(Bundle::class.java)
        Mockito.doReturn(R.string.error_no_internet).`when`(bundle)
            .getInt(FactsListPresenterImpl.ERROR_STRING)

        presenter.restoreState(bundle, context)
        Mockito.verify(view, Mockito.never()).showLoading()
        Mockito.verify(view).showError(R.string.error_no_internet)
    }

    fun test_RestoreState_showsList_whenSavedInstanceStateIsNotNull_andListIsPresent() {
        val bundle = Mockito.mock(Bundle::class.java)
        val fact = Fact().also { fact ->
            fact.title = "Test fact Title"
            fact.description = "Test Desc"
        }
        val list = listOf(fact)
        val response = FactsResponse().also { response ->
            response.list = list
            response.title = "Test Title"
        }
        Mockito.doReturn(true).`when`(bundle).containsKey(FactsListPresenterImpl.RESPONSE_JSON)
        Mockito.doReturn(Gson().toJson(response)).`when`(bundle)
            .getString(FactsListPresenterImpl.RESPONSE_JSON)

        Mockito.doAnswer { invocation ->
            val factsList: List<Fact> = invocation.arguments[0] as List<Fact>
            Assert.assertEquals(1, factsList.size)
            Assert.assertEquals("Test fact Title", factsList[0].title)
            Assert.assertEquals("Test Desc", factsList[0].description)
        }.`when`(view).showList(KotlinMockUtils.any())

        presenter.restoreState(bundle, context)
        Mockito.verify(view, Mockito.never()).showLoading()
        Mockito.verify(view).setActionbarTitle("Test Title")
    }

    fun test_LoadFacts_showsNoInternetError_whenInternetIsNotAvailable() {
        presenter.loadFacts(context)
        Mockito.verify(view).showError(R.string.error_no_internet)
    }

    fun test_loadFactData_hidesTitle_whenTitleIsEmpty() {
        val itemView = Mockito.mock(View::class.java)
        val titleView = Mockito.mock(TextView::class.java)
        Mockito.doReturn(titleView).`when`(itemView).findViewById<TextView>(R.id.fact_title)

        val descView = Mockito.mock(TextView::class.java)
        Mockito.doReturn(descView).`when`(itemView).findViewById<TextView>(R.id.description)

        val factImage = Mockito.mock(ImageView::class.java)
        Mockito.doReturn(factImage).`when`(itemView).findViewById<ImageView>(R.id.fact_image)

        val holder = FactViewHolder(itemView)
        val fact = Fact()
        presenter.loadFactData(holder, fact)
        Mockito.verify(titleView).visibility = View.GONE
    }

    fun test_loadFactData_showsTitle_whenTitleIsNotEmpty() {
        val itemView = Mockito.mock(View::class.java)
        val titleView = Mockito.mock(TextView::class.java)
        Mockito.doReturn(titleView).`when`(itemView).findViewById<TextView>(R.id.fact_title)

        val descView = Mockito.mock(TextView::class.java)
        Mockito.doReturn(descView).`when`(itemView).findViewById<TextView>(R.id.description)

        val factImage = Mockito.mock(ImageView::class.java)
        Mockito.doReturn(factImage).`when`(itemView).findViewById<ImageView>(R.id.fact_image)

        val holder = FactViewHolder(itemView)
        val fact = Fact().also { fact ->
            fact.title = "Test Title"
        }
        presenter.loadFactData(holder, fact)
        Mockito.verify(titleView).visibility = View.VISIBLE
        Mockito.verify(titleView).text = "Test Title"
    }

    fun test_loadFactData_hidesDesc_whenDescIsEmpty() {
        val itemView = Mockito.mock(View::class.java)
        val titleView = Mockito.mock(TextView::class.java)
        Mockito.doReturn(titleView).`when`(itemView).findViewById<TextView>(R.id.fact_title)

        val descView = Mockito.mock(TextView::class.java)
        Mockito.doReturn(descView).`when`(itemView).findViewById<TextView>(R.id.description)

        val factImage = Mockito.mock(ImageView::class.java)
        Mockito.doReturn(factImage).`when`(itemView).findViewById<ImageView>(R.id.fact_image)

        val holder = FactViewHolder(itemView)
        val fact = Fact()
        presenter.loadFactData(holder, fact)
        Mockito.verify(descView).visibility = View.GONE
    }

    fun test_loadFactData_showsDesc_whenDescIsNotEmpty() {
        val itemView = Mockito.mock(View::class.java)
        val titleView = Mockito.mock(TextView::class.java)
        Mockito.doReturn(titleView).`when`(itemView).findViewById<TextView>(R.id.fact_title)

        val descView = Mockito.mock(TextView::class.java)
        Mockito.doReturn(descView).`when`(itemView).findViewById<TextView>(R.id.description)

        val factImage = Mockito.mock(ImageView::class.java)
        Mockito.doReturn(factImage).`when`(itemView).findViewById<ImageView>(R.id.fact_image)

        val holder = FactViewHolder(itemView)
        val fact = Fact().also { fact ->
            fact.description = "Test Desc"
        }
        presenter.loadFactData(holder, fact)
        Mockito.verify(descView).visibility = View.VISIBLE
        Mockito.verify(descView).text = "Test Desc"
    }

    fun test_loadFactData_hidesImage_whenUrlIsEmpty() {
        val itemView = Mockito.mock(View::class.java)
        val titleView = Mockito.mock(TextView::class.java)
        Mockito.doReturn(titleView).`when`(itemView).findViewById<TextView>(R.id.fact_title)

        val descView = Mockito.mock(TextView::class.java)
        Mockito.doReturn(descView).`when`(itemView).findViewById<TextView>(R.id.description)

        val factImage = Mockito.mock(ImageView::class.java)
        Mockito.doReturn(factImage).`when`(itemView).findViewById<ImageView>(R.id.fact_image)

        val holder = FactViewHolder(itemView)
        val fact = Fact()
        presenter.loadFactData(holder, fact)
        Mockito.verify(factImage).visibility = View.GONE
    }
}