package com.sample.ali.goldprice.adapters

import androidx.recyclerview.widget.DiffUtil
import com.sample.ali.goldprice.remote.priceapi.GoldAndCurrencyContent

class DiffUtilCallback(
    private val oldList: ArrayList<GoldAndCurrencyContent>,
    private val newList: ArrayList<GoldAndCurrencyContent>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.javaClass == newItem.javaClass
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.hashCode() == newItem.hashCode()
    }

}