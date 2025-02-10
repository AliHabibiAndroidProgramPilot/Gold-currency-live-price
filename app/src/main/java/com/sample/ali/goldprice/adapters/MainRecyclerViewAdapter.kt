package com.sample.ali.goldprice.adapters

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.sample.ali.goldprice.databinding.RecyclerItemBinding
import com.sample.ali.goldprice.remote.priceapi.GoldAndCurrencyContent

class MainRecyclerViewAdapter(private val items: ArrayList<GoldAndCurrencyContent>) :
    Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setRecyclerData(data: GoldAndCurrencyContent) {
            val price = data.price / 10
            binding.contentTitle.text = data.label
            binding.contentPrice.text = DecimalFormat("#,###").format(price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setRecyclerData(items[position])
    }

}