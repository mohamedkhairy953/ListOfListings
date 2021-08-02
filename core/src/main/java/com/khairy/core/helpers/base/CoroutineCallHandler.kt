package com.khairy.core.helpers.base


import retrofit2.HttpException
import java.io.IOException

class CoroutineCallHandler<T> {
    companion object {
        inline fun <reified T> call(serviceMethod: () -> T): BaseResponseWrapper {
            return try {
                return BaseResponseWrapper.Success(serviceMethod())
            } catch (e: HttpException) {
                BaseResponseWrapper.Failed(e.message())
            } catch (e: IOException) {
                BaseResponseWrapper.NetworkError
            } catch (e: Exception) {
                BaseResponseWrapper.ServerError
            }
        }

    }


}