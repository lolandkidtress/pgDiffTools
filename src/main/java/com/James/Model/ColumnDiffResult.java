package com.James.Model;

import java.util.HashMap;

import com.James.Tools.ReflectObject;


/**
 * Created by James on 2018/1/24.
 */
public class ColumnDiffResult extends DiffResult{

  String Column ;

  public ColumnDiffResult(String column) {
    this.Column = column;
  }

  public String getColumn() {
    return Column;
  }

  public void setColumn(String column) {
    this.Column = column;
  }


  public void addlInfos(PgColumn pgColumn){
    HashMap<String,Object> map = ReflectObject.reflect(pgColumn);
    for (String s : map.keySet()) {
      diffDetail.addlInfo(s, map.get(s));
    }
  }

  public void addrInfos(PgColumn pgColumn){
    HashMap<String,Object> map = ReflectObject.reflect(pgColumn);
    for (String s : map.keySet()) {
      diffDetail.addlInfo(s,map.get(s));
    }
  }

  @Override
  public String toString() {
    return "TableDiffResult{" +
        "Column='" + Column + '\'' +
        ", isDiff=" + isDiff +
        ", lExist=" + lExist +
        ", rExist=" + rExist +
        ", DiffDetail=" + diffDetail.toString() +
        '}';
  }
}
