package com.example.myfinalapplication

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfinalapplication.databinding.FragmentRadarBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RadarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RadarFragment : Fragment() {
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
        val binding = FragmentRadarBinding.inflate(layoutInflater)
        var radar_values : ArrayList<RadarEntry> = ArrayList()
        radar_values.add(RadarEntry(28.2F))
        radar_values.add(RadarEntry(13.4F))
        radar_values.add(RadarEntry(18.2F))
        radar_values.add(RadarEntry(11.2F))
        radar_values.add(RadarEntry(7.8F))
        radar_values.add(RadarEntry(5.8F))
        radar_values.add(RadarEntry(3.8F))

        var radar_dataset = RadarDataSet(radar_values,"DataSet2")
        radar_dataset.color = Color.GREEN
        val label = arrayOf("영유아","10대","20대","30대","40대","50대","60대 이상")
        val xAxis : XAxis = binding.radarChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(label)
        var radar_data = RadarData(radar_dataset)
        binding.radarChart.data = radar_data
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RadarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RadarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}