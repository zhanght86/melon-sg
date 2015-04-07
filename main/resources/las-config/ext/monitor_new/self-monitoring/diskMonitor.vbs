Const HARD_DISK = 3
strComputer = "."
Set objWMIService = GetObject("winmgmts:" _
    & "{impersonationLevel=impersonate}!\\" & strComputer & "\root\cimv2")
Set colDisks = objWMIService.ExecQuery _
    ("Select * from Win32_LogicalDisk Where DriveType = " & HARD_DISK & "")
diskIndex = 1
For Each objDisk in colDisks
    Wscript.Echo "DeviceID(" & diskIndex &")=" &objDisk.DeviceID
    Wscript.Echo "VolumeName(" & diskIndex &")=" &objDisk.VolumeName
    Wscript.Echo "intTotalSpace(" & diskIndex &")=" &objDisk.Size
    Wscript.Echo "intFreeSpace(" & diskIndex &")=" &objDisk.FreeSpace
    diskIndex = diskIndex + 1
Next