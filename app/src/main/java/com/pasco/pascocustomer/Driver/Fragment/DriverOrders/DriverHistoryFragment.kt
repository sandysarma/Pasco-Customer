package com.pasco.pascocustomer.Driver.Fragment.DriverOrders

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.johncodeos.customprogressdialogexample.CustomProgressDialog
import com.pasco.pascocustomer.Driver.Fragment.DriverOrders.ViewModel.DAllOrderResponse
import com.pasco.pascocustomer.Driver.Fragment.DriverOrders.ViewModel.DAllOrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.pasco.pascocustomer.R
import com.pasco.pascocustomer.activity.Driver.adapter.DriverAllBiddsAdapter
import com.pasco.pascocustomer.Driver.adapter.DriverHistoryAdapter
import com.pasco.pascocustomer.databinding.FragmentDriverHistoryBinding
import com.pasco.pascocustomer.utils.ErrorUtil

@AndroidEntryPoint
class DriverHistoryFragment : Fragment() {
    private lateinit var binding:FragmentDriverHistoryBinding
    private lateinit var activity: Activity
    private var driverHistory:List<DAllOrderResponse.DAllOrderResponseData> = ArrayList()
    private val dAllOrdersViewModel: DAllOrdersViewModel by viewModels()
    private val progressDialog by lazy { CustomProgressDialog(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDriverHistoryBinding.inflate(inflater, container, false)
        activity = requireActivity()
        allOrdersApi()
        allBiddsObserver()
        binding.allBiddsTextIdD.setOnClickListener {
            binding.allBiddsTextIdD.background = ContextCompat.getDrawable(requireActivity(), R.drawable.order_bidding_yellow)
            binding.currentOrderTextIdD.background = null
            binding.allBiddsTextIdD.setTextColor(Color.parseColor("#FFFFFFFF"))
            binding.currentOrderTextIdD.setTextColor(Color.parseColor("#FF000000"))
            allOrdersApi()
            allBiddsObserver()

        }
        binding.currentOrderTextIdD.setOnClickListener {
            binding.allBiddsTextIdD.background = null
            binding.currentOrderTextIdD.background = ContextCompat.getDrawable(requireActivity(), R.drawable.accept_bidd_background)
            binding.allBiddsTextIdD.setTextColor(Color.parseColor("#FF000000"))
            binding.currentOrderTextIdD.setTextColor(Color.parseColor("#FFFFFFFF"))
            allOrdersApi()
            allOrderObserver()
        }
        return binding.root
    }
    private fun allBiddsObserver() {
        dAllOrdersViewModel.progressIndicator.observe(requireActivity(), Observer {
            // Handle progress indicator changes if needed
        })

        dAllOrdersViewModel.mAllOrderResponse.observe(requireActivity()) { response ->
            val message = response.peekContent().msg!!
            driverHistory = response.peekContent().data ?: emptyList()

            if (response.peekContent().status == "False") {
                binding.recycerHistoryList.isVerticalScrollBarEnabled = true
                binding.recycerHistoryList.isVerticalFadingEdgeEnabled = true
                binding.recycerHistoryList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recycerHistoryList.adapter = DriverAllBiddsAdapter(requireContext(), driverHistory)
                Toast.makeText(requireActivity(), "$message", Toast.LENGTH_LONG).show()
            } else {
                binding.recycerHistoryList.isVerticalScrollBarEnabled = true
                binding.recycerHistoryList.isVerticalFadingEdgeEnabled = true
                binding.recycerHistoryList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recycerHistoryList.adapter = DriverAllBiddsAdapter(requireContext(), driverHistory)
                // Toast.makeText(this@BiddingDetailsActivity, message, Toast.LENGTH_SHORT).show()

            }
        }

        dAllOrdersViewModel.errorResponse.observe(requireActivity()) {
            ErrorUtil.handlerGeneralError(requireActivity(), it)
        }
    }

    private fun allOrderObserver() {
        dAllOrdersViewModel.progressIndicator.observe(requireActivity(), Observer {
            // Handle progress indicator changes if needed
        })

        dAllOrdersViewModel.mAllOrderResponse.observe(requireActivity()) { response ->
            val message = response.peekContent().msg!!
            driverHistory = response.peekContent().data ?: emptyList()

            if (response.peekContent().status == "False") {
                binding.recycerHistoryList.isVerticalScrollBarEnabled = true
                binding.recycerHistoryList.isVerticalFadingEdgeEnabled = true
                binding.recycerHistoryList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recycerHistoryList.adapter = DriverHistoryAdapter(requireContext(), driverHistory)
                Toast.makeText(requireActivity(), "$message", Toast.LENGTH_LONG).show()
            } else {
                binding.recycerHistoryList.isVerticalScrollBarEnabled = true
                binding.recycerHistoryList.isVerticalFadingEdgeEnabled = true
                binding.recycerHistoryList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recycerHistoryList.adapter = DriverHistoryAdapter(requireContext(), driverHistory)
                // Toast.makeText(this@BiddingDetailsActivity, message, Toast.LENGTH_SHORT).show()

            }
        }

        dAllOrdersViewModel.errorResponse.observe(requireActivity()) {
            ErrorUtil.handlerGeneralError(requireActivity(), it)
        }
    }

    private fun allOrdersApi() {
        dAllOrdersViewModel.getAllOrdersData(
            progressDialog,
            activity
        )
    }

}