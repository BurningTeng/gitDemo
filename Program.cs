using  System.Diagnostics;

// See https://aka.ms/new-console-template for more information
Console.WriteLine("Hello, World!");
int? ExitCode = -1;
while (true) {
   executeCommand();
   if (ExitCode == 0) break;
   Console.WriteLine("Finish one loop");
   //already exist, it will be replaced with node
   Console.WriteLine();
   await Task.Delay(5000);
}

//while (line != null && line.Contains("already exist, it will be replaced with node"))
void executeCommand() {
   ProcessStartInfo processStartInfo = 
       new ProcessStartInfo("bash", "/home/burning/release/0330/build/prebuilts_download.sh");
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


