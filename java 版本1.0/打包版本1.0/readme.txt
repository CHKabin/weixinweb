ini文件夹下是配置文件主要配置以下信息：
corpid：对应 “我的企业”->“CorpID”
corpsecret:对应 “企业应用下”->“自己新建应用下”->“Secret”
agentid:对应 “企业应用下”->“自己新建应用下”->”AgentId“


使用：在Hostmonitor监控项目的报警里面进行简单的配置，配置信息如下：
"C:\Users\Administrator\Desktop\test\start.bat" "%TestName% is dead" "%DateTime%"

C:\Users\Administrator\Desktop\test\start.bat 是调用程序存放的路径可以自行更改

%TestName%和%DateTime% 这个是Hostmonitor自己提供的可选参数可以自行更改