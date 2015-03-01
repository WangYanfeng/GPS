# GPS
模仿实现雷达扫描，使用UDP实现通信，通信格式：1B通信站编号+GPGGA格式的GPS数据。udp服务器监听2323端口，等待客户端发送数据（server包UDPClinentTest发送测试数据）。基站编号默认为0，经纬度坐标保存在数据库中。udp服务器端将从站编号及经纬度解析后存入数据库。使用html5 的canvas标签实现扫描界面。每隔1s更新一次数据，并将相对位置显示在界面中，同时计算从站距离基站位置和当前速度。界面长1000px,高600px。（另：计算GPS坐标距离公式是参考网上公式，可能有误，请见谅）

测试使用：
1.将项目导入后部署到tomcat上。msql中创建名为gps的数据，并导入gps.sql（注意MySQL密码为123456，若不同请将Java代码的密码修改）
2.启动UDP服务器端：http://localhost:8080/GPS/udpServer.jsp
3.访问主界面http://localhost:8080/GPS/
4.启动测试客户端 /GPS/src/Server/UDPClientTest1.java
5.注意：测试数据只发送400条数据，如果再次测试，请将数据库中的除第一条数据外的其他数据删除，再重新部署测试。
