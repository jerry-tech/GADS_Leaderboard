package com.google.gadsleaderboard

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gadsleaderboard.models.SkillIq
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycle_skill_learner.view.*

class SkillAdapter(private val items: List<SkillIq>) : RecyclerView.Adapter<SkillAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_skill_learner, parent, false);
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.skill = items[position]

        holder.skName.text = items[position].name

        //formatting string
        val score : String = items[position].score.toString()
        val adText = "skill IQ Score,"
        val country: String = items[position].country

        holder.skScore.text = "$score $adText $country"

        //image
        //loading image from the server for processing using picasso
        Picasso.get().load(items[position].badgeUrl).into(holder.skImg)
    }

    //view holder class for getting the views in the recycler view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val skImg : ImageView = itemView.findViewById(R.id.imgSkill);
        val skName : TextView = itemView.findViewById(R.id.skill_name);
        val skScore : TextView = itemView.findViewById(R.id.text_skill);

        var skill: SkillIq? = null

        override fun toString(): String {
            return """${super.toString()} '${skName.text}'"""
        }
    }


}