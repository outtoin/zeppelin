/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.zeppelin.rinterpreter;

import org.apache.zeppelin.interpreter.*;
import org.apache.zeppelin.interpreter.thrift.InterpreterCompletion;
import org.apache.zeppelin.scheduler.Scheduler;

import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * KnitR is a simple wrapper around KnitRInterpreter to handle that Zeppelin prefers
 * to load interpreters through classes defined in Java with static methods that run
 * when the class is loaded.
 *
 */
public class KnitR extends Interpreter implements WrappedInterpreter {
  KnitRInterpreter intp;

  public KnitR(Properties property, Boolean startSpark) {
    super(property);
    intp = new KnitRInterpreter(property, startSpark);
  }
  public KnitR(Properties property) {
    this(property, true);
  }

  public KnitR() {
    this(new Properties());
  }

  @Override
  public void open() {
    intp.open();
  }

  @Override
  public void close() {
    intp.close();
  }

  @Override
  public InterpreterResult interpret(String s, InterpreterContext interpreterContext) {
    return intp.interpret(s, interpreterContext);
  }

  @Override
  public void cancel(InterpreterContext interpreterContext) {
    intp.cancel(interpreterContext);
  }

  @Override
  public FormType getFormType() {
    return intp.getFormType();
  }

  @Override
  public int getProgress(InterpreterContext interpreterContext) {
    return intp.getProgress(interpreterContext);
  }

  @Override
  public List<InterpreterCompletion> completion(String s, int i,
      InterpreterContext interpreterContext) {
    List completion = intp.completion(s, i, interpreterContext);
    return completion;
  }

  @Override
  public Interpreter getInnerInterpreter() {
    return intp;
  }

  @Override
  public Scheduler getScheduler() {
    return intp.getScheduler();
  }

  @Override
  public void setProperty(Properties property) {
    super.setProperty(property);
    intp.setProperty(property);
  }

  @Override
  public Properties getProperty() {
    return intp.getProperty();
  }

  @Override
  public String getProperty(String key) {
    return intp.getProperty(key);
  }

  @Override
  public void setInterpreterGroup(InterpreterGroup interpreterGroup) {
    super.setInterpreterGroup(interpreterGroup);
    intp.setInterpreterGroup(interpreterGroup);
  }

  @Override
  public InterpreterGroup getInterpreterGroup() {
    return intp.getInterpreterGroup();
  }

  @Override
  public void setClassloaderUrls(URL[] classloaderUrls) {
    intp.setClassloaderUrls(classloaderUrls);
  }

  @Override
  public URL[] getClassloaderUrls() {
    return intp.getClassloaderUrls();
  }
}
