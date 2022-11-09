package devlcc.io.tunedglobal.code.exam.feature.albumdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import devlcc.io.tunedglobal.code.exam.R
import devlcc.io.tunedglobal.code.exam.common.coroutines.throttleFirst
import devlcc.io.tunedglobal.code.exam.databinding.FragmentAlbumDetailsBinding
import devlcc.io.tunedglobal.code.exam.feature.albumdetails.adapter.ItemAlbumDetailArtistAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import reactivecircus.flowbinding.appcompat.navigationClicks
import reactivecircus.flowbinding.swiperefreshlayout.refreshes
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class AlbumDetailsFragment : Fragment() {

    private val binding: FragmentAlbumDetailsBinding by viewBinding(FragmentAlbumDetailsBinding::bind)
    private val viewModel: AlbumDetailsViewModel by viewModel {
        val albumId = arguments?.getLong(ARG_INPUT_ALBUM_ID, -1L)
        parametersOf(albumId)
    }

    private var appBarChangeListener: AppBarStateChangedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_album_details, container, false
    )

    override fun onDestroyView() {
        if (appBarChangeListener != null) {
            binding.appBarLayout.removeOnOffsetChangedListener(appBarChangeListener!!)
        }
        appBarChangeListener = null

        binding.rvArtists.adapter = null

        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Home-up
        binding.toolbar.navigationClicks().throttleFirst(1000).onEach {
            findNavController().popBackStack()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        // Observe App Bar State Changes
        appBarChangeListener = getAppBarOffsetChangeListener()
        binding.appBarLayout.addOnOffsetChangedListener(appBarChangeListener!!)

        // Swipe Refresh
        binding.swipeRefresh.refreshes().throttleFirst(1000L).onEach {
            // Attempt Refresh
            viewModel.refresh()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        // Setup Artists List
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvArtists.layoutManager = layoutManager

        //////////////////////////
        // Render UI State
        //////////////////////////
        viewModel.uiState.onEach(::render).launchIn(viewLifecycleOwner.lifecycleScope)

        //////////////////////////
        // Launch UI Effect(s)
        //////////////////////////
        viewModel.uiEffect.onEach(::launchEffect).launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun render(state: AlbumDetailsState) {
        Timber.d("render() -> state = $state")

        when (state) {
            is AlbumDetailsState.Loading -> {
                binding.swipeRefresh.isRefreshing = true
            }
            is AlbumDetailsState.Success -> {
                binding.swipeRefresh.isRefreshing = false

                // Album Title
                val albumTitle = "\"${state.appBarTitle}\""
                binding.actvAlbumTitle.text = albumTitle

                // Banner Image
                when (state.banner) {
                    is AlbumBanner.Image -> {
                        Glide.with(requireContext()).asBitmap().load(state.banner.url)
                            .into(binding.acivBannerImage)
                    }
                    is AlbumBanner.Placeholder -> {
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_library_music)
                            ?.let { placeholderBanner ->
                                DrawableCompat.setTint(
                                    placeholderBanner,
                                    ContextCompat.getColor(requireContext(), R.color.gray)
                                )

                                binding.acivBannerImage.setImageDrawable(placeholderBanner)
                            }
                    }
                }

                // Album Attributes
                binding.chipGroupAttributes.visibility =
                    if (state.attributes.isNotEmpty()) View.VISIBLE else View.GONE
                (state.attributes.firstOrNull { it is AlbumAttributes.SongCount } as? AlbumAttributes.SongCount)?.let { songCountAttrib ->
                    val count = songCountAttrib.count
                    val label = "$count ${
                        resources.getQuantityText(
                            R.plurals.album_details_songs, songCountAttrib.count
                        )
                    }"
                    binding.chipSongCount.text = label

                    binding.chipSongCount.visibility = View.VISIBLE
                } ?: run {
                    binding.chipSongCount.visibility = View.GONE
                }

                state.attributes.firstOrNull { it is AlbumAttributes.Downloadable }?.let {
                    binding.chipDownloadable.visibility = View.VISIBLE
                } ?: run {
                    binding.chipDownloadable.visibility = View.GONE
                }

                state.attributes.firstOrNull { it is AlbumAttributes.Streamable }?.let {
                    binding.chipStreamable.visibility = View.VISIBLE
                } ?: run {
                    binding.chipStreamable.visibility = View.GONE
                }

                // Record Label
                binding.actvRecordLabel.text = state.recordLabel

                // Copyright
                binding.actvCopyright.text = state.copyright

                // Artists
                binding.actvArtistsTitle.visibility =
                    if (state.artists.isNotEmpty()) View.VISIBLE else View.GONE
                binding.rvArtists.visibility =
                    if (state.artists.isNotEmpty()) View.VISIBLE else View.GONE
                binding.rvArtists.adapter = ItemAlbumDetailArtistAdapter(items = state.artists)

                // Release Date
                @Suppress("DEPRECATION") val locale = resources.configuration.locale
                @Suppress("DEPRECATION") val releaseDateValue = formatReleaseDate(
                    locale = resources.configuration.locale, date = parseIsoDate(
                        locale = locale, dateStr = state.releaseDate
                    )
                )
                binding.actvReleaseDateValue.text = releaseDateValue
                binding.actvReleaseDateValue.visibility =
                    if (releaseDateValue != null) View.VISIBLE else View.GONE

                binding.containerEmptyState.visibility = View.GONE
                binding.layoutContainer.visibility = View.VISIBLE

            }
            is AlbumDetailsState.Error -> {
                binding.swipeRefresh.isRefreshing = false

                binding.layoutContainer.visibility = View.GONE
                binding.containerEmptyState.visibility = View.VISIBLE
            }
        }
    }

    private fun launchEffect(effect: AlbumDetailsEffect) {
        Timber.d("launchEffect() -> effect = $effect")
    }

    /**
     * Helper function to provide Expand/Collapse TabLayout Text Appearance Behaviour
     */
    private fun getAppBarOffsetChangeListener(): AppBarStateChangedListener =
        object : AppBarStateChangedListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                Timber.d("binding.appBarLayout.addOnOffsetChangedListener() -> state = ${state.name}")
                when (state) {
                    State.EXPANDED -> {
                        binding.toolbar.title = ""
                    }
                    State.COLLAPSED -> {
                        binding.toolbar.title = getString(R.string.album_details_appbar_title)
                    }
                    State.IDLE -> {}
                }
            }
        }

    companion object {
        const val ARG_INPUT_ALBUM_ID = "arg-input-album-id"

        private fun parseIsoDate(locale: Locale, dateStr: String?): Date? {
            val yyyyMMddPart = dateStr?.split("T")?.firstOrNull() ?: return null
            val dateFormatter = SimpleDateFormat("yyyy-MM-dd", locale)

            return try {
                dateFormatter.parse(yyyyMMddPart)
            } catch (err: Throwable) {
                err.printStackTrace()
                null
            }
        }

        private fun formatReleaseDate(locale: Locale, date: Date?): String? {
            date ?: return null
            val dateFormatter = SimpleDateFormat("MMM dd, yyyy", locale)
            return dateFormatter.format(date)
        }

        fun createInputArgument(albumId: Long): Bundle = bundleOf(ARG_INPUT_ALBUM_ID to albumId)

    }

}