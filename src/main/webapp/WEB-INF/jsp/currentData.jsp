<%@page contentType="application/json; charset=UTF-8"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.levente.carelink.viewer.objects.CareLinkDataManager"%>
<%
JSONObject jsonObj = new JSONObject();
jsonObj.put("bg", CareLinkDataManager.getCurrentSGString(2));
jsonObj.put("bg_delta", CareLinkDataManager.getSGDeltaString(2));
jsonObj.put("unit", CareLinkDataManager.getUnit());
out.println(jsonObj);
out.flush();
%>