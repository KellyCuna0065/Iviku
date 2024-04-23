package com.example.iviku

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.iviku.databinding.FragmentPaintBinding

class PaintFragment : Fragment() {

    private var _binding: FragmentPaintBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentPaintBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val miniPaint: MiniPaint = binding.miniPaint
        val line: TextView = binding.btnDrawLine
        val circle: TextView = binding.btnDrawCircle
        val arc: TextView = binding.btnDrawArc
        val rectangle: TextView = binding.btnDrawRect
        val oval: TextView = binding.btnDrawOval
        val free: TextView = binding.btnDrawFree
        val delete: TextView = binding.btnDelete

        line.setOnClickListener() {
            miniPaint.option = 1
        }

        arc.setOnClickListener() {
            miniPaint.option = 2
        }

        circle.setOnClickListener() {
            miniPaint.option = 3
        }

        rectangle.setOnClickListener {
            miniPaint.option = 4
        }

        oval.setOnClickListener {
            miniPaint.option = 5
        }

        free.setOnClickListener {
            miniPaint.option = 6
        }

        delete.setOnClickListener() {
            miniPaint.clearCanvas()
        }

        return root
    }

    /*override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}