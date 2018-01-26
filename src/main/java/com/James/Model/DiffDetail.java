package com.James.Model;

import java.util.HashMap;

import com.James.Tools.JsonConvert;


/**
 * Created by James on 2018/1/25.
 */
public class DiffDetail {

  HashMap<String,Object> lInfo = new HashMap<>();
  HashMap<String,Object> rInfo = new HashMap<>();

  public void addlInfo(String key,Object value){
    lInfo.put(key,value);
  }
  public void addrInfo(String key,Object value){
    rInfo.put(key,value);
  }

  public HashMap<String, Object> getlInfo() {
    return lInfo;
  }

  public void setlInfo(HashMap<String, Object> lInfo) {
    this.lInfo = lInfo;
  }

  public HashMap<String, Object> getrInfo() {
    return rInfo;
  }

  public void setrInfo(HashMap<String, Object> rInfo) {
    this.rInfo = rInfo;
  }

  public boolean isEqual(){
    return lInfo.equals(rInfo);
  }
  @Override
  public String toString() {
    return "DiffDetail{" +
        "lInfo=" + JsonConvert.toJson(lInfo) +
        ", rInfo=" + JsonConvert.toJson(rInfo) +
        '}';
  }
}
