package com.google.gadsleaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gadsleaderboard.Services.ServiceBuilder
import com.google.gadsleaderboard.Services.SkillService
import com.google.gadsleaderboard.models.SkillIq
import kotlinx.android.synthetic.main.fragment_skilliq.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Skilliq.newInstance] factory method to
 * create an instance of this fragment.
 */
class Skilliq : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var progressBar: ProgressBar? = null

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

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_skilliq, container, false)

        //progressbar
        progressBar = root.findViewById(R.id.progressBarSkill)

        //adding the request
        addQueRequest(root);

        return root;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Skilliq.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Skilliq().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun addQueRequest(root: View) {
        val skillService = ServiceBuilder.buildService(SkillService::class.java)

        val requestCall = skillService.getSkillIq()

        //progressbar
        progressBar!!.visibility = View.VISIBLE

        requestCall.enqueue(object : Callback<List<SkillIq>> {
            override fun onResponse(
                call: Call<List<SkillIq>>,
                response: Response<List<SkillIq>>
            ) {
                if (response.isSuccessful) {
                    val skillList = response.body()!!

                    //progressbar
                    progressBar!!.visibility = View.GONE

                    //adding the loader manger function
                    recycleSkill.layoutManager = LinearLayoutManager(root.context)
                    //from layout
                    recycleSkill.adapter = SkillAdapter(skillList)

                }
            }

            override fun onFailure(call: Call<List<SkillIq>>, t: Throwable) {
                //progressbar
                progressBar!!.visibility = View.GONE

                Toast.makeText(
                    root.context,
                    "Failed to retrieve details " + t.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }


}