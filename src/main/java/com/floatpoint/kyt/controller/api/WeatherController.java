package com.floatpoint.kyt.controller.api;

import com.floatpoint.kyt.common.result.Result;
import com.floatpoint.kyt.common.utils.ControllerSuccessUtil;
import com.floatpoint.kyt.entity.params.add.WeatherAddPA;
import com.floatpoint.kyt.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Api(description = "天气")
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;
    @ApiOperation(value = "天气输入")
    @PostMapping("/addweather")
    public Result addMine(@RequestBody WeatherAddPA weatherAddPA){
        boolean add = weatherService.addweather(weatherAddPA);

        return ControllerSuccessUtil.result(add);
    }

    @ApiOperation(value = "天气")
    @PostMapping("/wea")
    public Result wea(String str1, String str2) throws IOException {
        Process proc;
        String[] arg = new String[] { "python", "C:\\Users\\simple\\PycharmProjects\\pythonProject\\test.py", String.valueOf(str1), String.valueOf(str2) };
        proc = Runtime.getRuntime().exec(arg);
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String str = in.readLine();
//        System.out.println(str);
//        return Result.success().data("msg",str);

        return Result.success().data("msg",str);
    }
}
