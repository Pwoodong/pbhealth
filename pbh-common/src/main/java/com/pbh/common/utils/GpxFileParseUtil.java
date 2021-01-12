package com.pbh.common.utils;

import me.himanshusoni.gpxparser.GPXParser;
import me.himanshusoni.gpxparser.modal.GPX;
import me.himanshusoni.gpxparser.modal.Track;
import me.himanshusoni.gpxparser.modal.TrackSegment;
import me.himanshusoni.gpxparser.modal.Waypoint;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Package com.pbh.common.utils
 * ClassName GpxFileParseUtil.java
 * Description GPX文件解析工具类
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-15 10:50
 **/
public class GpxFileParseUtil {

    /**
     * 解析GPX文件，并返回数据
     * @param   inputStream     gpx文件流数据
     * @return  List            返回数据
     */
    public static List<Map<String,Object>> gpxParse(InputStream inputStream){
        GPXParser p = new GPXParser();
        List<Map<String,Object>> resultList = new ArrayList<>();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            GPX gpx = p.parseGPX(inputStream);
            HashSet<Track> tracksSet = gpx.getTracks();
            Iterator<Track> iterator = tracksSet.iterator();
            Map<String,Object> map = null;
            while(iterator.hasNext()){
                List<TrackSegment> list = iterator.next().getTrackSegments();
                for(TrackSegment trackSegment:list){
                    List<Waypoint> waypointList = trackSegment.getWaypoints();
                    for(Waypoint waypoint:waypointList){
                        String date = sdf.format(waypoint.getTime());
                        map = new HashMap<>(4);
                        map.put("latitude",waypoint.getLatitude());
                        map.put("longitude",waypoint.getLongitude());
                        map.put("elevation",waypoint.getElevation());
                        map.put("runningTime",date);
                        resultList.add(map);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultList;
    }

    public static void main(String[] args) {
        try{
            FileInputStream in = new FileInputStream("F:/1111.gpx");
            List<Map<String,Object>> result = gpxParse(in);
            System.out.println(result.size());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
