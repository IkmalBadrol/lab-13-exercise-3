package exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class clientTextApplication {

	public static void main(String[] args) 
			throws UnknownHostException, IOException {
		
		// Launch client-side frame
		clientTextFrame clientTextFrame = new clientTextFrame();
		clientTextFrame.setVisible(true);
		
		// Connect to the server @ localhost, port 4080
		Socket socket = new Socket(InetAddress.getLocalHost(), 4080);
		
		// Update the status of the connection
		clientTextFrame.updateConnectionStatus(socket.isConnected());
		
		 // Read word count from network
        int wordCount = readWordCount(socket);
        
        //Display the word count
        clientTextFrame.updateServerDate("Word Count: " + wordCount);
        
        
		socket.close();

	}
	
	
	 //Read the word count sent by the server
	private static int readWordCount(Socket socket) throws IOException {
		
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    String wordCountStr = bufferedReader.readLine();
	    
	    //Convert text received from server to integer
	    int wordCount = Integer.parseInt(wordCountStr);
	    
	    bufferedReader.close();
	    
	    //Return the value of word count
	    return wordCount;
	}




}
