package com.matheus0liveira.rickandmorty.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.matheus0liveira.rickandmorty.R
import com.matheus0liveira.rickandmorty.model.Episode


class EpisodeAdapter(private val items: List<Episode>) :
    RecyclerView.Adapter<EpisodeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.episode_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(episode: Episode) {


            val epName = itemView.findViewById<TextView>(R.id.episode_name)
            val epAirDate = itemView.findViewById<TextView>(R.id.episode_air_date)
            val epSeasonEp = itemView.findViewById<TextView>(R.id.episode_season_ep)

            epName.text = episode.name
            epAirDate.text = itemView.context.getString(R.string.air_date, episode.airDate)
            epSeasonEp.text = episode.episode

        }
    }
}