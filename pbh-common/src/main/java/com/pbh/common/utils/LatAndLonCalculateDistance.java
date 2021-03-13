package com.pbh.common.utils;

/**
 * Package com.pbh.common.utils
 * ClassName LatAndLonCalculateDistance.java
 * Description 根据两点的经纬度计算之间的距离
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-07-08 14:05
 **/
public class LatAndLonCalculateDistance {

    public static final double DEGREES_TO_RADIANS = Math.PI / 180.0;
    public static final double RADIANS_TO_DEGREES = 180.0 / Math.PI;
    /** 地球半径 */
    public static final double EARTH_MEAN_RADIUS_KM = 6371.009;
    /** 地球直径 */
    private static final double EARTH_MEAN_DIAMETER = EARTH_MEAN_RADIUS_KM * 2;

    /***
     * 距离半径计算方式
     * @param latCenterRad  中心点纬度
     * @param lonCenterRad  中心点经度
     * @param latVal        目标纬度
     * @param lonVal        目标经度
     * @return double       两坐标的距离 单位千米
     */
    public static double getDistance(double latCenterRad, double lonCenterRad, double latVal, double lonVal) {
        /** 计算经纬度 */
        double latRad = latVal * DEGREES_TO_RADIANS;
        double lonRad = lonVal * DEGREES_TO_RADIANS;
        /** 计算经纬度的差 */
        double diffX = latCenterRad * DEGREES_TO_RADIANS - latRad;
        double diffY = lonCenterRad * DEGREES_TO_RADIANS - lonRad;
        /** 计算正弦和余弦 */
        double hSinX = Math.sin(diffX * 0.5);
        double hSinY = Math.sin(diffY * 0.5);
        double latCenterRad_cos = Math.cos(latCenterRad * DEGREES_TO_RADIANS);
        double h = hSinX * hSinX + (latCenterRad_cos * Math.cos(latRad) * hSinY * hSinY);
        return (EARTH_MEAN_DIAMETER * Math.atan2(Math.sqrt(h), Math.sqrt(1 - h)));
    }

//    public static void main(String[] args) {
//        double latCenterRad = 39.96152114868164;
//        double lonCenterRad = 116.2874526977539;
//        double latVal = 39.96153259277344;
//        double lonVal = 116.28746032714844;
//        double distance = getDistance(latCenterRad,lonCenterRad,latVal,lonVal);
//        System.out.println(distance);
//    }

}
