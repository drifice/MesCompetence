package com.example.app_demo.popups

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_demo.R
import com.example.app_demo.models.CompetenceModel
import com.example.app_demo.repositories.CompetenceRepository
import com.example.app_demo.stats.TagsAdapter


class CompetenceDetailPopup(context: Context, val competence: CompetenceModel) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_competence_detail)

        val nameView = findViewById<TextView>(R.id.competence_title)
        nameView.text = competence.name
        val descriptionView = findViewById<TextView>(R.id.description_field)
        descriptionView.text = competence.description
        val tagsView = findViewById<RecyclerView>(R.id.tags_recycler)
        tagsView.adapter = TagsAdapter(competence.tags)
        val removeButton = findViewById<Button>(R.id.remove_button)
        removeButton.setOnClickListener {
            CompetenceRepository.remove(competence)
            dismiss()
        }
        val level = findViewById<TextView>(R.id.niveau)
        val btnLvlDown = findViewById<ImageButton>(R.id.btn_lvl_down)
        val btnLvlUp = findViewById<ImageButton>(R.id.btn_lvl_up)

        this.checkLevelAndSetButtonColor( btnLvlDown, btnLvlUp)

        btnLvlDown.setOnClickListener {
            competence.niveau--
            CompetenceRepository.insert(competence)
            level.text = competence.niveau.toString()
            checkLevelAndSetButtonColor(btnLvlDown, btnLvlUp)
        }
        btnLvlUp.setOnClickListener {
            competence.niveau++
            CompetenceRepository.insert(competence)
            level.text = competence.niveau.toString()
            checkLevelAndSetButtonColor(btnLvlDown, btnLvlUp)
        }

    }

    fun checkLevelAndSetButtonColor( btnLvlDown: ImageButton, btnLvlUp: ImageButton) {
        if (competence.niveau == 20) {
            btnLvlUp.setBackgroundColor(context.getColor(R.color.light_grey))
        } else if (competence.niveau == 0) {
            btnLvlDown.setBackgroundColor(context.getColor(R.color.light_grey))
        } else {
            btnLvlUp.setBackgroundColor(context.getColor(R.color.green))
            btnLvlDown.setBackgroundColor(context.getColor(R.color.red))
        }
    }
}