package com.example.iviku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.iviku.databinding.FragmentProgramacionBinding

class ProgramacionFragment : Fragment() {

    private var _binding: FragmentProgramacionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentProgramacionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var titulo: TextView = binding.tituloProgramacion
        titulo.text = "Fragmento PROGRAMACION"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}