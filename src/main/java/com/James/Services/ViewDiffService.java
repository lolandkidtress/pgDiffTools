package com.James.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.James.Configure.pgToolsConfig;
import com.James.Dao.ViewMapper;
import com.James.Model.PgView;
import com.James.Model.ViewDiffResult;


/**
 * Created by James on 2018/1/24.
 */
@Service
public class ViewDiffService {

  public List<ViewDiffResult> getDiff() {
    SqlSession lSession = pgToolsConfig.getlSqlSessionFactory().openSession();
    SqlSession rSession = pgToolsConfig.getrSqlSessionFactory().openSession();

    List<PgView> lPgViews = lSession.getMapper(ViewMapper.class).selectAll();
    List<PgView> rPgViews = rSession.getMapper(ViewMapper.class).selectAll();

    //key:viewname
    ConcurrentHashMap<String,ViewDiffResult> diffResultsMap = new ConcurrentHashMap<>();

    lPgViews.stream().forEach(pgView->{
      ViewDiffResult viewDiffResult = new ViewDiffResult(pgView.getViewname());
      viewDiffResult.setlExist(true);
      viewDiffResult.addlSchemaInfo(pgView.getSchemaname());
      diffResultsMap.put(pgView.getViewname(), viewDiffResult);
    });

    rPgViews.stream().forEach(pgView->{
      //没有就新增进map
      ViewDiffResult viewDiffResult = diffResultsMap.getOrDefault(pgView.getViewname(),new ViewDiffResult(pgView.getViewname()));
      viewDiffResult.addrSchemaInfo(pgView.getSchemaname());
      viewDiffResult.setrExist(true);

    });

    List<ViewDiffResult> diffResultsList = new ArrayList<>();

    diffResultsMap.values().forEach(viewDiffResult->{
      if(viewDiffResult.checkDiff()){
        diffResultsList.add(viewDiffResult);
      }

    });

    return diffResultsList;
  }
}
