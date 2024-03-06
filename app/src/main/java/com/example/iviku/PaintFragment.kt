package com.example.iviku

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.iviku.databinding.FragmentPaintBinding

class PaintFragment : Fragment() {

    private var _binding: FragmentPaintBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentPaintBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val miniPaint: MiniPaint = binding.miniPaint
        val line: TextView = binding.btnDrawLine
        val circle: TextView = binding.btnDrawCircle
        val arc: TextView = binding.btnDrawArc
        val delete: TextView = binding.btnDelete

        line.setOnClickListener() {
            miniPaint.option = 1
        }

        circle.setOnClickListener() {
            miniPaint.option = 2
        }

        arc.setOnClickListener() {
            miniPaint.option = 3
        }

        delete.setOnClickListener() {
            miniPaint.clearCanvas()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}