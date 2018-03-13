set svb="C:\Program Files (x86)\Altova\StyleVision2017\StyleVision"
set spsdir=D:\projekte\de.ivu.wahl.wus.reportgenerator\src\test\resources\de\ivu\wahl\wus\reportgen\sps
set tpldir=D:\projekte\de.ivu.wahl.wus.reportgenerator\src\main\resources\de\ivu\wahl\wus\reportgen\templates

@rem for %%G in (N11 O3 ... not in use) do call %svb% -v %spsdir%\%%G-eml.sps -OutXSLT=%tpldir%\%%G-to-html.xslt

rem MTP/P0/P1
@rem for %%G in (             osv0-1                                              ) do call %svb% -v %spsdir%\%%G-eml.sps -OutXSLRTF=%tpldir%\%%G-to-RTF.xslt
@rem for %%G in (MTP1 MTP1mac osv0-1 H1 osv1-1 H3-1 H3-2 H4 H9 I10 Y13 Y35 osv1-2 ) do call %svb% -v %spsdir%\%%G-eml.sps -OutXSLFO=%tpldir%\%%G-to-FO.xslt

rem P2-3
@rem for %%G in (I1 osv2-1 I4 osv2-6 osv2-7 osv3-2        osv3-4        osv3-7        ) do call %svb% -v %spsdir%\%%G-eml.sps -OutXSLRTF=%tpldir%\%%G-to-RTF.xslt
@rem for %%G in (I1        I4 osv2-6 osv2-7        osv3-3 osv3-4 osv3-6        osv3-9 ) do call %svb% -v %spsdir%\%%G-eml.sps -OutXSLFO=%tpldir%\%%G-to-FO.xslt

rem P4/P5
@rem for %%G in (N10-1 I osv4-8 N11 II osv4-7 O3        osv4-2 osv4-4 Wrr83 P22-1 P22-2 osv5-1 osv5-2 osv5-3 ) do call %svb% -v %spsdir%\%%G-eml.sps -OutXSLRTF=%tpldir%\%%G-to-RTF.xslt
@rem for %%G in (               N11 II osv4-7 O3 osv4-1 osv4-2 osv4-4 Wrr83 P22-1 P22-2               osv5-3 ) do call %svb% -v %spsdir%\%%G-eml.sps -OutXSLFO=%tpldir%\%%G-to-FO.xslt

rem EK
@rem for %%G in (osv4-5 T11 U16 ) do call %svb% -v %spsdir%\%%G-eml.sps -OutXSLRTF=%tpldir%\%%G-to-RTF.xslt
@rem for %%G in (osv4-5 T11 U16 ) do call %svb% -v %spsdir%\%%G-eml.sps -OutXSLFO=%tpldir%\%%G-to-FO.xslt

for %%G in (I osv4-8 II osv4-7) do call %svb% -v %spsdir%\%%G-eml.sps -OutXSLRTF=%tpldir%\%%G-to-RTF.xslt
for %%G in (         II osv4-7) do call %svb% -v %spsdir%\%%G-eml.sps -OutXSLFO=%tpldir%\%%G-to-FO.xslt
