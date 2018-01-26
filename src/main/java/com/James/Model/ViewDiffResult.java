package com.James.Model;

/**
 * Created by James on 2018/1/24.
 */
public class ViewDiffResult extends DiffResult{

  String ViewName;

  public void addlSchemaInfo(String lSchemaName){
    diffDetail.addlInfo("Schema", lSchemaName);
  }

  public void addrSchemaInfo(String rSchemaName){
    diffDetail.addrInfo("Schema", rSchemaName);
  }

  public ViewDiffResult(String viewName) {
    ViewName = viewName;
  }


  public String getViewName() {
    return ViewName;
  }

  public void setViewName(String viewName) {
    ViewName = viewName;
  }

  @Override
  public String toString() {
    return "ViewDiffResult{" +
        "ViewName='" + ViewName + '\'' +
        ", isDiff=" + isDiff +
        ", lExist=" + lExist +
        ", rExist=" + rExist +
        ", diffDetail=" + diffDetail.toString() +
        '}';

  }
}
