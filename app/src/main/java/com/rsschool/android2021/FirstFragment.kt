package com.rsschool.android2021

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private lateinit var generateButton: Button
    private lateinit var previousResult: TextView
    private lateinit var minValueEditText: EditText
    private lateinit var maxValueEditText: EditText
    private var onFirstFragmentButtonListener: FirstFragmentButtonListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValueEditText = view.findViewById(R.id.min_value)
        maxValueEditText = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult.text = "Previous result: ${result.toString()}"


        generateButton.setOnClickListener {
            onGenerateButtonClicked()
        }
    }

    private fun onGenerateButtonClicked() {
        var minValueString = minValueEditText.text?.toString() ?: "0"
        var min = -1
        if (minValueString == "") {
            minValueString = "-1"
        }
        val minLong = minValueString.toLong()
        if (minLong <= Int.MAX_VALUE) {
            min = minLong.toInt()
        }

        var maxValueString = maxValueEditText.text?.toString() ?: "0"
        var max = 0
        if (maxValueString == "") {
            maxValueString = "0"
        }
        val maxLong = maxValueString.toLong()
        if (maxLong <= Int.MAX_VALUE) {
            max = maxLong.toInt()
        }

        if (min >= 0 && max > 0 && min < max && max <= Int.MAX_VALUE) {
            onFirstFragmentButtonListener?.onFirstFragmentButtonListener(min, max)
        } else getToast()
    }

    private fun getToast() {
        val text = "Из указанного диапазона нельзя выбрать случайное значение."
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(activity?.baseContext, text, duration)
        toast.show()
    }

    fun setFirstFragmentButtonListener(listener: FirstFragmentButtonListener) {
        onFirstFragmentButtonListener = listener
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}