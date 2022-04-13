import java.io.BufferedInputStream; 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
public class Test{ 
  public static void main(String[] args) 
  { 
  // user ur = new user(1000,"账户"); 
  // new errorThread(800,"甲",ur).start(); 
  // new errorThread(800,"乙",ur).start(); 
    while (true){
      try { 
        Process process = Runtime.getRuntime().exec("bash /home/xich/ohos/master/0413/build/prebuilts_download.sh"); 
        BufferedInputStream bis = new BufferedInputStream(process.getInputStream()); 
        BufferedReader br = new BufferedReader(new InputStreamReader(bis)); 
        String line; 
        while (( line = br.readLine())!= null) { 
          System.out.println(line); 
        } 
        process.waitFor(); 
        if (process.exitValue() != 0) { 
          System.out.println("error" + process.exitValue()); 
        } 
        if (process.exitValue() == 0) {
          br.close(); 
          bis.close();
          process.destroy();
          break;
        }
        br.close(); 
        bis.close(); 
        System.out.println("Finish once loop\n");
        Thread.sleep(5000);
        process.destroy();
      } catch (Exception e) { 
        e.printStackTrace(); 
      }
    } 
  }
}
