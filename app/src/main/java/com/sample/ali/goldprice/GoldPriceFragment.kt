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
import com.sample.ali.goldprice.mvp.ext.GoldPriceFragmentContract
import com.sample.ali.goldprice.mvp.model.ModelGoldPriceFragment
import com.sample.ali.goldprice.mvp.presenter.PresenterGoldPriceFragment
import com.sample.ali.goldprice.remote.model.PriceApiModel

class GoldPriceFragment : Fragment(), GoldPriceFragmentContract.View {

    private var _binding: FragmentGoldPriceBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: PresenterGoldPriceFragment
    private lateinit var adapter: GoldRecyclerViewAdapter
//    private lateinit var fragmentLifecycleCallbacks: FragmentLifecycleCallbacks

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoldPriceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ModelGoldPriceFragment()
        presenter = PresenterGoldPriceFragment(model)
        presenter.attachView(this)
        presenter.viewCaller(requireContext())
        presenter.fetchGoldPrices("Freedh1PXvgCtNrcn374FDBUVintkvGa")
        /*val swipeRefresh = binding.swipeRefresh
        swipeRefresh.setColorSchemeResources(R.color.gold_text, R.color.splash_gold)
        swipeRefresh.setProgressBackgroundColorSchemeResource(R.color.back_view_black)*/
        /*swipeRefresh.setOnRefreshListener {
            *//*ApiRepository.instance.getPrices(
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
            )*//*
            viewLifecycleOwner.lifecycleScope.launch {
                delay(850)
                binding.swipeRefresh.isRefreshing = false
            }
        }*/
    }

    /*override fun onStart() {
        super.onStart()
        fragmentLifecycleCallbacks = object : FragmentLifecycleCallbacks() {
            override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
                super.onFragmentDetached(fm, f)
                *//*ApiRepository.instance.getPrices(
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
                )*//*
            }
        }
        parentFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, false)
    }*/

    /*override fun onDestroy() {
        super.onDestroy()
        parentFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
    }*/

    override fun setupRecyclerView(data: PriceApiModel) {
        adapter = GoldRecyclerViewAdapter(data.gold)
        binding.recyclerGoldPrice.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerGoldPrice.adapter = adapter
    }

    override fun errorFetchingGoldPrice(errorMessage: String) {
        Log.w("Api", errorMessage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.detachView()
    }

}