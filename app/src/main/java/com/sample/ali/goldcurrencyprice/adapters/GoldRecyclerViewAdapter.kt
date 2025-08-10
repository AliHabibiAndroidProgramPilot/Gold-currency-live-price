package info.alihabibi.goldcurrencyprice.adapters

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import info.alihabibi.goldcurrencyprice.R
import info.alihabibi.goldcurrencyprice.databinding.RecyclerItemGoldBinding
import info.alihabibi.goldcurrencyprice.remote.model.GoldAndCurrencyModel

class GoldRecyclerViewAdapter(private var items: ArrayList<GoldAndCurrencyModel>) :
    Adapter<GoldRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RecyclerItemGoldBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val icons = arrayOf(
            R.drawable.ic_18,
            R.drawable.ic_24,
            R.drawable.ic_molten_gold,
            R.drawable.ic_ounce,
            R.drawable.ic_kilogram_coin,
            R.drawable.quarter_coin_ic,
            R.drawable.half_coin_ic,
            R.drawable.ic_king_coin,
            R.drawable.full_coin_ic
        )

        fun setRecyclerData(data: GoldAndCurrencyModel, position: Int) {
            binding.contentTitle.text = data.name
            binding.contentPrice.text = DecimalFormat("#,###").format(data.price)
            if (position in icons.indices) {
                binding.contentIcon.setImageResource(icons[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemGoldBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setRecyclerData(items[position], position)
    }

}
