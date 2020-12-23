package com.ce.majiang.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.ce.majiang.dto.AccessTokenDTO;
import com.ce.majiang.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author c__e
 * @date 2020/12/23 16:32
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .header("Accept", "application/json")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String string = response.body().string();
            System.out.println(string);
            return JSONObject.parseObject(string).getString("access_token");
        } catch (IOException ignored) {
        }
        return null;
    }

    public GithubUser getUser(String token) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.github.com/user";
        Request request = new Request.Builder()
                .header("Authorization", "token " + token)
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String string = response.body().string();
            return JSON.parseObject(string, GithubUser.class);
        } catch (IOException ignored) {
        }
        return null;
    }
}
