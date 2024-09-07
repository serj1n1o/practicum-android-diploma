package ru.practicum.android.diploma.global.data.network.dto

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.practicum.android.diploma.global.data.network.HhApi
import ru.practicum.android.diploma.global.data.network.NetworkClient
import ru.practicum.android.diploma.global.util.NetworkUtil
import ru.practicum.android.diploma.global.util.RequestResult
import ru.practicum.android.diploma.global.util.ResponseCodes

class RetrofitNetworkClient(
    private val hhApi: HhApi,
    private val networkUtil: NetworkUtil,
) : NetworkClient {
    override suspend fun doRequest(request: Request): RequestResult<Response> {
        if (!networkUtil.isConnected()) return RequestResult.Error(ResponseCodes.CODE_NO_CONNECT)
        return withContext(Dispatchers.IO) {
            try {
                val response = getResponse(request)
                RequestResult.Success(response)
            } catch (err: HttpException) {
                Log.e("RequestError", err.toString())
                if (err.code() == ResponseCodes.CODE_VACANCY_HAVE_NOT) {
                    RequestResult.Error(ResponseCodes.CODE_VACANCY_HAVE_NOT)
                } else {
                    RequestResult.Error(ResponseCodes.CODE_BAD_REQUEST)
                }

            }
        }
    }

    private suspend fun getResponse(request: Request): Response {
        return when (request) {
            is Request.GetVacancies -> hhApi.getVacanciesRequest(request.params)
            is Request.GetVacancyById -> hhApi.getVacancyById(request.vacancyId)
        }
    }

}
