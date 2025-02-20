package com.sample.ali.goldprice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.ali.goldprice.adapters.CurrencyRecyclerViewAdapter
import com.sample.ali.goldprice.databinding.FragmentCurrencyPriceBinding
import com.sample.ali.goldprice.remote.ApiRepository
import com.sample.ali.goldprice.remote.priceapi.GoldAndCurrencyContent
import com.sample.ali.goldprice.remote.priceapi.PriceApiRespond
import com.sample.ali.goldprice.remote.priceapi.PriceModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CurrencyPriceFragment : Fragment() {
    private lateinit var binding: FragmentCurrencyPriceBinding
    private val recyclerListItems = ArrayList<GoldAndCurrencyContent>()
    private lateinit var adapter: CurrencyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiRepository.instance.getPrices(
            object : PriceApiRespond {
                override fun onApiRespond(respond: PriceModel) {
                    activity?.runOnUiThread {
                        val startPosition = recyclerListItems.size
                        recyclerListItems.addAll(respond.data.currencies)
                        adapter.notifyItemRangeInserted(startPosition, respond.data.currencies.size)
                        binding.progress.visibility = View.INVISIBLE
                    }
                }

                override fun onApiRespondFailure(message: String) {
                    Log.e("API", "no respond from api")
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
        adapter = CurrencyRecyclerViewAdapter(recyclerListItems)
        val recyclerView = binding.recyclerCurrencyPrice
        recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        binding.progress.visibility = View.INVISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefresh.setColorSchemeResources(R.color.gold_text, R.color.splash_gold)
        binding.swipeRefresh.setProgressBackgroundColorSchemeResource(R.color.splash_gold_low_opacity)
        binding.swipeRefresh.setOnRefreshListener {
            ApiRepository.instance.getPrices(
                object : PriceApiRespond {
                    override fun onApiRespond(respond: PriceModel) {
                        adapter.setUpdatedData(respond.data.currencies)
                        Log.i("TEST", respond.data.currencies.toString())
                    }

                    override fun onApiRespondFailure(message: String) {
                        Log.e("API", "no respond from api")
                    }
                }
            )
            viewLifecycleOwner.lifecycleScope.launch {
                delay(850)
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

}