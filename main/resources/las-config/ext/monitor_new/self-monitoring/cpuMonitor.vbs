rem modified by bilei
Option explicit
Dim wmiSvc 
Dim wmiObj
Dim pro
Dim computer
DIm cpuIndex

computer ="."
cpuIndex = 0
Set wmiSvc = GetObject("winmgmts://"&computer&"/root/cimv2") 
Set wmiObj = wmiSvc.InstancesOf("Win32_Processor")
For   Each   pro   In   wmiObj  
      cpuIndex = cpuIndex + 1
      Wscript.Echo "cpu(" & cpuIndex &")=" &   _ 
      pro.LoadPercentage 
Next   



