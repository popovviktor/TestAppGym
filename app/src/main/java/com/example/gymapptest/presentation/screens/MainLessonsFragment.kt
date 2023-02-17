package com.example.gymapptest.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymapptest.data.api.model.Lesson
import com.example.gymapptest.data.api.model.Model
import com.example.gymapptest.databinding.FragmentMainLessonsBinding
import com.example.gymapptest.presentation.MainActivity
import com.example.gymapptest.presentation.MainViewModel
import com.example.gymapptest.utils.NetworkResult
import com.example.testgymapp.AdapterForSections
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.example.gymapptest.comparators.CustomComparator as CustomComparator

class MainLessonsFragment : Fragment() {
    lateinit var binding: FragmentMainLessonsBinding
    private val mainviewModel: MainViewModel by activityViewModels()
    private val adapter = AdapterForSections()
    lateinit var model:com.example.gymapptest.data.api.model.Model
    companion object {
        fun newInstance() = MainLessonsFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainviewModel.allmodel.observe(requireActivity() as MainActivity,Observer{
            NetworkResult.Success(data = it)
            var itdatamodel = it.data!!
            init(itdatamodel)
        })

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainLessonsBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        System.out.println("Он крейтд")
    }
    fun init(itdatamodel: Model){
        var comp = CustomComparator()
        Collections.sort(itdatamodel?.lessons!!,comp)
        System.out.println(itdatamodel.lessons.size)
        System.out.println(itdatamodel.lessons.get(0).date)
        System.out.println(itdatamodel.lessons.get(0).startTime)
        System.out.println(itdatamodel.lessons.get(1).date)
        System.out.println(itdatamodel.lessons.get(1).startTime)
        var dates = createKeyForFutureTreeMapForAdapter(itdatamodel.lessons)
        var treeMapforSection = createTreeMapForAdapterForOneSection(itdatamodel.lessons,dates)
        binding.apply {
            recViewForSection.layoutManager = LinearLayoutManager(activity)
            adapter.addAllDates(dates)
            adapter.addAllModel(treeMapforSection)
            adapter.addAlltrainers(itdatamodel.trainers)
            recViewForSection.adapter = adapter
        }
    }
    fun createKeyForFutureTreeMapForAdapter(lessonsArray: ArrayList<Lesson>):TreeSet<Calendar>{
        var dates = TreeSet<Calendar>()
        for(elem:Lesson in lessonsArray){
            var simpledateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(elem.date)
            var calendar = GregorianCalendar()
            calendar.time = simpledateFormat
            dates.add(calendar)
        }
    return dates}
    fun createTreeMapForAdapterForOneSection(lessonsArray: ArrayList<Lesson>, dates:TreeSet<Calendar>):TreeMap<Calendar,ArrayList<Lesson>>{
        var treeMapforSection:TreeMap<Calendar,ArrayList<Lesson>> = TreeMap()
        for (date in dates){
            var arrFor1dayLessons = ArrayList<Lesson>()
            for (elem in lessonsArray){
                var simpledateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(elem.date)
                var calendar = GregorianCalendar()
                calendar.time = simpledateFormat
                var elemdate = calendar as Calendar
                if (date == elemdate){
                    arrFor1dayLessons.add(elem)
                }
            }
            treeMapforSection.put(date,arrFor1dayLessons)
        }
    return treeMapforSection}
}