package com.m800.itunes.module.apiParams;

import com.android.volley.Request;
import com.m800.itunes.module.volley.VolleyMultipartRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class InParamsBase {
    String subPath;
    protected String os = "android";
    protected String token;
    int method = Request.Method.POST;


    List<VolleyMultipartRequest.DataPart> dataPartList;

    public InParamsBase(String group, String name, String token) {
        this.subPath = group + "/" + name;
        this.token = token;
    }

    public InParamsBase(String subPath, String token) {
        this.subPath = subPath;
        this.token = token;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public void setSubPath(String subPath){
        this.subPath = subPath;
    }
    public String getSubPath(){
        return subPath;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    //-------------------------------------------------------------------
    public List<VolleyMultipartRequest.DataPart> getDataPartList() {
        return dataPartList;
    }

    public void setDataPartList(List<VolleyMultipartRequest.DataPart> dataPartList) {
        this.dataPartList = dataPartList;
    }

    public abstract Map<String, String> renderMap() throws Exception;

    private boolean hasByteData(){
        return dataPartList != null && dataPartList.size() > 0;
    }

    //public abstract Map<String, VolleyMultipartRequest.DataPart> renderDataPartMap() throws Exception;
    public Map<String, VolleyMultipartRequest.DataPart> renderDataPartMap() throws Exception {
        Map<String, VolleyMultipartRequest.DataPart> ret = null;
        if(hasByteData()) {
            ret = new HashMap<String, VolleyMultipartRequest.DataPart>();
            for(VolleyMultipartRequest.DataPart item : dataPartList)
                ret.put(item.getKey(), item);
        }
        return ret;
    }

    public void addDataPart(VolleyMultipartRequest.DataPart dataPart){
        if(dataPartList == null) dataPartList = new ArrayList<VolleyMultipartRequest.DataPart>();
        dataPartList.add(dataPart);
    }

    public void removeDataPart(VolleyMultipartRequest.DataPart dataPart){
        if(dataPartList != null){
            dataPartList.remove(dataPart);
        }
    }
}
