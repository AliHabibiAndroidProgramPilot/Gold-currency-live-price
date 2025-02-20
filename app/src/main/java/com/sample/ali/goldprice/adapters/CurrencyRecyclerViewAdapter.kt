package com.sample.ali.goldprice.adapters

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.sample.ali.goldprice.R
import com.sample.ali.goldprice.databinding.RecyclerItemCurrencyBinding
import com.sample.ali.goldprice.remote.priceapi.GoldAndCurrencyContent

class CurrencyRecyclerViewAdapter(private var items: ArrayList<GoldAndCurrencyContent>) :
    Adapter<CurrencyRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RecyclerItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val icons = arrayOf(
            R.drawable.ic_dollar,
            R.drawable.ic_uro,
            R.drawable.ic_derham,
            R.drawable.ic_pond
        )

        fun setRecyclerData(data: GoldAndCurrencyContent, position: Int) {
            val price = data.price / 10
            binding.contentTitle.text = data.label
            binding.contentPrice.text = DecimalFormat("#,###").format(price)
            val imageView = binding.contentIcon
            when (position) {
                0 -> imageView.setImageResource(icons[0])
                1 -> imageView.setImageResource(icons[1])
                2 -> imageView.setImageResource(icons[2])
                3 -> imageView.setImageResource(icons[3])
            }
        }
    }

    fun makeMutableData(data: ArrayList<GoldAndCurrencyContent>) {
        this.items = data.toMutableList() as ArrayList<GoldAndCurrencyContent>
    }

    fun setUpdatedData(newList: ArrayList<GoldAndCurrencyContent>) {
        val diffUtilCallback = DiffUtilCallback(items, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
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