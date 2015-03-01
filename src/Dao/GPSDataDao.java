package Dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.sql.rowset.CachedRowSet;

import Util.GPSDataEntity;

/**
 * WangYanfeng 作者 E-mail:wangyanfeng_2012@163.com
 * 创建时间：2015年2月3日 下午9:11:37
 * 
 * 存储GPS数据
 */
public class GPSDataDao {
	DBExecutor executor=new DBExecutor();
	//保存
	public void saveData(GPSDataEntity data){
		try {
			String sql="insert into gps_data (stationNo,longitude,latitude,altitude,time) values (column,'column','column',column,'column')";
			sql=sql.replaceFirst("column", ((Integer)data.getStationNo()).toString());
			sql=sql.replaceFirst("column", data.getLongitude());
			sql=sql.replaceFirst("column", data.getLatitude());
			sql=sql.replaceFirst("column", ((Double)data.getAltitude()).toString());
			Calendar calendar=data.getTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sql=sql.replaceFirst("column", format.format(calendar.getTime()));
			//System.out.println(sql);
			executor.update(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//由id查询获取最新的数据
	public GPSDataEntity queryData(int stationNo){
		GPSDataEntity entity=new GPSDataEntity();
		entity.setStationNo(stationNo);
		try {
			String sql="select * from gps_data where stationNo="+stationNo+" order by time desc limit 1";
			CachedRowSet result=executor.query(sql);
			if(result.size()==0){
				entity=null;
				return null;
			}
			while (result.next()){
				String longitude=result.getString("longitude");
				entity.setLongitude(longitude);
				String latitude=result.getString("latitude");
				entity.setLatitude(latitude);
				double altitude=result.getDouble("altitude");
				entity.setAltitude(altitude);
				String time=result.getString("time");
				//System.out.println("update"+time);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(format.parse(time));
				entity.setTime(calendar);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
}
