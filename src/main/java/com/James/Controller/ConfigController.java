package com.James.Controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.James.Configure.pgToolsConfig;
import com.James.Tools.Return;


/**
 * Created by James on 2018/1/24.
 */
@RestController
@RequestMapping("")
public class ConfigController {

  //TODO 放body
  @PostMapping("/rest/init")
  public Return init(String scope,
      String lurl,String lusername,String lpassword,
      String rurl,String rusername,String rpassword){

    if(!pgToolsConfig.checkScope(scope)){
      return Return.FAIL(500, "检查范围错误,支持的范围为" + StringUtils.join(pgToolsConfig.availableCompareScope, ","));
    }

    pgToolsConfig.init(
        lurl,lusername,lpassword,
        rurl,rusername,rpassword);

    return Return.SUCCESS(200,"初始化数据库成功");
  }

}
