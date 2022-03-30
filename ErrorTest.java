import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ErrorTest{
    public static void main(String[] args)
    {
        // user ur = new user(1000,"账户");
        // new errorThread(800,"甲",ur).start();
        // new errorThread(800,"乙",ur).start();
        try {
            Process process = Runtime.getRuntime().exec("whoami");
            BufferedInputStream bis = new BufferedInputStream(process.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
            String  line;
            while (( line = br.readLine())!= null) {
                System.out.println(line);
            }
            process.waitFor();
            if (process.exitValue() != 0) {
                System.out.println("error" + process.exitValue());
            }
            br.close();
            bis.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }       
}
class user
{
    private int balance;
    private String name;
    public user(int balance,String name)
    {
        this.balance = balance;
        this.name = name;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
    @Override
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }
        if (o.getClass() == user.class && o !=null )
        {
            user ur = (user)o;
            if (ur.name.equals(name))
            {
                return true;
            }
        }
        return false;
    }   
}
class errorThread extends Thread
{
    private int money;
    //private String name;
    private user ur;
    public errorThread(int money,String name,user ur)
    {
        super(name);
        this.money = money;
        this.ur = ur;
    }
    @Override
    public void run()
    {

            if (ur.getBalance() >= money)
            {
                System.out.println(getName()+"成功取出钞票"+money);
                ur.setBalance(ur.getBalance()-money);
                System.out.println("剩下的钱:"+ur.getBalance());
                try{
                    sleep(10);//这里用了sleep()，强制线程切换
                }catch(InterruptedException ie)
                {
                    ie.printStackTrace();
                }
            }

            else
            {
                System.out.println(getName()+"余额不足，取钱失败");
            }

    }
}