package Util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * WangYanfeng 作者 E-mail:wangyanfeng_2012@163.com
 * 创建时间：2015年2月3日 下午7:01:45
 * 
 * GPS数据实体类
 */
public class GPSDataEntity {
	private int stationNo;
	//经度 格式为dddmm.mmmm 度分.分
	private String longitude;
	//维度 格式为ddmm.mmmm 度分.分
	private String latitude;
	//海拔
	private double altitude;
	//时间
	private Calendar time;
	
	public int getStationNo() {
		return stationNo;
	}
	public void setStationNo(int stationNo) {
		this.stationNo = stationNo;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar time) {
		this.time = time;
	}
}
