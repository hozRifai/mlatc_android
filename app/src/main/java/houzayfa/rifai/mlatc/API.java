package houzayfa.rifai.mlatc;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {
    @FormUrlEncoded
    @POST("articles/")
    Call<ResponseBody> createArticle(
            @Field("article_content") String article_content
    );
}
