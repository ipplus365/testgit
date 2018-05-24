package com.ipplus360.entity;

public class MultiArea {
	
	/**
	 * multiarea={"multiarea": [
     *                     	{"lat": "22.290268", "lng": "113.946784", "radius": "0.6269", "prov": "香港特别行政区", "district": "离岛区", "city": "新界"},
     *                      {"lat": "22.310980", "lng": "114.213855", "radius": "11.54042", "prov": "香港特别行政区", "district": "观塘区", "city": "九龙"},
     *                      {"lat": "22.365534", "lng": "114.091145", "radius": "7.08376", "prov": "香港特别行政区", "district": "荃湾区", "city": "新界"}
     *                  ]
     *  }
		*]
	 */
	
	/*纬度*/
    private String w;
    /*经度*/
    private String j;
    /*radius*/
    private String r;
    /*省*/
    private String p;
    /*市*/
    private String c;
    /*区县*/
    private String d;
    public String getW() {
        return w;
    }
    public void setW(String w) {
        this.w = w;
    }
    public String getJ() {
        return j;
    }
    public void setJ(String j) {
        this.j = j;
    }
    public String getR() {
        return r;
    }
    public void setR(String r) {
        this.r = r;
    }
    public String getP() {
        return p;
    }
    public void setP(String p) {
        this.p = p;
    }
    public String getC() {
        return c;
    }
    public void setC(String c) {
        this.c = c;
    }
    public String getD() {
        return d;
    }
    public void setD(String d) {
        this.d = d;
    }
    @Override
    public String toString() {
        return "MultiArea [lat=" + w + ", lng=" + j + ", radius=" + r + ", prov=" + p + ", city=" + c + ", district=" + d + "]";
    }

}
