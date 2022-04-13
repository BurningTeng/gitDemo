    using  System.Diagnostics;
using ThirdParty.Json.LitJson;
using System.IO;

// See https://aka.ms/new-console-template for more information
Console.WriteLine("Hello, World!");
int? ExitCode = -1;
string ShPath;
GetShPath();
//dotnet-sdk-6.0
while (true) {
   ExecuteCommand();
   if (ExitCode == 0) break;
   Console.WriteLine("Finish one loop");
   //already exist, it will be replaced with node
   Console.WriteLine();
   await Task.Delay(5000);
}

void GetShPath()
{
   var folder = Environment.SpecialFolder.LocalApplicationData;
   var path = Environment.GetFolderPath(folder);
   var config = System.IO.Path.Join(path, "config.txt");
   ShPath =  File.ReadAllText(config).Trim();
   Console.WriteLine(ShPath);
}

//while (line != null && line.Contains("already exist, it will be replaced with node"))
void ExecuteCommand() {
   ProcessStartInfo processStartInfo = 
       new ProcessStartInfo("bash", ShPath);
   processStartInfo.UseShellExecute   = true;
   processStartInfo.CreateNoWindow = true;
   //zprocessStartInfo.RedirectStandardOutput = false;
   Process? process = Process.Start(processStartInfo);
   // Notice here. Otherwise there is an error.
   process?.WaitForExit();
   ExitCode = process?.ExitCode;
   Console.WriteLine("Err code:" + ExitCode);
   process?.Close();
 }