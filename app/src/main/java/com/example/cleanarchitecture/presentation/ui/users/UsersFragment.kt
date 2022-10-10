package com.example.cleanarchitecture.presentation.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentUsersBinding
import com.example.cleanarchitecture.presentation.extension.addPaginationScrollListener
import com.example.cleanarchitecture.presentation.extension.addVerticalSpaceDecoration
import com.example.cleanarchitecture.presentation.model.PagingData
import com.example.cleanarchitecture.presentation.ui.users.adapter.UserAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by inject<UsersViewModel>()

    private val adapter by lazy {
        UserAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUsersBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            recyclerView.addVerticalSpaceDecoration(R.dimen.list_space)

            swipeRefresh.setOnRefreshListener {
                viewModel.onRefreshed()
            }

            recyclerView.addPaginationScrollListener(layoutManager, RECYCLER_ITEM_TO_LOAD) {
                viewModel.onLoadMore()
            }

            viewModel
                .dataFlow
                .onEach { swipeRefresh.isRefreshing = false }
                .onEach { users ->
                    adapter.submitList(
                        users.map { PagingData.Content(it) } + PagingData.Loading
                    )
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val RECYCLER_ITEM_TO_LOAD = 15
    }
}