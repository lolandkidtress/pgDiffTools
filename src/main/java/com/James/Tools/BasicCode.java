package com.James.Tools;

/**
 * Created by James on 2018/1/26.
 */
public enum BasicCode {
  success(10200, "成功"),
  error(500100, "系统异常");

  public String note;
  public Integer code;

  private BasicCode(Integer code, String note) {
    this.note = note;
    this.code = code;
  }

  public Integer getCode(){
    return this.code;
  }

  public String getNote(){
    return this.note;
  }
}
