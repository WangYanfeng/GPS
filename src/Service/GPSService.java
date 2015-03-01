package Service;

import java.util.ArrayList;
import java.util.List;

import Dao.GPSDataDao;
import Util.GPSDataEntity;

/**
 * WangYanfeng 作者 E-mail:wangyanfeng_2012@163.com
 * 创建时间：2015年2月4日 下午5:36:45
 * 
 * 
 */
public class GPSService {
	GPSDataDao dao=new GPSDataDao();
	List<GPSDataEntity> list=new ArrayList<GPSDataEntity>();
	public List<GPSDataEntity> getRefreshData(){
		for(int i=1;i<4;i++){
			GPSDataEntity entity=dao.queryData(i);
			if(entity!=null){
				//将度分.分格式的数据转换成十进制数据
				//calculate(entity);
				list.add(entity);
			}
		}
		return list;
	}
	//将GPS 度分.分格式转为十进制 度
	public void calculate(GPSDataEntity entity){
		//维度 ddmm.mmmm
		String la=entity.getLatitude();
		String la_char=la.substring(la.length()-1, la.length());
		
		String la_D=la.substring(0,2);
		String la_M=la.substring(2, la.length()-1);
		
		Double la_d=Double.parseDouble(la_D)+Double.parseDouble(la_M)/60;
		entity.setLatitude(la_d+la_char);
		//经度dddmm.mmmm
		String lo=entity.getLongitude();
		String lo_char=lo.substring(lo.length()-1, lo.length());
		
		String lo_D=lo.substring(0,3);
		String lo_M=lo.substring(3, lo.length()-1);
		
		double lo_d=Double.parseDouble(lo_D)+Double.parseDouble(lo_M)/60;
		entity.setLongitude(lo_d+lo_char);
	}
}
