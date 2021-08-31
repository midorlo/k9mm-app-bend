package com.midorlo.wolkenbruch.configuration;

public enum RestPath {

   files("/accounts"),
   accounts("/accounts");

   final String value;

   RestPath(String value) {
      this.value = "/api/v1" + value;
   }

   @Override
   public String toString() {
      return "RestPath{" +
         "value='" + value + '\'' +
         '}';
   }
}
