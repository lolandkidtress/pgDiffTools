package com.James.Model;

/**
 * Created by James on 2018/1/24.
 */
public class TableDiffResult extends DiffResult{
  String TabName;

  public TableDiffResult(String tabName) {
    TabName = tabName;

  }

  public void addlSchemaInfo(String lSchemaName){
    diffDetail.addlInfo("Schema", lSchemaName);
  }

  public void addrSchemaInfo(String rSchemaName){
    diffDetail.addrInfo("Schema", rSchemaName);
  }

  public String getTabName() {
    return TabName;
  }

  public void setTabName(String tabName) {
    TabName = tabName;
  }

  @Override
  public String toString() {
    return "TableDiffResult{" +
        "TabName='" + TabName + '\'' +
        ", isDiff=" + isDiff +
        ", lExist=" + lExist +
        ", rExist=" + rExist +
        ", DiffDetail=" + diffDetail.toString() +
        '}';
  }
}
