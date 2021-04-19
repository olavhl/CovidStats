package no.olav.codelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import no.olav.codelab.adapters.CountryListAdapter
import no.olav.codelab.databinding.ActivityMainBinding
import no.olav.codelab.datasources.DummySource
import no.olav.codelab.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var listAdapter : CountryListAdapter
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listAdapter = CountryListAdapter(ArrayList())

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = listAdapter

        viewModel.liveStats.observe(this, { list ->
            listAdapter.update(list)
        })

        viewModel.isLoading.observe(this, { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }

}