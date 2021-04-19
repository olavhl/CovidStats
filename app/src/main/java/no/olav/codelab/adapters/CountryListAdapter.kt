package no.olav.codelab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import no.olav.codelab.databinding.ItemCountryViewBinding
import no.olav.codelab.models.CountryStats

class CountryListAdapter (private var list: List<CountryStats>) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.CountryViewHolder {

        return CountryViewHolder(ItemCountryViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CountryListAdapter.CountryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class CountryViewHolder(private val binding: ItemCountryViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(stats: CountryStats) {
            Picasso.get().load("https://www.countryflags.io/${stats.CountryCode}/shiny/64.png").into(binding.imageViewFlag)
            binding.textViewCountryCases.text = "${stats.TotalConfirmed}"
            binding.textViewCountry.text  = stats.Country
        }
    }

    fun update (newList: List<CountryStats>) {
        list = newList
        notifyDataSetChanged()
    }

}