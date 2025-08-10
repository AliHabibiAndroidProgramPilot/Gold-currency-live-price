package info.alihabibi.goldcurrencyprice.remote

import info.alihabibi.goldcurrencyprice.remote.model.PriceApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PriceApiService {

    /**
     * This is Main Function For Api
     * Receiving Both Gold And Currency Data At One Body
     * @author Ali Habibi
     * @param apiKey api key
     */
    @GET("Gold_Currency.php?")
    suspend fun getPrices(@Query("key") apiKey: String) : Response<PriceApiModel>

}