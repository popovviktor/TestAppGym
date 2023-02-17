package com.example.testgymapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapptest.R
import com.example.gymapptest.data.api.model.Lesson
import com.example.gymapptest.data.api.model.Trainer
import com.example.gymapptest.databinding.ItemsOneSectionBinding
import java.util.*
import kotlin.collections.ArrayList

class AdapterForSections: RecyclerView.Adapter<AdapterForSections.ModelHolder>() {
    val map= TreeMap<Calendar,ArrayList<Lesson>>()
    val trainersList = ArrayList<Trainer>()
    val dates = ArrayList<Calendar>()
    class ModelHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding = ItemsOneSectionBinding.bind(item)
        fun bind(date: Calendar, lessons:ArrayList<Lesson>, trainers: ArrayList<Trainer>)= with(binding){
            val strDayOfWeek = date.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG_FORMAT, Locale("ru")).toString()
            val strIntDay = date.get(Calendar.DAY_OF_MONTH).toString()
            val strNameMonth = date.getDisplayName(Calendar.MONTH,Calendar.LONG_FORMAT,Locale("ru"))
            binding.seictionTextDate.text = strDayOfWeek+", "+strIntDay+" "+strNameMonth
            val adapterMyModel = AdapterMyModel()
            adapterMyModel.addAll(lessons,trainers)
            binding.SectionRecyclerView.adapter = adapterMyModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_one_section,parent,false)

        return ModelHolder(view)
    }

    override fun onBindViewHolder(holder: ModelHolder, position: Int) {
        holder.bind(dates[position],map.get(dates.get(position)) as ArrayList<Lesson>,trainersList)
    }

    override fun getItemCount(): Int {
        return dates.size
    }
    fun addModel(key:Calendar,value:java.util.ArrayList<Lesson>){
        map.put(key,value)
        notifyDataSetChanged()
    }
    fun addAllModel(mapAll:TreeMap<Calendar,ArrayList<Lesson>>){
        mapAll.forEach{ (t: Calendar, u: ArrayList<Lesson>) -> addModel(t,u) }
    }
    fun addAllDates(datesfrom:Set<Calendar>){
        dates.addAll(datesfrom)
        notifyDataSetChanged()
    }
    fun addAlltrainers(trainers: ArrayList<Trainer>){
        trainersList.addAll(trainers)
        notifyDataSetChanged()
    }
}