package com.example.app_demo.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.app_demo.MainActivity
import com.example.app_demo.R
import com.example.app_demo.models.CompetenceModel
import com.example.app_demo.repositories.CompetenceRepository

class NewCompetenceFragment(private val mainActivity: MainActivity) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_competence, container, false)

        val nameView = view.findViewById<EditText>(R.id.name_input)
        val tagsView = view.findViewById<EditText>(R.id.tags_input)
        val buttonView = view.findViewById<Button>(R.id.submit_button)
        val descriptionView = view.findViewById<EditText>(R.id.description_input)

        buttonView.setOnClickListener {
            val name = nameView.text.toString()
            val tags = tagsView.text.split(",").map { it.trim().lowercase() }
            val description = descriptionView.text.toString()
            val competence = CompetenceModel(name = name, tags = tags, description = description )
            CompetenceRepository.insert(competence)
            mainActivity.loadFragment(HomeFragment(mainActivity), R.string.home_page_title)
        }
        return view
    }
}