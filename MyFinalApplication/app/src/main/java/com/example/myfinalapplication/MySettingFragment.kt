package com.example.myfinalapplication

import android.os.Bundle
import android.preference.PreferenceFragment
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class MySettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)


        val idPreference: EditTextPreference? = findPreference("id")
        //idPreference?.summaryProvider = EditTextPreference.SimpleSummaryProvider.getInstance()
        idPreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference> {
            preference ->
                val text:String? = preference.text
                if(TextUtils.isEmpty(text)){
                    "닉네임 설정이 되지 않았습니다."
                }
                else{
                    "설정된 닉네임은 $text 입니다."
                }
        }

        val genderPreference: ListPreference? = findPreference("gender")
        genderPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()

        val addrPreference: EditTextPreference? = findPreference("addr")
        addrPreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference> {
                preference ->
            val text:String? = preference.text
            if(TextUtils.isEmpty(text)){
                "주소 설정이 되지 않았습니다."
            }
            else{
                "설정된 주소는 $text 입니다."
            }
        }

        val agePreference: EditTextPreference? = findPreference("age")
        agePreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference> {
                preference ->
            val text:String? = preference.text
            if(TextUtils.isEmpty(text)){
                "나이 설정이 되지 않았습니다."
            }
            else{
                "설정된 나이는 $text 입니다."
            }
        }

    }
}