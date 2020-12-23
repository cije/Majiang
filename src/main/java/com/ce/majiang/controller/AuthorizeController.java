package com.ce.majiang.controller;

import com.ce.majiang.dto.AccessTokenDTO;
import com.ce.majiang.dto.GithubUser;
import com.ce.majiang.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author c__e
 * @date 2020/12/23 16:26
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client_id}")
    private String client_id;

    @Value("${github.client_secret}")
    private String client_secret;

    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO()
                .setClient_id(client_id)
                .setClient_secret(client_secret)
                .setCode(code)
                .setRedirect_uri(redirect_uri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if (!ObjectUtils.isEmpty(user)) {
            // 登陆成功 写Cookie和Session
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        } else {
            // 登陆失败，重新登陆
            return "redirect:/";
        }
    }
}
