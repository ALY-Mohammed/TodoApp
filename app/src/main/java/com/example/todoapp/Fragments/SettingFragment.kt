package com.example.todoapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.todoapp.R
import org.intellij.lang.annotations.Language

class SettingFragment : Fragment() {

    val itemLanguage= listOf("English","Arabic")
    val itemMode= listOf("Light","Dark")
    lateinit var autoCompleteTextViewLanguage: AutoCompleteTextView
    lateinit var autoCompleteTextViewMode: AutoCompleteTextView
   lateinit var  adapterItemsOfLanguage:ArrayAdapter<String>
    lateinit var  adapterItemsOfMode:ArrayAdapter<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        autoCompleteTextViewLanguage=view.findViewById(R.id.auto_Complete_textView)
        adapterItemsOfLanguage=ArrayAdapter(requireContext(),R.layout.list_item,itemLanguage)
        autoCompleteTextViewLanguage.setAdapter(adapterItemsOfLanguage)


        adapterItemsOfMode=ArrayAdapter(requireContext(),R.layout.list_item,itemMode)
        autoCompleteTextViewMode=view.findViewById(R.id.auto_Complete_textView2)
        autoCompleteTextViewMode.setAdapter(adapterItemsOfMode)



        autoCompleteTextViewLanguage.setOnItemClickListener(object :AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               val item = parent?.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "${item}", Toast.LENGTH_SHORT).show()
           //     autoCompleteTextViewLanguage.setHint("${item}")

            }

        })


        autoCompleteTextViewMode.setOnItemClickListener(object :AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = parent?.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "${item}", Toast.LENGTH_SHORT).show()
//                autoCompleteTextViewMode.setHint("${item}")

            }

        })

    }


}