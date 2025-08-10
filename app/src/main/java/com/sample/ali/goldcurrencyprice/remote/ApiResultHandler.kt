package info.alihabibi.goldcurrencyprice.remote

sealed class ApiResultHandler<out T> {

    data class OnSuccess<out T>(
        val body: T,
        val code: Int?
    ) : ApiResultHandler<T>()

    data class OnFailure(
        val errorMessage: String? = null,
        val code: Int? = null,
        val throwable: Throwable
    ) : ApiResultHandler<Nothing>()

}