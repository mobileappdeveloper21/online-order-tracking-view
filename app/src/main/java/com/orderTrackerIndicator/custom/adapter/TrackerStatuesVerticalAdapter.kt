package com.orderTrackerIndicator.custom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orderTrackerIndicator.custom.model.TrackerStateProgressData
import com.orderTrackerIndicator.databinding.VerticalStatusProgressBinding

class TrackerStatuesVerticalAdapter constructor():RecyclerView.Adapter<TrackerStatuesVerticalAdapter.TrackerStatusViewHolder>() {


    private val trackerStatusProgressList : ArrayList<TrackerStateProgressData> = arrayListOf()

    public fun addList(trackerStatusProgressList:ArrayList<TrackerStateProgressData>){
        this.trackerStatusProgressList.addAll(trackerStatusProgressList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackerStatusViewHolder {
        return TrackerStatusViewHolder(VerticalStatusProgressBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TrackerStatusViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = trackerStatusProgressList.size

    inner class TrackerStatusViewHolder(val binding:VerticalStatusProgressBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

}