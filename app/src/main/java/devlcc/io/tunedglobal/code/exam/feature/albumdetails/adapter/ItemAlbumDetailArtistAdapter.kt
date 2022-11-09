package devlcc.io.tunedglobal.code.exam.feature.albumdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import devlcc.io.tunedglobal.code.exam.databinding.ItemArtistBinding
import devlcc.io.tunedglobal.code.exam.model.Artist

class ItemAlbumDetailArtistAdapter(
    private val items: List<Artist>
): RecyclerView.Adapter<ItemAlbumDetailArtistAdapter.ItemArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemArtistViewHolder =
        ItemArtistViewHolder(
            binding = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ItemArtistViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class ItemArtistViewHolder(
        val binding: ItemArtistBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Artist) {
            item.name?.let { artistName ->
                binding.actvArtistLabel.text = artistName
            } ?: run {
                binding.actvArtistLabel.text = ""
            }

            item.artistID?.let { artistID ->
                val artistIdLabel = "ID: $artistID"
                binding.actvArtistId.text = artistIdLabel
            } ?: run {
                binding.actvArtistId.text = ""
            }

        }
    }

}