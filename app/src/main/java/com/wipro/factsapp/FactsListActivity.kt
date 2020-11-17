package com.wipro.factsapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.wipro.factsapp.adapter.FactsAdapter
import com.wipro.factsapp.model.Fact

/**
 * Activity to show facts list
 */
class FactsListActivity : AppCompatActivity(), FactsListView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var factsListView:RecyclerView
    private lateinit var errorText:TextView

    private lateinit var adapter:FactsAdapter

    private lateinit var presenter: FactsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts_list)

        swipeLayout = findViewById(R.id.swipe_layout)
        factsListView = findViewById(R.id.facts_list)
        errorText = findViewById(R.id.error_message)

        factsListView.layoutManager = LinearLayoutManager(this)

        presenter = FactsListPresenterImpl(this)
        swipeLayout.setOnRefreshListener(this)

        adapter = FactsAdapter(presenter)
        factsListView.adapter = adapter

        swipeLayout.isRefreshing = true
        presenter.loadFacts(this)
    }

    override fun onRefresh() {
        presenter.loadFacts(this)
    }

    override fun showError(error: Int) {
        errorText.setText(error)
        errorText.visibility = View.VISIBLE
        factsListView.visibility = View.GONE
        swipeLayout.isRefreshing = false
    }

    override fun showErrorToast(error: Int) {
        swipeLayout.isRefreshing = false
        Snackbar.make(swipeLayout, error, Snackbar.LENGTH_SHORT).show()
    }

    override fun setActionbarTitle(title: String) {
        swipeLayout.isRefreshing = false
        setTitle(title)
    }

    override fun showList(factsList: List<Fact>) {
        errorText.visibility = View.GONE
        factsListView.visibility = View.VISIBLE
        swipeLayout.isRefreshing = false
        adapter.setFactsList(factsList)
    }
}