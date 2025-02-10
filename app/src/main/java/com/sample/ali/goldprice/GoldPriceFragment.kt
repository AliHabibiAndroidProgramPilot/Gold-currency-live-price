package com.sample.ali.goldprice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.ali.goldprice.adapters.GoldRecyclerViewAdapter
import com.sample.ali.goldprice.databinding.FragmentGoldPriceBinding
import com.sample.ali.goldprice.remote.ApiRepository
import com.sample.ali.goldprice.remote.priceapi.GoldAndCurrencyContent
import com.sample.ali.goldprice.remote.priceapi.PriceApiRespond
import com.sample.ali.goldprice.remote.priceapi.PriceModel

class GoldPriceFragment : Fragment() {
    private lateinit var binding: FragmentGoldPriceBinding
    private var goldsPrice = ArrayList<GoldAndCurrencyContent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiRepository.instance.getPrices(
            object : PriceApiRespond {
                override fun onApiRespond(respond: PriceModel) {
                    goldsPrice.addAll(respond.data.golds)
                    activity?.runOnUiThread {
                        val recyclerView = binding.recyclerGoldPrice
                        recyclerView.layoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        recyclerView.adapter = GoldRecyclerViewAdapter(goldsPrice)
                        binding.progress.visibility = View.GONE
                    }
                }

                override fun onApiRespondFailure(message: String) {
                    Log.i("API", message)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGoldPriceBinding.inflate(inflater)
        return binding.root
    }

}