// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

final class MyJSON implements JSON {

  private Map<String, JSON> jsonMap = new HashMap<>();

  private String keyName;
  private String strVal;
  private JSON nestedObj;


  @Override
  public JSON getObject(String name) {
    // TODO: implement this
    if (jsonMap.containsKey(name)) {
        return jsonMap.get(name);
    }
    return null;
  }

  @Override
  public JSON setObject(String name, JSON value) {
    if (jsonMap.containsKey(name)) {
      nestedObj.setString(name, value);
    }
    else {
      this.keyName = name;
      this.nestedObj = value;
      jsonMap.put(name, this);
    }
    return this;
  }

  //REQUIRES: value associated with given name must be a string
  @Override
  public String getString(String name) {
    if (jsonMap.containsKey(name)) {
      //differentiated between string and object when called in JSONParser
      if ((jsonMap.get(name)).strVal != "") {
        return (jsonMap.get(name)).strVal;
      }
    }
    return null;
  }

  @Override
  public JSON setString(String name, String value) {
    if (jsonMap.containsKey(name)) {
      strVal = value;
    }
    else {
      this.keyName = name;
      this.strVal = value;
      jsonMap.put(name, this);
    }
    return this;
  }

  @Override
  public void getObjects(Collection<String> names) {
    // TODO: implement this
    for (int i=0; i < jsonMap.size(); i++) {
      names.add(getObject(jsonMap.get(i)).keyName);
    }
  }

  @Override
  public void getStrings(Collection<String> names) {
    // TODO: implement this
    for (int i=0; i < jsonMap.size(); i++) {
      names.add(getString(jsonMap.get(i)).keyName);
    }

  }
}
