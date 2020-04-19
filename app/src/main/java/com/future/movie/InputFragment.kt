package com.future.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.frag_input.*

class InputFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = InputFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_input, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireActivity()
        val inputViewModel = ViewModelProviders.of(activity)
            .get(InputViewModel::class.java)

        saveBtn.setOnClickListener {
            val type = typeEt.text.toString().trim()
            val title = titleEt.text.toString().trim()
            val content = contentEt.text.toString().trim()
            val pic = picEt.text.toString().trim()
            val logo = logoEt.text.toString().trim()
            if (type.isNotBlank() && title.isNotBlank() &&
                content.isNotBlank() && pic.isNotBlank() && logo.isNotBlank()) {
                inputViewModel.saveMovie(type, title, content, pic, logo)
            }
        }
    }
}