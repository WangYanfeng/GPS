package Util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import Controller.ServerController;
import Dao.GPSDataDao;

/**
 * WangYanfeng 作者 E-mail:wangyanfeng_2012@163.com
 * 创建时间：2015年2月3日 下午3:00:05
 * 
 * 进行udp通信
 * 服务器端接受从属通信车的经纬度
 */
public class UDPReceive implements Runnable {
	//
	public final static int DEFAULT_PORT = 2323;
	//
	public final static int MAX_PACKET_SIZE=65507;
	
	DatagramSocket socket;
	DatagramPacket packet;
	public void receivePacket(){
		
	}

	@Override
	public void run() {
		
		byte[] buffer=new byte[MAX_PACKET_SIZE];
		try {
			socket = new DatagramSocket(DEFAULT_PORT);
			packet=new DatagramPacket(buffer,buffer.length);
			System.out.println("Run Server...");
			ServerController.isServerOn=true;
			
			while(true){
				try {
					if(!socket.isClosed()){
						socket.receive(packet);
						byte[] result=packet.getData();
						System.out.println(new String(result));
						
						//解析数据
						GPSDataEntity entity=GPSDataParse.parse(result);
						System.out.println("维度:"+entity.getLatitude()+"经度:"+entity.getLongitude()+"海拔:"+entity.getAltitude());
						//存数据库
						GPSDataDao dao=new GPSDataDao();
						dao.saveData(entity);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Exception - Server has closed!");
				}
			}
		} catch (SocketException e1) {
			System.out.println("Close Server");
			socket.close();
			Thread.currentThread().interrupt();
			ServerController.isServerOn=false;
			return;
		}
	}
	
}
