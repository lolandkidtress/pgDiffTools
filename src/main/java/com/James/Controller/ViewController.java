package com.James.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.James.Configure.pgToolsConfig;
import com.James.Services.ViewDiffService;
import com.James.Tools.Return;


/**
 * Created by James on 2018/1/24.
 */
@RestController
@RequestMapping
public class ViewController {

  @Autowired
  ViewDiffService _viewDiffService;
  @GetMapping("/rest/ViewDiff")
  public Return ViewDiff(){
    if(!pgToolsConfig.checkValid()){
      return Return.SUCCESS(500,"数据库配置未初始化,请先调用 /config/init 接口");
    }

    List t = _viewDiffService.getDiff();

    if (t.size()>0){
      return Return.SUCCESS(200, "对比成功").put("diff", true).put("data", t).put("size",t.size());
    }else{
      return Return.SUCCESS(200,"对比成功").put("diff",false);
    }
  }
}
