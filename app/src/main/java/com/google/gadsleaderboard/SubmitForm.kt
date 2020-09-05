package com.google.gadsleaderboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gadsleaderboard.Services.ProjectBuilder
import com.google.gadsleaderboard.Services.ProjectService
import com.google.gadsleaderboard.models.ProjectModel
import kotlinx.android.synthetic.main.activity_submit_form.*
import kotlinx.android.synthetic.main.confirm_response.view.*
import kotlinx.android.synthetic.main.content_submit_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmitForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_form)

        //onclick listener for back button
        btnBack.setOnClickListener { view ->
            //calling the function that contains the intent
            goBack()
        }

        //onclick listener for submitting form
        btnSubmit.setOnClickListener { view ->

            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.confirm_response, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)

            //show dialog
            val mAlertDialog = mBuilder.show()
            mAlertDialog.setCancelable(false);

            //onclick listener for alert dialog(dismiss)
            mDialogView.dialogCancel.setOnClickListener {
                //canceling the alert dialog
                mAlertDialog.dismiss()
            }

            //yes response
            mDialogView.yesProject.setOnClickListener {
                //canceling the alert dialog
                mAlertDialog.dismiss()
                //submit project
                submitProject()
            }


        }
    }

    //intent for going back
    private fun goBack() {
        val activityIntent = Intent(this, LeaderBoard::class.java)
        startActivity(activityIntent)
    }

    //function used to submit project
    private fun submitProject() {
        //getting the text from the textFields
        val firstName: String = subFirstName.text.toString()
        val lastName: String = subLastName.text.toString()
        val emailAdd: String = subEmailAddress.text.toString()
        val linkProj: String = subProject.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && emailAdd.isNotEmpty() && linkProj.isNotEmpty()) {
            //service
            val projectService = ProjectBuilder.buildProject(ProjectService::class.java)

            //request call
            val requestCall = projectService.submitProject(firstName, lastName, emailAdd, linkProj);

            //making the network call using the enqueue function
            requestCall.enqueue(object : Callback<ProjectModel> {
                //on failure
                override fun onFailure(call: Call<ProjectModel>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ProjectModel>,
                    response: Response<ProjectModel>
                ) {
                    //for successful response
                    if (response.isSuccessful) {
                        createDialog(R.layout.success_response, true)
                    } else {
                        createDialog(R.layout.error_response, true)
                    }
                }

            })
        } else {
            //error response
            createDialog(R.layout.error_response, true)
        }

    }

    private fun createDialog(layout: Int, cancel: Boolean) {
        //Inflate the dialog with custom view
        val mDialogView = LayoutInflater.from(this).inflate(layout, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)

        //show dialog
        val mAlertDialog = mBuilder.show()
        mAlertDialog.setCancelable(cancel);

    }
}