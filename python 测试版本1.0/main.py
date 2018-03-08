# -*- coding: utf-8 -*-
import urllib2
import sys
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
    req = urllib2.Request(url+textmod)
    res = urllib2.urlopen(req)

if __name__ == "__main__":
    sys.exit(main())

