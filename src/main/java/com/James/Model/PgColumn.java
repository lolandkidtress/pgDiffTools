package com.James.Model;

/**
 * Created by James on 2018/1/24.
 */
public class PgColumn {
  String Schema;
  String TabName;
  String compareName;
  String columnName;
  String data_type;
  boolean is_nullable;
  String column_default;
  int character_maximum_length;
  boolean is_identity;
  String identity_generation;

  public String getSchema() {
    return Schema;
  }

  public void setSchema(String schema) {
    Schema = schema;
  }

  public String getTabName() {
    return TabName;
  }

  public void setTabName(String tabName) {
    TabName = tabName;
  }

  public String getCompareName() {
    return compareName;
  }

  public void setCompareName(String compareName) {
    this.compareName = compareName;
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getData_type() {
    return data_type;
  }

  public void setData_type(String data_type) {
    this.data_type = data_type;
  }

  public boolean is_nullable() {
    return is_nullable;
  }

  public void setIs_nullable(boolean is_nullable) {
    this.is_nullable = is_nullable;
  }

  public String getColumn_default() {
    return column_default;
  }

  public void setColumn_default(String column_default) {
    this.column_default = column_default;
  }

  public int getCharacter_maximum_length() {
    return character_maximum_length;
  }

  public void setCharacter_maximum_length(int character_maximum_length) {
    this.character_maximum_length = character_maximum_length;
  }

  public boolean is_identity() {
    return is_identity;
  }

  public void setIs_identity(boolean is_identity) {
    this.is_identity = is_identity;
  }

  public String getIdentity_generation() {
    return identity_generation;
  }

  public void setIdentity_generation(String identity_generation) {
    this.identity_generation = identity_generation;
  }
}
