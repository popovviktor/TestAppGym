package com.example.gymapptest.presentation.screens.onTouchItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymapptest.R
import com.example.gymapptest.data.api.model.Lesson
import com.example.gymapptest.data.api.model.Trainer
import com.example.gymapptest.databinding.FragmentItemOnTouchBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemOnTouchFragment : Fragment() {
    lateinit var binding:FragmentItemOnTouchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemOnTouchBinding.inflate(layoutInflater)
        return return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var lesson = arguments?.get("modelItem") as Lesson
        var trainers = arguments?.get("trainersItem") as ArrayList<Trainer>
        binding.apply {
            itemDesription.text = lesson.description
            itemTouchStartTime.text = lesson.startTime
            itemTouchEndTime.text = lesson.endTime
            itemTouchNameLesson.text = lesson.name
            itemTouchPlace.text = lesson.place
            //formate date to calendar and 'ru' version in screen
            var simpledateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(lesson.date)
            var calendar = GregorianCalendar()
            calendar.time = simpledateFormat
            var date = calendar
            var strDayOfWeek = date.getDisplayName(
                Calendar.DAY_OF_WEEK,
                Calendar.LONG_FORMAT, Locale("ru")
            ).toString()
            var strIntDay = date.get(Calendar.DAY_OF_MONTH).toString()
            var strNameMonth = date.getDisplayName(
                Calendar.MONTH,
                Calendar.LONG_FORMAT,
                Locale("ru")
            )
            itemTouchAvailSlots.text = lesson.availableSlots.toString()
            for (elem:Trainer in trainers){
                if (lesson.coachId == elem.id){
                    itemTouchNameTrainer.text = elem.fullName
                }
            }
            itemOntouchDate.text = strDayOfWeek+", "+strIntDay+" "+strNameMonth
        }
        binding.btnbackToLEssons.setOnClickListener {
            findNavController().navigate(R.id.bottom_nav_lessons)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemOnTouchFragment().apply {
                arguments = Bundle()
            }
    }
}