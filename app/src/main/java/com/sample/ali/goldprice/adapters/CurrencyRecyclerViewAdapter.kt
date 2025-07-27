package com.sample.ali.goldprice.adapters

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.sample.ali.goldprice.R
import com.sample.ali.goldprice.databinding.RecyclerItemCurrencyBinding
import com.sample.ali.goldprice.remote.model.GoldAndCurrencyModel

class CurrencyRecyclerViewAdapter(private var items: ArrayList<GoldAndCurrencyModel>) :
    Adapter<CurrencyRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RecyclerItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val icons = arrayOf(
            R.drawable.ic_tether,
            R.drawable.ic_dollar,
            R.drawable.ic_euro,
            R.drawable.ic_derham,
            R.drawable.ic_pond,
            R.drawable.ic_japan,
            R.drawable.ic_kuwait,
            R.drawable.ic_austrila,
            R.drawable.ic_canada,
            R.drawable.ic_china,
            R.drawable.ic_turkey,
            R.drawable.ic_saudi,
            R.drawable.ic_switzerland,
            R.drawable.ic_india,
            R.drawable.ic_pakistan,
            R.drawable.ic_iraq,
            R.drawable.ic_syria,
            R.drawable.ic_sweden,
            R.drawable.ic_qatar,
            R.drawable.ic_oman,
            R.drawable.ic_bahrain,
            R.drawable.ic_afghanistan,
            R.drawable.ic_malaysia,
            R.drawable.ic_thailand,
            R.drawable.ic_russia,
            R.drawable.ic_azerbaijan,
            R.drawable.ic_armenia,
            R.drawable.ic_georgia
        )

        fun setRecyclerData(data: GoldAndCurrencyModel, position: Int) {
            binding.contentTitle.text = data.name
            binding.contentPrice.text = DecimalFormat("#,###").format(data.price)
            if (position in icons.indices)
                binding.contentIcon.setImageResource(icons[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemCurrencyBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setRecyclerData(items[position], position)
    }

}
