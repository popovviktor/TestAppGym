package com.example.testgymapp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapptest.R
import com.example.gymapptest.data.api.model.Lesson
import com.example.gymapptest.data.api.model.Trainer
import com.example.gymapptest.databinding.ItemModelBinding
import java.text.SimpleDateFormat
import java.util.*

class AdapterMyModel: RecyclerView.Adapter<AdapterMyModel.ModelHolder>() {
    val trainersList = ArrayList<Trainer>()
    val modelList= ArrayList<Lesson>()
    val setDate = TreeSet<Calendar>()

    class ModelHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding = ItemModelBinding.bind(item)
        fun bind(model:Lesson,trainers: ArrayList<Trainer>)= with(binding){
            itemEndTime.text =model.endTime
            itemStartTime.text = model.startTime
            if(model.name?.length!!>25){
                itemNameLesson.text = model?.name?.substring(0,25) +"..."
            }else{itemNameLesson.text = model.name}

            if(model.place?.length!!>19){
                itemPlace.text = model.place.toString().substring(0,19)+"..."
            }else{itemPlace.text = model.place}
            //search lenght lesson for item
            val formatStartTime = SimpleDateFormat("HH:mm").parse(model.startTime)
            val formatEndTime = SimpleDateFormat("HH:mm").parse(model.endTime)
            var dif = formatEndTime.time - formatStartTime.time
            var strbufForLenghtLessontext = StringBuffer()
            val SECOND = 1000
            val MINUTE = 60 * SECOND
            val HOUR = 60 * MINUTE
            var h:Int = 0
            var m:Int = 0
            if (dif >= HOUR){
                h = (dif/HOUR).toInt()
                dif = dif%HOUR
            }
            if (dif >= MINUTE){
                m = (dif/MINUTE).toInt()
                 }
            strbufForLenghtLessontext.append(h).append(" ч. ").append(m).append(" мин")
            itemLenghtLesson.text = strbufForLenghtLessontext
            // end search lenght lesson for item
            for (elem:Trainer in trainers){
                if (model.coachId == elem.id){
                    itemLessonNameTrainer.text = elem.fullName
                }
            }
            itemView.setOnClickListener{
                System.out.println(model.name.toString())
                //var dataModelItemOnTouchFragment = DataModelItemOnTouchFragment()
                var bundle: Bundle = Bundle()
                bundle.putSerializable("modelItem",model)
                bundle.putSerializable("trainersItem",trainers)
                //val action = FragmentD
                itemView.findNavController().navigate(R.id.itemOnTouchFragment,bundle)
            }
            constrForColorBackLeftLine.setBackgroundColor(Color.parseColor(model.color))


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_model,parent,false)

        return ModelHolder(view)
    }

    override fun onBindViewHolder(holder: ModelHolder, position: Int) {
        holder.bind(modelList.get(position),trainersList)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }
    fun addModel(model: Lesson){
        modelList.add(model)
        notifyDataSetChanged()
    }

    fun addTrainer(model: Trainer){
        trainersList.add(model)
        notifyDataSetChanged()
    }
    fun addAll(models:ArrayList<Lesson>,trainers:ArrayList<Trainer> ){
        for(elem:Lesson in models){
            addModel(elem)
            var simpledateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(elem.date)
            var calendar = GregorianCalendar()
            calendar.time = simpledateFormat
            setDate.add(calendar)
        }
        for (elem:Trainer in trainers){
            addTrainer(elem)
        }
    }
}