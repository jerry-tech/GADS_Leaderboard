package com.google.gadsleaderboard.Services

import com.google.gadsleaderboard.models.Learning
import com.google.gadsleaderboard.models.SkillIq
import retrofit2.Call
import retrofit2.http.GET

interface LearnerService {

    //declaring the url the get request will be mapped to
    @GET(" /api/hours")
    fun getLearners() : Call<List<Learning>>
}