package com.sample.ali.goldprice

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
import com.sample.ali.goldprice.mvp.model.ModelGoldPriceFragment
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
        val model = ModelGoldPriceFragment()
        presenter = PresenterGoldPriceFragment(model)
        presenter.attachView(this)
        presenter.viewCaller(requireContext())
        presenter.fetchGoldPrices()
    }

    override fun setupRecyclerView(data: PriceApiModel) {
        binding.includedMessageBox.root.visibility = View.GONE
        adapter = GoldRecyclerViewAdapter(data.gold)
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

    override fun showErrorFetchingGoldPriceMessage(errorCode: Int) {
        binding.includedMessageBox.txtErrorCode.text = errorCode.toString()
        binding.includedMessageBox.root.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.detachView()
    }

}