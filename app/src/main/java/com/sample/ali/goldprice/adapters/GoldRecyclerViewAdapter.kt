package com.sample.ali.goldprice.adapters

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.sample.ali.goldprice.R
import com.sample.ali.goldprice.databinding.RecyclerItemGoldBinding
import com.sample.ali.goldprice.remote.priceapi.GoldAndCurrencyContent

class GoldRecyclerViewAdapter(private var items: ArrayList<GoldAndCurrencyContent>) :
    Adapter<GoldRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RecyclerItemGoldBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val icons = arrayOf(
            R.drawable.ic_18,
            R.drawable.ic_24,
            R.drawable.full_coin_ic,
            R.drawable.half_coin_ic,
            R.drawable.quarter_coin_ic
        )

        fun setRecyclerData(data: GoldAndCurrencyContent, position: Int) {
            val price = data.price / 10
            binding.contentTitle.text = data.label
            binding.contentPrice.text = DecimalFormat("#,###").format(price)
            when (position) {
                0 -> binding.contentIcon.setImageResource(icons[position])
                1 -> binding.contentIcon.setImageResource(icons[position])
                2 -> binding.contentIcon.setImageResource(icons[position])
                3 -> binding.contentIcon.setImageResource(icons[position])
                4 -> binding.contentIcon.setImageResource(icons[position])
            }
        }
    }

    fun makeMutableData(data: ArrayList<GoldAndCurrencyContent>) {
        this.items = data.toMutableList() as ArrayList<GoldAndCurrencyContent>
    }

    fun setNewData(newList: ArrayList<GoldAndCurrencyContent>) {
        val diffUtilCallback = DiffUtilCallback(items, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
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