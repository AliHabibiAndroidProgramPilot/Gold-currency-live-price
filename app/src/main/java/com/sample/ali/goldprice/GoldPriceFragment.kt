package com.sample.ali.goldprice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.ali.goldprice.adapters.GoldRecyclerViewAdapter
import com.sample.ali.goldprice.databinding.FragmentGoldPriceBinding
import com.sample.ali.goldprice.remote.ApiRepository
import com.sample.ali.goldprice.remote.priceapi.GoldAndCurrencyContent
import com.sample.ali.goldprice.remote.priceapi.PriceApiRespond
import com.sample.ali.goldprice.remote.priceapi.PriceModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GoldPriceFragment : Fragment() {
    private lateinit var binding: FragmentGoldPriceBinding
    private var recyclerListItems = ArrayList<GoldAndCurrencyContent>()
    private lateinit var adapter: GoldRecyclerViewAdapter
    private lateinit var fragmentLifecycleCallbacks: FragmentLifecycleCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiRepository.instance.getPrices(
            object : PriceApiRespond {
                override fun onApiRespond(respond: PriceModel) {
                    val startPosition = recyclerListItems.size
                    activity?.runOnUiThread {
                        recyclerListItems.addAll(respond.data.golds)
                        adapter.notifyItemRangeInserted(startPosition, respond.data.golds.size)
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
        adapter = GoldRecyclerViewAdapter(recyclerListItems)
        val recyclerView = binding.recyclerGoldPrice
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val swipeRefresh = binding.swipeRefresh
        swipeRefresh.setColorSchemeResources(R.color.gold_text, R.color.splash_gold)
        swipeRefresh.setProgressBackgroundColorSchemeResource(R.color.back_view_black)
        swipeRefresh.setOnRefreshListener {
            ApiRepository.instance.getPrices(
                object : PriceApiRespond {
                    override fun onApiRespond(respond: PriceModel) {
                        adapter.makeMutableData(recyclerListItems)
                        adapter.setNewData(respond.data.golds)
                        Log.i("TEST", respond.data.golds.toString())
                    }

                    override fun onApiRespondFailure(message: String) {
                        Log.e("API", message)
                    }

                }
            )
            viewLifecycleOwner.lifecycleScope.launch {
                delay(850)
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

    override fun onStart() {
        super.onStart()
        fragmentLifecycleCallbacks = object : FragmentLifecycleCallbacks() {
            override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
                super.onFragmentDetached(fm, f)
                ApiRepository.instance.getPrices(
                    object : PriceApiRespond {
                        override fun onApiRespond(respond: PriceModel) {
                            adapter.makeMutableData(recyclerListItems)
                            adapter.setNewData(respond.data.golds)
                            binding.progress.visibility = View.GONE
                            Log.i("TEST", respond.data.golds.toString())
                        }

                        override fun onApiRespondFailure(message: String) {
                            Log.e("API", message)
                        }
                    }
                )
            }
        }
        parentFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        parentFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
    }

}