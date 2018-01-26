package com.James.Tools;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.James.Model.PgColumn;


/**
 * Created by James on 2018/1/26.
 * 反射方式取得字段名和字段值
 */
public class ReflectObject {

  //反射 对象 的key和value
  //处理String,int和boolean
  public static HashMap<String,Object> reflect(Object obj) {

    if (obj == null) {
      return null;
    }

    HashMap<String,Object> retMap= new HashMap<>();
    Field[] fields = obj.getClass().getDeclaredFields();

    for (int j = 0; j < fields.length; j++) {
      fields[j].setAccessible(true);
      //String 类型
      if (fields[j].getType().getName().equals(
          java.lang.String.class.getName())) {
        // String type
        try {
          retMap.put(fields[j].getName(),fields[j].get(obj));
        } catch (IllegalArgumentException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      //Integer 类型
      if (
          fields[j].getType().getName().equals(
          java.lang.Integer.class.getName())
          ||
          "int".equals(fields[j].getType().getName())
          ) {
        try {
          retMap.put(fields[j].getName(), fields[j].getInt(obj));
        } catch (IllegalArgumentException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

      //boolean 类型
      if (fields[j].getType().getName().equals(
          java.lang.Boolean.class.getName())
          ||
          "boolean".equals(fields[j].getType().getName())
          ) {
        // Integer type
        try {
          retMap.put(fields[j].getName(), fields[j].getBoolean(obj));
        } catch (IllegalArgumentException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      //TODO 其他类型
    }
    return retMap;
  }

  public static void main(String[] args) {
    PgColumn pgColumn = new PgColumn();
    pgColumn.setColumnName("ColumnName");
    pgColumn.setIs_nullable(true);
    pgColumn.setCharacter_maximum_length(31);

    HashMap<String,Object> map = reflect(pgColumn);
    for (String s : map.keySet()) {
      System.out.println(s+":"+map.get(s));
    }
  }
}
