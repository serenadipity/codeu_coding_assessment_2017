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

import java.io.IOException;

final class MyJSONParser implements JSONParser {

  @Override
  public JSON parse(String in) throws IOException {
    
    /*******UTILIZE THESE FUNCTION FROM MyJSON.java********/
  	//setObject(String name, JSON value);
  	//setString(String name, String value);


  	/********CONDITIONS TO CHECK FOR VALID INPUT**********/
  	//check if the first non-whitespace character is a "{"
  	//check if the next non-whitespace character is a ' " '
  	//check if once you encounter the quotes, you have a valid string (if you have a quote you must have the backslash) + must have an endquote
  	//must contain ":"
  	//after the ":" must either have quotes or brackets

  	MyJSON newObj = new MyJSON();
  	MyJSON nestedObject = new MyJSON();
  	String jsonName = "";
  	

  	//remove all whitespace for sequential parsing
  	in.replaceAll(" ","");

  	char[] temp = in.toCharArray();
  	//to go through the string sequentially and check for correct format
  	int parseIndex = 0;

  	if (parseIndex != '{' || temp[temp.length] != '}') {
  		throw new IOException("Must be valid JSON object: {}");
  	}
  	parseIndex++;
  	if (temp.length > 2) {
  		if (parseIndex != '\"') {
  			throw new IOException("Key value must be type string");
  		}
  		else {
  			parseIndex++;
  			for (int i=2; i < (temp.length + 1); i++) {
  				if (temp[i] == '\"') {
  					if (i == parseIndex) {
  						throw new IOException("key value must not be an empty string");
  					}
  					else {
  						parseIndex = i;
  					}
  					i = temp.length + 1;
  				}
  			}
  			jsonName = in.substring(2, parseIndex);
  		}
  	}
  	if (temp.length > (parseIndex + 1)) {
  		if ((temp[parseIndex + 1]) != ':') {
  			throw new IOException("Must be in valid form Key : Value");
  		}
  		else {
  			parseIndex += 2;
  		}
  	}
  	if (temp.length > (parseIndex + 1)) {
  		tempParseVal = parseIndex;
  		boolean isValidObj = false;
  		boolean isValidStr = false;
  		if (temp[parseIndex] == '{') {
  			while ((parseIndex + 1) < temp.length) {
  				if (temp[parseIndex + 1] == '}') {
  					isValidObj = true;
  					MyJSON.setObject(parse(jsonName, in.substring(tempParseVal, parseIndex)));
  				}
  				parseIndex++;
  			}
  			if (!isValidObj) {
  				throw new IOException("Must be valid object {}");
  			}
  		}
  		else if (temp[tempParseVal] == '\"') {
  			parseIndex = tempParseVal;
  			while ((parseIndex + 1) < temp.length) {
  				if (temp[parseIndex + 1] == '\"') {
  					isValidStr = true;
  					MyJSON.setString(jsonName, in.substring(tempParseVal, parseIndex));
  				}
  				parseIndex++;
  			}
  			if (!isValidStr) {
  				throw new IOException("Must be valid string ");
  			}
  		}
  	}



    return newObj;
  }
}
