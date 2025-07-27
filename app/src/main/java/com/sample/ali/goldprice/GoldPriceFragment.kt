package com.sample.ali.goldprice

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.ali.goldprice.adapters.GoldRecyclerViewAdapter
import com.sample.ali.goldprice.databinding.FragmentGoldPriceBinding
import com.sample.ali.goldprice.mvp.ext.GoldPriceFragmentContract
import com.sample.ali.goldprice.mvp.presenter.PresenterGoldPriceFragment
import com.sample.ali.goldprice.remote.model.PriceApiModel

class GoldPriceFragment : Fragment(), GoldPriceFragmentContract.View {

    private var _binding: FragmentGoldPriceBinding? = null

    private val binding get() = _binding!!

    private lateinit var presenter: PresenterGoldPriceFragment

    private lateinit var adapter: GoldRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoldPriceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = PresenterGoldPriceFragment()
        presenter.attachView(this)
        presenter.viewCaller(requireContext())
    }

    override fun setupRecyclerView() {
        @Suppress("DEPRECATION") val data =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("DATA", PriceApiModel::class.java)
            } else {
                arguments?.getParcelable("DATA")
            }
        adapter = GoldRecyclerViewAdapter(data?.gold ?: arrayListOf())
        binding.recyclerGoldPrice.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerGoldPrice.adapter = adapter
    }

    override fun setupSwipeRefresh() {
        binding.swipeRefresh.apply {
            setColorSchemeResources(R.color.gold_text, R.color.splash_gold)
            setProgressBackgroundColorSchemeResource(R.color.back_view_black)
        }
    }

    override fun onDestroyView() {
        _binding = null
        presenter.detachView()
        super.onDestroyView()
    }

}