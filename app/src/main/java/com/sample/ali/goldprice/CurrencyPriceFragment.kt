package com.sample.ali.goldprice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.ali.goldprice.adapters.CurrencyRecyclerViewAdapter
import com.sample.ali.goldprice.databinding.FragmentCurrencyPriceBinding
import com.sample.ali.goldprice.remote.ApiRepository
import com.sample.ali.goldprice.remote.priceapi.GoldAndCurrencyContent
import com.sample.ali.goldprice.remote.priceapi.PriceApiRespond
import com.sample.ali.goldprice.remote.priceapi.PriceModel

class CurrencyPriceFragment : Fragment() {
    private lateinit var binding: FragmentCurrencyPriceBinding
    private var currenciesPrice = ArrayList<GoldAndCurrencyContent>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiRepository.instance.getPrices(
            object : PriceApiRespond {
                override fun onApiRespond(respond: PriceModel) {
                    currenciesPrice.addAll(respond.data.currencies)
                    activity?.runOnUiThread {
                        val recyclerView = binding.recyclerCurrencyPrice
                        recyclerView.layoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        recyclerView.adapter = CurrencyRecyclerViewAdapter(currenciesPrice)
                        binding.progress.visibility = View.GONE
                    }
                }

                override fun onApiRespondFailure(message: String) {
                    Log.e("API", message)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCurrencyPriceBinding.inflate(inflater)
        return binding.root
    }

}