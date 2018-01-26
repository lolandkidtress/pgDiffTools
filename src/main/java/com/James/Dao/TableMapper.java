package com.James.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.James.Model.PgTable;


/**
 * Created by James on 2018/1/24.
 */
public interface TableMapper {
  @Select("SELECT n.nspname as \"Schema\",\n" + "\t  c.relname as \"TabName\"\n" + "\tFROM pg_catalog.pg_class c\n"
      + "\t     LEFT JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace\n" + "\tWHERE c.relkind ='r'\n"
      + "\t      AND n.nspname <> 'pg_catalog'\n" + "\t      AND n.nspname <> 'information_schema'\n"
      + "\t      AND n.nspname !~ '^pg_toast'\n" + "\t  AND pg_catalog.pg_table_is_visible(c.oid)\n" + "\tORDER BY 1,2;")
  List<PgTable> selectAll();


}
