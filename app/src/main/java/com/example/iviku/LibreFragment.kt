package com.example.iviku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.iviku.databinding.FragmentLibreBinding

class LibreFragment : Fragment() {

    private var _binding: FragmentLibreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentLibreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val titulo: TextView = binding.tituloLibre
        titulo.text = "Fragmento LIBRE"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}