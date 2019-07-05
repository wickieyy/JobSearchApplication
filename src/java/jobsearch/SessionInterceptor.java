/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobsearch;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SessionInterceptor implements Interceptor {

 @Override
 public void destroy() {
  // TODO Auto-generated method stub

 }

 @Override
 public void init() {
  // TODO Auto-generated method stub

 }

 @Override
 public String intercept(ActionInvocation invocation) throws Exception {
  Map<String, Object> session = invocation.getInvocationContext()
    .getSession();

  String userName = (String) session.get("employeeEmail");

  if (userName == null || !userName.equals("admin")) {
   return "loginUser";
  }
  return invocation.invoke();
 }

}