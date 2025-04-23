package com.sample.ali.goldprice.adapters

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.sample.ali.goldprice.R
import com.sample.ali.goldprice.databinding.RecyclerItemGoldBinding
import com.sample.ali.goldprice.remote.price.model.GoldAndCurrencyModel

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
            when (position) {
                0 -> binding.contentIcon.setImageResource(icons[position])
                1 -> binding.contentIcon.setImageResource(icons[position])
                2 -> binding.contentIcon.setImageResource(icons[position])
                3 -> binding.contentIcon.setImageResource(icons[position])
                4 -> binding.contentIcon.setImageResource(icons[position])
                5 -> binding.contentIcon.setImageResource(icons[position])
                6 -> binding.contentIcon.setImageResource(icons[position])
                7 -> binding.contentIcon.setImageResource(icons[position])
                8 -> binding.contentIcon.setImageResource(icons[position])
            }
        }
    }

    fun makeMutableData(data: ArrayList<GoldAndCurrencyModel>) {
        this.items = data.toMutableList() as ArrayList<GoldAndCurrencyModel>
    }

    fun setNewData(newList: ArrayList<GoldAndCurrencyModel>) {
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
