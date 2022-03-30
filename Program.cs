using  System.Diagnostics;

// See https://aka.ms/new-console-template for more information
Console.WriteLine("Hello, World!");
while (true){
   executeCommand();
   Console.WriteLine("Finish one loop");
   Console.WriteLine();
   await Task.Delay(5000);
}

static void executeCommand() {
   ProcessStartInfo processStartInfo = 
       new ProcessStartInfo("bash", "/home/burning/release/0330/build/prebuilts_download.sh");
   processStartInfo.UseShellExecute   = true;
    processStartInfo.CreateNoWindow = false;
    Process? process = 
      Process.Start(processStartInfo);
   // Notice here. Otherwise there is an error.
   process?.WaitForExit();
   var exitCode = process?.ExitCode;
   process?.Close();
 }

