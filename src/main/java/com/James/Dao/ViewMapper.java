package com.James.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.James.Model.PgView;


/**
 * Created by James on 2018/1/24.
 */
public interface ViewMapper {

  @Select("\tSELECT schemaname, viewname \n" + "\tFROM pg_views \n" + "\tWHERE schemaname NOT LIKE 'pg_%' \n"
      + "\tAND schemaname <> 'information_schema'\n" + "\tORDER BY schemaname,viewname;")
  List<PgView> selectAll();

}
