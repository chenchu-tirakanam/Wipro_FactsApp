package com.wipro.factsapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Activity to show facts list
 */
class FactsListActivity : AppCompatActivity(), FactsListView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var factsList:RecyclerView
    private lateinit var errorText:TextView

    private lateinit var presenter: FactsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts_list)

        swipeLayout = findViewById(R.id.swipe_layout)
        factsList = findViewById(R.id.facts_list)
        errorText = findViewById(R.id.error_message)

        presenter = FactsListPresenterImpl(this)
        swipeLayout.setOnRefreshListener(this)

        swipeLayout.isRefreshing = true
        presenter.loadFacts(this)
    }

    override fun onRefresh() {
        presenter.loadFacts(this)
    }

    override fun showError(error: Int) {
        errorText.setText(error)
        errorText.visibility = View.VISIBLE
        factsList.visibility = View.GONE
        swipeLayout.isRefreshing = false
    }
}