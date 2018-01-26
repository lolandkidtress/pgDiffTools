package com.James.Model;

import java.util.Objects;


/**
 * Created by James on 2018/1/24.
 */
public class DiffResult {
  boolean isDiff = true;
  boolean lExist = false;
  boolean rExist = false;

  DiffDetail diffDetail = new DiffDetail();

  public boolean checkDiff(){

    if(lExist&&rExist && diffDetail.isEqual()){
      isDiff=false;
    }else{
      isDiff=true;
    }

    return isDiff;
  }

  public void addlInfo(String key,Objects value){
    diffDetail.addlInfo(key,value);
  }
  public void addrInfo(String key,Objects value){
    diffDetail.addrInfo(key, value);
  }

  public boolean isDiff() {
    return isDiff;
  }

  public void setIsDiff(boolean isDiff) {
    this.isDiff = isDiff;
  }

  public boolean islExist() {
    return lExist;
  }

  public void setlExist(boolean lExist) {
    this.lExist = lExist;
  }

  public boolean isrExist() {
    return rExist;
  }

  public void setrExist(boolean rExist) {
    this.rExist = rExist;
  }

  public DiffDetail getDiffDetail() {
    return diffDetail;
  }

  public void setDiffDetail(DiffDetail diffDetail) {
    this.diffDetail = diffDetail;
  }
}
