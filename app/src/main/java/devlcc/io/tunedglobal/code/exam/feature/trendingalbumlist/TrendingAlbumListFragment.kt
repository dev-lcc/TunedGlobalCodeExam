package devlcc.io.tunedglobal.code.exam.feature.trendingalbumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import devlcc.io.tunedglobal.code.exam.R
import devlcc.io.tunedglobal.code.exam.common.coroutines.throttleFirst
import devlcc.io.tunedglobal.code.exam.databinding.FragmentTrendingAlbumListBinding
import devlcc.io.tunedglobal.code.exam.feature.trendingalbumlist.adapter.EndlessRecyclerViewScrollListener
import devlcc.io.tunedglobal.code.exam.feature.trendingalbumlist.adapter.ItemTrendingAlbumAdapter
import devlcc.io.tunedglobal.code.exam.model.Album
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.flowbinding.android.view.clicks
import reactivecircus.flowbinding.swiperefreshlayout.refreshes
import timber.log.Timber

class TrendingAlbumListFragment : Fragment() {

    private val binding: FragmentTrendingAlbumListBinding by viewBinding(
        FragmentTrendingAlbumListBinding::bind
    )
    private val viewModel: TrendingAlbumListViewModel by viewModel()

    private var adapter: ItemTrendingAlbumAdapter? = null
    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_trending_album_list, container, false
    )

    override fun onDestroyView() {
        binding.rvAlbums.adapter = null
        adapter = null
        if (scrollListener != null) {
            binding.rvAlbums.removeOnScrollListener(scrollListener!!)
        }
        scrollListener = null

        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefresh.refreshes().throttleFirst(1000L).onEach {
            // Attempt Refresh
            viewModel.refresh()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter = ItemTrendingAlbumAdapter(onItemTap = { which: Album ->
            Timber.d("onItemTap() -> which = $which")
            // TODO:: On Tap
        })
        binding.rvAlbums.adapter = adapter

        // Setup Grid Layout Manager
        val layoutManager = GridLayoutManager(requireContext(), 2/*, RecyclerView.VERTICAL, false*/)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter?.getItemViewType(position)) {
                    ItemTrendingAlbumAdapter.VIEW_TYPE_LOAD_MORE -> 2
                    else -> 1
                }
            }

        }
        binding.rvAlbums.layoutManager = layoutManager

        // Set Scroll Bottom Fetch More Behavior
        val fabVisibilityAnimatorFlow = MutableSharedFlow<Boolean>(extraBufferCapacity = 1)
        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                Timber.d("scrollListener::onLoadMore() -> page/totalItemsCount = $page / $totalItemsCount")
                // Attempt fetch more
                viewModel.fetchMore()
            }

            override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(view, dx, dy)

                fabVisibilityAnimatorFlow.tryEmit(layoutManager.findFirstVisibleItemPosition() >= 20)
            }
        }
        //
        // Animate FAB Visibility
        //
        fabVisibilityAnimatorFlow.throttleFirst(1000L).onEach { isVisible ->
            if (isVisible) {
                binding.fabScrollToTop.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f)
                    .setDuration(350L).setUpdateListener {
                        binding.fabScrollToTop.visibility = View.VISIBLE
                    }
            } else {
                binding.fabScrollToTop.animate().alpha(0.0f).scaleX(0f).scaleY(0f).setDuration(350L)
                    .setUpdateListener {
                        if (it.isStarted && !it.isRunning) {
                            binding.fabScrollToTop.visibility = View.GONE
                        }
                    }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.rvAlbums.addOnScrollListener(scrollListener!!)

        // Tap force scroll to top
        binding.fabScrollToTop.clicks().throttleFirst(1000L).onEach {
            binding.rvAlbums.smoothScrollToPosition(0)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        //////////////////////////
        // Render UI State
        //////////////////////////
        viewModel.uiState.onEach(::render).launchIn(viewLifecycleOwner.lifecycleScope)

        //////////////////////////
        // Launch UI Effect(s)
        //////////////////////////
        viewModel.uiEffect.onEach(::launchEffect).launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun render(state: TrendingAlbumListState) {
        Timber.d("render() -> state = $state")

        when (state) {
            is TrendingAlbumListState.Loading -> {
                if (state.refresh) {
                    binding.swipeRefresh.isRefreshing = true
                } else {
                    adapter?.setLoading(isLoading = true)
                }
            }
            is TrendingAlbumListState.Success -> {
                binding.swipeRefresh.isRefreshing = false
                adapter?.setLoading(isLoading = false)

                // Update Album List
                adapter?.replace(state.albums)

                binding.containerEmptyState.visibility = View.GONE
                binding.rvAlbums.visibility = View.VISIBLE
            }
            is TrendingAlbumListState.Empty -> {
                binding.swipeRefresh.isRefreshing = false
                adapter?.setLoading(isLoading = false)

                binding.rvAlbums.visibility = View.GONE
                binding.fabScrollToTop.visibility = View.GONE
                binding.containerEmptyState.visibility = View.VISIBLE
            }
        }
    }

    private fun launchEffect(effect: TrendingAlbumListEffect) {
        Timber.d("launchEffect() -> effect = $effect")

        when (effect) {
            is TrendingAlbumListEffect.ErrorFetchTrendingAlbums -> {
                Toast.makeText(
                    requireContext(), R.string.something_went_wrong_label, Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}