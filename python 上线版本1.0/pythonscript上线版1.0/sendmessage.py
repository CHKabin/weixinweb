# -*- coding: utf-8 -*-
import urllib2
import sys
def main(argv = None):
    if argv == None:
        argv = sys.argv

    content = sys.argv[1]
    contentdate = sys.argv[2]
    print content,contentdate
    content = content.decode(sys.stdin.encoding).encode('UTF8')
    content = urllib2.quote(content)
    contentdate = contentdate.decode(sys.stdin.encoding).encode('UTF8')
    contentdate = urllib2.quote(contentdate)
    #content = content.replace(' ','')
    #contentdate = contentdate.replace(' ','')
    
    #content = "dsfds"
    #contentdate = "666"
    corpid = "ww36f407763c85d8a2"
    corpsecret= "6vOzBgQtTLChZgVAfmTsdNZoG-iIEXqNsYy7oRS_ji0"
    agentid = "1000013"
    
    url='http://10.38.111.64/weixinweb/servlet.do?'
    textmod ="corpid="+corpid+"&corpsecret="+corpsecret+"&agentid="+agentid+"&content="+content+"&contentdate="+contentdate

    urlstr = url+textmod
    urlstr = urlstr.decode(sys.stdin.encoding).encode('UTF8')
    req = urllib2.Request(urlstr)
    res = urllib2.urlopen(req)

if __name__ == "__main__":
    sys.exit(main())

