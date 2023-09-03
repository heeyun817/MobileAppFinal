package com.example.myfinalapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myfinalapplication.databinding.FragmentLineBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.google.android.play.integrity.internal.x
import com.google.android.play.integrity.internal.y

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LineFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLineBinding.inflate(layoutInflater)
        var values:ArrayList<Entry> = ArrayList()
//        values.add(Entry(x, y))
        values.add(Entry(1F,1.3F))
        values.add(Entry(2F,1.2F))
        values.add(Entry(3F,1.4F))
        values.add(Entry(4F,1.5F))
        values.add(Entry(5F,1.6F))
        values.add(Entry(6F,1.8F))
        values.add(Entry(7F,1.5F))
        values.add(Entry(8F,1.6F))
        values.add(Entry(9F,1.5F))
        values.add(Entry(10F,1.3F))
        values.add(Entry(11F,1.2F))
        values.add(Entry(12F,1.8F))
        var dataset = LineDataSet(values, "DataSet1")
        dataset.color = Color.BLUE
        dataset.setCircleColor(Color.GREEN)
        dataset.lineWidth = 2f
        var data = LineData(dataset)
        binding.lineChart.data = data
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LineFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}