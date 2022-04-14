using  System.Diagnostics;
using System.IO;

// See https://aka.ms/new-console-template for more information
Console.WriteLine("Hello, World!");

int? ExitCode = -1;
Console.Write("Please input prebuilts_download.sh path: ");
string? ShPath = Console.ReadLine()?.Trim();
//dotnet-sdk-6.0
while (true) {
   ExecuteCommand();
   if (ExitCode == 0) break;
   Console.WriteLine("Finish one loop");
   Console.WriteLine();
   await Task.Delay(5000);

}



//while (line != null && line.Contains("already exist, it will be replaced with node"))

void ExecuteCommand() {

   if (ShPath == null){

      Console.WriteLine("PLease input valid prebuilts_download.sh path");

      return;

   }

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

