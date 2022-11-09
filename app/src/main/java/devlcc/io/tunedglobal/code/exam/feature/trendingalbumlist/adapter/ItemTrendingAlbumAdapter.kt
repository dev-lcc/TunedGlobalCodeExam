package devlcc.io.tunedglobal.code.exam.feature.trendingalbumlist.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import devlcc.io.tunedglobal.code.exam.R
import devlcc.io.tunedglobal.code.exam.databinding.ItemAlbumBinding
import devlcc.io.tunedglobal.code.exam.databinding.ItemAlbumLoadMoreBinding
import devlcc.io.tunedglobal.code.exam.model.Album
import java.util.*

typealias OnItemTap = ((Album) -> Unit)

class ItemTrendingAlbumAdapter(
    private val onItemTap: OnItemTap? = null,
) : RecyclerView.Adapter<ViewHolder>() {

    private var items: List<Album> = emptyList()
    private var loadingNextPage: Boolean = false

    override fun getItemCount(): Int = when (loadingNextPage) {
        true -> items.size + 1
        else -> items.size
    }

    override fun getItemViewType(position: Int): Int =
        when (loadingNextPage) {
            true -> {
                if (position < items.size) VIEW_TYPE_ITEM
                else VIEW_TYPE_LOAD_MORE
            }
            false -> VIEW_TYPE_ITEM
        }

    fun isLoading(): Boolean = loadingNextPage

    fun setLoading(isLoading: Boolean) {
        val prevState = loadingNextPage
        loadingNextPage = isLoading

        try {
            if (!prevState && isLoading) {
                notifyItemInserted(items.size)
            } else if (!isLoading) {
                notifyItemRemoved(items.size)
            }
        } catch (_: Throwable) {
        }
    }

    /**
     * Each time data is set, we update this variable so that if DiffUtil calculation returns
     * after repetitive updates, we can ignore the old calculation
     */
    private var dataVersion = 0

    @Suppress("DEPRECATION")
    @SuppressLint("StaticFieldLeak", "NotifyDataSetChanged")
    @MainThread
    fun replace(update: List<Album>) {
        dataVersion++

        if (items.isEmpty()) {
            items = update
            notifyDataSetChanged()
        } else if (update.isEmpty()) {
            val oldSize = items.size
            items = emptyList()
            notifyItemChanged(0, oldSize)
        } else {
            val startVersion = dataVersion
            val oldItems = ArrayList(items)

            object : AsyncTask<Void, Void, DiffUtil.DiffResult>() {
                @Deprecated("Deprecated in Java")
                override fun doInBackground(vararg voids: Void): DiffUtil.DiffResult {
                    return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                        override fun getOldListSize(): Int = oldItems.size
                        override fun getNewListSize(): Int = update.size

                        override fun areItemsTheSame(
                            oldItemPosition: Int,
                            newItemPosition: Int
                        ): Boolean {
                            val oldItem = oldItems[oldItemPosition]
                            val newItem = update[newItemPosition]
                            return oldItem.albumID == newItem.albumID
                        }

                        override fun areContentsTheSame(
                            oldItemPosition: Int,
                            newItemPosition: Int
                        ): Boolean {
                            val oldItem = oldItems[oldItemPosition]
                            val newItem = update[newItemPosition]
                            return oldItem == newItem
                        }
                    })
                }

                @Deprecated("Deprecated in Java")
                override fun onPostExecute(diffResult: DiffUtil.DiffResult) {
                    if (startVersion != dataVersion) {
                        // ignore update
                        return
                    }
                    items = update
                    diffResult.dispatchUpdatesTo(this@ItemTrendingAlbumAdapter)
                }
            }
                .execute()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            VIEW_TYPE_ITEM -> ItemAlbumViewHolder(
                binding = ItemAlbumBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onItemTap = onItemTap,
            )
            VIEW_TYPE_LOAD_MORE -> ItemAlbumLoadMoreViewHolder(
                ItemAlbumLoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> object : ViewHolder(parent) {}
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ItemAlbumViewHolder -> {
                position.takeIf { it < items.size } ?: return
                val item = items[position]
                holder.bind(item)
            }
            is ItemAlbumLoadMoreViewHolder -> {}
        }
    }

    inner class ItemAlbumViewHolder(
        private val binding: ItemAlbumBinding,
        private val onItemTap: OnItemTap? = null,
    ) : ViewHolder(binding.root) {

        fun bind(item: Album) {
            val context = binding.root.context

            // Album Image
            item.primaryRelease?.image?.let { imageUrl ->
                Glide.with(context)
                    .asBitmap()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_library_music)
                    .addListener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            // Set Default Error Image Placeholder
                            binding.acivImage.setBackgroundResource(R.color.secondaryBlue)
                            binding.acivImage.setImageResource(R.drawable.ic_library_music)
                            return true
                        }

                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            // Transform into image with rounded corners
                            val roundedImg = RoundedBitmapDrawableFactory
                                .create(context.resources, resource)
                                .apply {
                                    cornerRadius =
                                        context.resources.getDimensionPixelSize(R.dimen.img_corner_radius_12dp)
                                            .toFloat()
                                }

                            binding.acivImage.setBackgroundResource(android.R.color.transparent)
                            binding.acivImage.setImageDrawable(roundedImg)
                            return true
                        }
                    })
                    .into(binding.acivImage)
            }

            // Album Title
            binding.actvTitle.text = item.name

            // Summary
            // i.e. "17 Songs • 2005"
            binding.actvSongSummary.text = run {
                val sb = java.lang.StringBuilder()

                val songCount = item.primaryRelease?.trackIDS?.size ?: 0
                sb.append(
                    "$songCount ${
                        context.resources.getQuantityString(
                            R.plurals.trending_album_list_songs,
                            songCount
                        )
                    }"
                )
                item.primaryRelease?.releaseDate?.trim()?.let { releaseDateStr ->
//                    val releaseDate = getIsoDateFormatter(
//                        context.resources.configuration.locales.get(0)
//                    ).parse(releaseDateStr) ?: return@let
//                    val calendar = Calendar.getInstance().clone() as Calendar
//                    calendar.time = releaseDate
//                    val year = calendar.get(Calendar.YEAR)

                    // Just Extract the Year value for now
                    val year = releaseDateStr.split("-").firstOrNull()?.toInt(10)
                        ?: return@let
                    sb.append(" • ")
                    sb.append("$year")
                }

                sb.toString()
            }

            //
            // On Tap Action
            //
            binding.root.setOnClickListener { onItemTap?.invoke(item) }
        }

    }

    inner class ItemAlbumLoadMoreViewHolder(
        private val binding: ItemAlbumLoadMoreBinding
    ) : ViewHolder(binding.root)

    companion object {

        val VIEW_TYPE_ITEM = 0x01
        val VIEW_TYPE_LOAD_MORE = 0x02

//        @Suppress("ObjectPropertyName")
//        private var _dateFormatter: SimpleDateFormat? = null
//        private fun getIsoDateFormatter(locale: Locale): SimpleDateFormat {
//            if(_dateFormatter == null) {
//                /*_dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", locale)*/
//                _dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ", locale)
//                    .apply {
//                        timeZone = TimeZone.getTimeZone("UTC")
//                    }
//            }
//
//            return _dateFormatter!!
//        }
    }
}