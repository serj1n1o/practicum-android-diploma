package ru.practicum.android.diploma.global.data.network.dto

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.practicum.android.diploma.global.data.network.HhApi
import ru.practicum.android.diploma.global.data.network.NetworkClient
import ru.practicum.android.diploma.global.util.ErrorEnum
import ru.practicum.android.diploma.global.util.NetworkUtil
import ru.practicum.android.diploma.global.util.RequestResult
import java.io.IOException

class RetrofitNetworkClient(
    private val hhApi: HhApi,
    private val networkUtil: NetworkUtil

) : NetworkClient {
    override suspend fun doRequest(request: Request): RequestResult<Response> {
        if (networkUtil.isConnected()) return RequestResult.Error(ErrorEnum.NO_INTERNET, errTxt = "")
        return withContext(Dispatchers.IO) {
            try {
                val response = getResponse(request)
                RequestResult.Success(response)
            } catch (err: IOException) {
                RequestResult.Error(ErrorEnum.SERVER_ERROR, errTxt = err.message)
            } catch (err: HttpException) {
                RequestResult.Error(ErrorEnum.HTTP_ERROR, errTxt = err.message)
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
