<%@page contentType="application/json; charset=UTF-8"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.levente.carelink.viewer.objects.CareLinkDataManager"%>
<%
JSONObject jsonObj = new JSONObject();
jsonObj.put("bg", CareLinkDataManager.FormatData(CareLinkDataManager.getCurrentSG(), 2));
jsonObj.put("bg_delta", CareLinkDataManager.FormatData(CareLinkDataManager.getSGDelta(),2));
jsonObj.put("IOB", CareLinkDataManager.FormatData(CareLinkDataManager.getCurrentIOB(),2));
jsonObj.put("reservoir_ammount", CareLinkDataManager.FormatData(CareLinkDataManager.getInsulinInPump(), 2));
jsonObj.put("unit", CareLinkDataManager.getUnit());
out.println(jsonObj);
out.flush();
%>