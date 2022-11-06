package com.example.mywechat.controller;

import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.spring.application.ImageCaptchaApplication;
import cloud.tianai.captcha.spring.plugins.secondary.SecondaryVerificationApplication;
import cloud.tianai.captcha.spring.vo.CaptchaResponse;
import cloud.tianai.captcha.spring.vo.ImageCaptchaVO;
import cloud.tianai.captcha.validator.common.model.dto.ImageCaptchaTrack;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CaptchaController
 * @Description TODO
 * @Author Wenbo Li
 * @Date 6/11/2022 下午 8:10
 * @Version 1.0
 */
@RestController
public class CaptchaController {
    @Resource
    private ImageCaptchaApplication imageCaptchaApplication;

    @GetMapping("/gen")
    public CaptchaResponse<ImageCaptchaVO> genCaptcha(HttpServletRequest request, @RequestParam(value = "type", required = false) String type) {
        if (StrUtil.isBlank(type)) {
            type = CaptchaTypeConstant.SLIDER;
        }
        CaptchaResponse<ImageCaptchaVO> response = imageCaptchaApplication.generateCaptcha(type);
        return response;
    }

    @PostMapping("/check")
    public boolean checkCaptcha(@RequestParam("id") String id,
                                @RequestBody ImageCaptchaTrack imageCaptchaTrack,
                                HttpServletRequest request) {
        return imageCaptchaApplication.matching(id, imageCaptchaTrack);
    }

    /**
     * 二次验证，一般用于机器内部调用，这里为了方便测试
     *
     * @param id id
     * @return boolean
     */
    @GetMapping("/check2")
    public boolean check2Captcha(@RequestParam("id") String id) {
        // 如果开启了二次验证
        if (imageCaptchaApplication instanceof SecondaryVerificationApplication) {
            return ((SecondaryVerificationApplication) imageCaptchaApplication).secondaryVerification(id);
        }
        return false;
    }
}
