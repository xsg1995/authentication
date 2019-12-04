# 接口鉴权功能

## 需求描述
* 调用方进行接口请求时，将URL、AppID、密码、时间戳拼接在一起，通过加密算法生成token，并将token、AppID、时间戳拼接在URL中，一并发送到服务端，例：http://localhost:8080/server?token=xxx&app_id=xxxx&time_stamp=9632587；
* 服务端接收到调用方的接口请求之后，从请求中解析出token、AppID、时间戳；
* 服务端首先将时间戳跟当前时间进行比较，检查token是否在失效窗口内，如果超时则拒绝接口调用请求；
* 如果token验证没有过期，服务端再从自己的存储中，取出AppID对应的密码，通过同样的算法生成token，然后与传递过来的token进行比较，如果一致，则鉴权成功；

### 运行Demo在test目录下