package com.google.gadsleaderboard.Services

import com.google.gadsleaderboard.models.SkillIq
import retrofit2.Call
import retrofit2.http.GET

interface SkillService {

    //declaring the url the get request will be mapped to
    @GET(" /api/skilliq")
    fun getSkillIq() : Call<List<SkillIq>>
}