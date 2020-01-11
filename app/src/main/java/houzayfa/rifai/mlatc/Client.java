package houzayfa.rifai.mlatc;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static final String BASE_URL = "http://192.168.0.163:8000/api/v1/" ;
    private static Client mInstance;
    private Retrofit retrofit ;

    private Client() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Client getInstance(){
        if (mInstance == null){
            mInstance = new Client();
        }
        return mInstance;
    }
    public API getApi(){
        return  retrofit.create(API.class);
    }
}
