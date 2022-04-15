import java.io.BufferedInputStream; 
import java.io.BufferedReader; 
import java.io.InputStreamReader;
public class Test{
  private static int code = -1;
  public static void main(String[] args) 
  {    
    while (true){
      excuteComamnd();
      if (code == 0) break;
      System.out.println("Finish once loop\n");
      System.out.println();
      try{
        Thread.sleep(5000);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } 
  }

  public static void excuteComamnd()
  {
    Process process = null;
    BufferedInputStream bis = null;
    BufferedReader br = null;
    try { 
    process = Runtime.getRuntime().exec("bash /home/burning/master/0301/openharmony/build/prebuilts_download.sh 2>&1"); 
    bis = new BufferedInputStream(process.getInputStream()); 
    br = new BufferedReader(new InputStreamReader(bis)); 
    String line; 

    //readline 阻塞
    while (( line = br.readLine())!= null) {
      System.out.println(line); 
    } 
    process.waitFor(); 
    code = process.exitValue();

    } catch (Exception e) { 
      e.printStackTrace(); 
    } finally {
      System.out.println("err code is: " + code);
    try {
        br.close(); 
        bis.close(); 
        process.destroy();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
