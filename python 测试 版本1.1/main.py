# -*- coding: utf-8 -*-
import urllib2
import sys
import chardet
def main(argv = None):
    if argv == None:
        argv = sys.argv

    content = sys.argv[1]
    contentdate = sys.argv[2]
    content.replace(' ','')
    contentdate.replace(' ','')
    corpid = "ww4239a83c07569367"
    corpsecret= "qmNyjQYNyZXg-5Ns3H6Xk9SmDGD9VUPwqpG_c5ZfJ0k"
    agentid = "1000003"

    url='http://localhost:8080/weixinweb/servlet.do?'
    textmod ="corpid="+corpid+"&corpsecret="+corpsecret+"&agentid="+agentid+"&content="+content+"&contentdate="+contentdate
    urlstr = url+textmod
    urlstr = urlstr.decode(sys.stdin.encoding).encode('UTF8')
    #检查当前字符串是否是中文
    #fencoding=chardet.detect(urlstr)
    #print fencoding
    req = urllib2.Request(urlstr)
    res = urllib2.urlopen(req)

if __name__ == "__main__":
    sys.exit(main())

