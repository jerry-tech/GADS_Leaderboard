package com.google.gadsleaderboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gadsleaderboard.models.Learning
import com.squareup.picasso.Picasso

class LearnerAdapter(private val items: List<Learning>) : RecyclerView.Adapter<LearnerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_top_learner, parent, false);
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.learner = items[position]

        //top learner's name
        holder.hourName.text = items[position].name

        //top learner's learning hours
        val tlHours : String = items[position].hours.toString()
        val adText = "learning hours,"
        val country: String = items[position].country;

        //adding the hour
        holder.hours.text = "$tlHours $adText $country"

        //image
        //loading image from the server for processing using picasso
        Picasso.get().load(items[position].badgeUrl).into(holder.hourImg)
    }

    //view holder class for getting the views in the recycler view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val hourImg : ImageView = itemView.findViewById(R.id.imgTop);
        val hourName : TextView = itemView.findViewById(R.id.top_learner_name);
        val hours : TextView = itemView.findViewById(R.id.text_top);

        var learner: Learning? = null

        override fun toString(): String {
            return """${super.toString()} '${hourName.text}'"""
        }

    }

}