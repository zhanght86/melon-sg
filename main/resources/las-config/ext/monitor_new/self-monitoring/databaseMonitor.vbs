Set objFSO = CreateObject("Scripting.FileSystemObject")
Set objFolder = objFSO.GetFolder(Wscript.Arguments.Item(0))
Wscript.Echo "databaseSpace=" &objFolder.Size

strComputer = "."
Set objWMIService = GetObject("winmgmts:" _
    & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colDisks = objWMIService.ExecQuery _
    ("Select * from Win32_LogicalDisk Where DeviceID = '" & Wscript.Arguments.Item(1) & "'")
For Each objDisk in colDisks
	Wscript.Echo "DeviceID=" &objDisk.DeviceID
	Wscript.Echo "intTotalSpace=" &objDisk.Size
    Wscript.Echo "intFreeSpace=" &objDisk.FreeSpace
Next