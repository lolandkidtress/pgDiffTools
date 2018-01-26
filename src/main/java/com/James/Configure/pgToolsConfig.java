package com.James.Configure;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import com.James.Model.DBConfig;
import com.James.Tools.DB_Client;


/**
 * Created by James on 2018/1/24.
 */
public class pgToolsConfig {


  //比较的范围
  private String compareScope = "db,table,index,sequence,view,column";
  //当前支持的范围
  public final static String[] availableCompareScope = new String[]{"table","view","column"};

  //需要比较的2个数据库的配置
  private static DBConfig lDBConfig = new DBConfig();
  private static DBConfig rDBConfig = new DBConfig();

  //需要比较的2个数据库的连接池
  private static SqlSessionFactory lSqlSessionFactory;
  private static SqlSessionFactory rSqlSessionFactory;

  private static boolean isLValid =false;
  private static boolean isRValid =false;


  public static void init(
      String lurl,String lusername,String lpassword,
      String rurl,String rusername,String rpassword){

      DB_Client ldc = new DB_Client();
      lDBConfig.setUrl(lurl);
      lDBConfig.setUsername(lusername);
      lDBConfig.setPassword(lpassword);

      DB_Client rdc = new DB_Client();
      rDBConfig.setUrl(rurl);
      rDBConfig.setUsername(rusername);
      rDBConfig.setPassword(rpassword);

      lSqlSessionFactory = ldc.createSqlSessionFactory(lurl, lusername, lpassword);
      isLValid = true;
      rSqlSessionFactory = rdc.createSqlSessionFactory(rurl, rusername, rpassword);
      isRValid = true;


  }

  public static boolean checkValid(){
    if(isLValid&&isRValid){
      return true;
    }else{
      return false;
    }
  }

  public static boolean checkScope(String scope){
    String[] scopes = scope.split(",");
    List<String> aCompareScope = Arrays.asList(availableCompareScope);

    for (int i = 0; i < scopes.length; i++) {
      if(aCompareScope.contains(scopes[i])){
        //do nothing
      }else{
        return false;
      }
    }
    return true;
  }

  public static SqlSessionFactory getlSqlSessionFactory() {
    return lSqlSessionFactory;
  }

  public static void setlSqlSessionFactory(SqlSessionFactory lSqlSessionFactory) {
    pgToolsConfig.lSqlSessionFactory = lSqlSessionFactory;
  }

  public static SqlSessionFactory getrSqlSessionFactory() {
    return rSqlSessionFactory;
  }

  public static void setrSqlSessionFactory(SqlSessionFactory rSqlSessionFactory) {
    pgToolsConfig.rSqlSessionFactory = rSqlSessionFactory;
  }
}
