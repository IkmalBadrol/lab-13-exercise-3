package exercise3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverTextApplication {
	
	public static void main(String[] args) throws IOException {
		
	serverTextFrame  serverTFrame = new serverTextFrame();	
	serverTFrame.setVisible(true);
	
	// Binding to a port 
	int portNo = 4080;
	ServerSocket serverSocket = new ServerSocket(portNo);
	

	int totalRequest = 0;
	
	while(true) {
		
		// Message to indicate server is alive
		serverTFrame.updateServerStatus(false);
		
		//Accept client request for connection
		Socket clientSocket = serverSocket.accept();
		
		//Generate input text
		String text = "Distributed Aplication Development ";
		
		// Count the number of words in the text
        int wordCount = countWords(text);
        
        // Convert the word count to a string
        String wordCountStr = Integer.toString(wordCount);
		
		// Create stream to write data on the network
		DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
		
		//Send text to be counted back to the client
		dos.writeBytes(wordCountStr + "\n");
		
		//Close socket
		clientSocket.close();
		
		
		//update request
		serverTFrame.updateRequestStatus("Data sent to client : "+text);
		
		serverTFrame.updateRequestStatus("Accepted connection to from the client. Total request = " +totalRequest);
		
	}
	
	}
	
	 //Count the number of words in a string
    private static int countWords(String text) {
    	
    	if (text == null || text.isEmpty()) {
            return 0;
        }
    	
    	//Split on whitespace characters
        String[] words = text.split("\\s+"); 
        
        //Return the number of words
        return words.length;
    }

}
