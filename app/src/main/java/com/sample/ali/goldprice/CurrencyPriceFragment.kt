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
import com.sample.ali.goldprice.databinding.FragmentCurrencyPriceBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CurrencyPriceFragment : Fragment() {
    private lateinit var binding: FragmentCurrencyPriceBinding
//    private val recyclerListItems = ArrayList<GoldAndCurrencyContent>()
//    private lateinit var adapter: CurrencyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*ApiRepository.instance.getPrices(
            object : PriceApiRespond {
                override fun onApiRespond(respond: PriceModel) {
                    val startPosition = recyclerListItems.size
                    activity?.runOnUiThread {
                        recyclerListItems.addAll(respond.data.currencies)
                        adapter.notifyItemRangeInserted(startPosition, respond.data.currencies.size)
                        binding.progress.visibility = View.GONE
                    }
                }

                override fun onApiRespondFailure(message: String) {
                    Log.e("API", "no respond from api")
                }
            }
        )*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCurrencyPriceBinding.inflate(inflater)
//        adapter = CurrencyRecyclerViewAdapter(recyclerListItems)
        val recyclerView = binding.recyclerCurrencyPrice
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//        recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val swipeRefresh = binding.swipeRefresh
        swipeRefresh.setColorSchemeResources(R.color.gold_text, R.color.splash_gold)
        swipeRefresh.setProgressBackgroundColorSchemeResource(R.color.back_view_black)
        swipeRefresh.setOnRefreshListener {
            /*ApiRepository.instance.getPrices(
                object : PriceApiRespond {
                    override fun onApiRespond(respond: PriceModel) {
                        adapter.makeMutableData(recyclerListItems)
                        adapter.setUpdatedData(respond.data.currencies)
                        Log.i("TEST", respond.data.currencies.toString())
                    }

                    override fun onApiRespondFailure(message: String) {
                        Log.e("API", message)
                    }
                }
            )*/

            viewLifecycleOwner.lifecycleScope.launch {
                delay(850)
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

}
