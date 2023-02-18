package com.example.todoapp.Fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Adapter.TodosAdapter
import com.example.todoapp.HomeTodoApp
import com.example.todoapp.R
import com.example.todoapp.TodoDatabase.MyDatabase
import com.example.todoapp.TodoDatabase.Todo
import com.example.todoapp.UpdateActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.Calendar


class ListFragment : Fragment() {

    lateinit var calenderViewPackage:MaterialCalendarView
    var selectedDatePackage:Calendar=Calendar.getInstance()
    var todosAdapter=TodosAdapter(listOf())

    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val todoOfStart = MyDatabase.getinstance(requireContext()).getTodoDao().getTodoByDate(selectedDatePackage.time.time)
//        todosAdapter=TodosAdapter(todoOfStart)

        initViews(view)
        initListners()
        pushActivityUpdate()
        iconCoreckChick()


    }

    fun initViews(view:View){
        calenderViewPackage=view.findViewById(R.id.calenderViewPackage)
        calenderViewPackage.selectedDate=CalendarDay.today()
        calenderViewPackage.selectedDate
        recyclerView=view.findViewById(R.id.recyclerViewContaner)
        recyclerView.adapter=todosAdapter
    }

    fun refreshList(){
        val todo = MyDatabase.getinstance(requireActivity()).getTodoDao().getTodoByDate(selectedDatePackage.time.time)
        todosAdapter.changeList(todo)
    }


    fun initListners(){
     calenderViewPackage.setOnDateChangedListener(object :OnDateSelectedListener{
         override fun onDateSelected(
             widget: MaterialCalendarView,
             date: CalendarDay,
             selected: Boolean
         ) {
             selectedDatePackage.clear(Calendar.MILLISECOND)
             selectedDatePackage.clear(Calendar.MINUTE)
             selectedDatePackage.clear(Calendar.HOUR)
             selectedDatePackage.clear(Calendar.SECOND)
             selectedDatePackage.set(date.year,date.month-1,date.day)
             refreshList()
         }

     })


        todosAdapter.onItemDeletedClicked=object :TodosAdapter.OnItemDeletedClicked{
            override fun onItemClick(position: Int, todo: Todo) {
                callBackDeleteButton?.deleet(position,todo)
            }

        }

    }

    fun pushActivityUpdate(){
        todosAdapter.onItemClick=object :TodosAdapter.OnItemClick{
            override fun onItemClicked(todo: Todo) {
               val intent = Intent(requireContext(),UpdateActivity::class.java)
                intent.putExtra("T","${ todo.title}")
                intent.putExtra("D","${todo.description}")
                intent.putExtra("id","${todo.id}")
                startActivity(intent)
            }
        }
    }


    fun iconCoreckChick() {
        todosAdapter.oncorecktlick = object : TodosAdapter.Oncorecktlick {
            override fun onChecked(v: TodosAdapter.viewHolder,todo: Todo) {


              val click= Todo(id=todo.id, description = todo.description, title =todo.title, isDone = true, date = todo.date)
                MyDatabase.getinstance(requireContext()).getTodoDao().updateTodo(click)

                v.iconCheck.setImageResource(R.drawable.done)
                v.titletextView.setTextColor(Color.GREEN)
                v.descriptiontextView.setTextColor(Color.GREEN)


            }


        }

    }


    var callBackDeleteButton:CallBackDeleteButton?=null

    interface CallBackDeleteButton{
        fun deleet(position:Int,todo:Todo)
    }
}