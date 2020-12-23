package com.ce.majiang;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author c__e
 * @date 2020/12/23 17:05
 */
public class GithubLoginTest {
    @Test
    public void test1() {
        OkHttpClient client = new OkHttpClient();
        String testToken = "009c8de09f2b0dc7cf37bccd43eba7247b2d640f";
        String url = "https://api.github.com/user";
        Request request = new Request.Builder()
                .header("Authorization", "token " + testToken)
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String string = response.body().string();
            System.out.println(string);
        } catch (IOException ignored) {
        }
    }
}
