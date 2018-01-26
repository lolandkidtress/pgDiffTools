package com.James.Tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;


/**
 * Created by James on 2018/1/26.
 */
public class Return extends HashMap<String, Object> {
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LogManager.getLogger(Return.class.getName());

  public enum Return_Fields {
    success, code, note
  }

  //////////////////////////////// create//////////////////////////////////
  public static Return create() {
    return new Return();
  }

  public static Return create(String key, Object value) {
    return new Return().add(key, value);
  }

  public static Return create(String json) {
    Return jo = new Return();
    try {
      Map<String, Object> fromJson = JsonConvert.toObject(json, new TypeReference<HashMap<String, Object>>() {
      });
      for (Entry<String, Object> entry : fromJson.entrySet()) {
        jo.put(entry.getKey(), entry.getValue());
      }
    } catch (IOException e) {
      LOGGER.error("TReturn.create 解析 JSON 失败", e);
      return Return.FAIL(BasicCode.error);
    }
    return jo;
  }

  /////////////////////////////////////////// SUCCESS/////////////////////////

  public static Return SUCCESS(Integer code, String note) {
    Return jo = new Return();
    jo.put(Return_Fields.success.name(), true);
    jo.put(Return_Fields.code.name(), code);
    jo.put(Return_Fields.note.name(), note);
    return jo;
  }

  public static Return SUCCESS(String json) {
    Return jo = create(json);
    jo.put(Return_Fields.success.name(), true);
    return jo;
  }

  public static Return SUCCESS(BasicCode basicCode) {
    return SUCCESS(basicCode.code, basicCode.note);
  }

  ///////////////////////////////////////////////// FAIL////////////////////////////
  public static Return FAIL(Integer code, String note) {
    Return jo = new Return();
    jo.put(Return_Fields.success.name(), false);
    jo.put(Return_Fields.code.name(), code);
    jo.put(Return_Fields.note.name(), note);
    return jo;
  }

  public static Return FAIL(BasicCode basicCode) {
    return FAIL(basicCode.code, basicCode.note);
  }

  public static Return FAIL(BasicCode basicCode, Exception e) {
    return FAIL(basicCode.code, e.getLocalizedMessage());
  }

  //////////////////////////////////// GETTER SETTER///////////////////////////
  public Boolean is_success() {
    return (Boolean) this.getOrDefault(Return_Fields.success.name(), false);
  }

  public Integer get_code() {
    return (Integer) this.getOrDefault(Return_Fields.code.name(), BasicCode.error.code);
  }

  public String get_note() {
    return (String) this.getOrDefault(Return_Fields.note.name(), "");
  }

  //////////////////////// @Override/////////////////////////////////////
  @Override
  public Return put(String key, Object value) {
    super.put(key, value);
    return this;
  }

  public Return add(String key, Object value) {
    super.put(key, value);
    return this;
  }

  public String toJson() {
    try {
      return JsonConvert.toJson(this);
    } catch (Exception e) {
      LOGGER.error("json 解析失败:", e);
      return JsonConvert.toJson(Return.FAIL(BasicCode.error));
    }
  }
}
