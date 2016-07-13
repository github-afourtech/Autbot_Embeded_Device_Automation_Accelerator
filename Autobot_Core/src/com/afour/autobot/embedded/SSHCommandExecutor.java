package com.afour.autobot.embedded;
import java.io.IOException;
import java.io.InputStream;

import org.ini4j.Ini;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
 
 
public class SSHCommandExecutor {
 
    /**
     * @param args
     */
	public static Session session1=null;
	
	public SSHCommandExecutor(Ini config){
		SSHCommandExecutor.session1 =SSHCommandExecutor.connectSSH(config);		
	}
	
	
	public static Session connectSSH(Ini configObj){
//		Ini configObj =CommonUtilities.config;
	    String host=configObj.get("All", "ip_raspberry_pi");
	    String user= configObj.get("All", "raspberry_pi_user");
	    String password= configObj.get("All", "raspberry_pi_password");
		
        Session session=null;
        try{
	        java.util.Properties config = new java.util.Properties(); 
	        config.put("StrictHostKeyChecking", "no");
	        JSch jsch = new JSch();
	        session=jsch.getSession(user, host, 22);
	        session.setPassword(password);
	        session.setConfig(config);
	        session.connect();
	              	        
        }catch(Exception e){
            e.printStackTrace();
        }
		return session;
		
	}
		
    public boolean executeButtonPress(String command) {
        
        String command1=command;
        Session session = SSHCommandExecutor.session1;
 
        try{            
            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);
             
            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true){
              while(in.available()>0){
                int i=in.read(tmp, 0, 1024);
                if(i<0)break;
                System.out.print(new String(tmp, 0, i));
              }
              if(channel.isClosed()){
                System.out.println("exit-status: "+channel.getExitStatus());
                break;
              }
              try{Thread.sleep(1000);}catch(Exception ee){}
            }
            channel.disconnect();
            
            System.out.println("DONE");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return true;
 
    }
    
    public void ssh_disconnect(){
    	Session session = SSHCommandExecutor.session1;
    	session.disconnect();
        System.out.println("Session DONE");
    	
    }

}