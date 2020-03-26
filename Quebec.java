package server;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.xml.ws.Endpoint;

import webimpl.DemsImplementation;


public class Quebec {
	
	public static DemsImplementation implementation;
	public static HashMap<String, String> eventList = new HashMap<String, String>();
	public static HashMap<String, Integer> a = new HashMap<String, Integer>();
	public static HashMap<String, Integer> b = new HashMap<String, Integer>();
	public static HashMap<String, Integer> c = new HashMap<String, Integer>();
	public static HashMap<String, ArrayList<String>> Muser1 = new HashMap<String, ArrayList<String>>();

	public static void main(String[] args) throws FileNotFoundException {
		/*String location="G:\\workspace\\6231_project\\src\\logger\\clientlog\\Quebec.txt";;
		PrintStream o=new PrintStream(new File(location));
		System.setOut(o);*/
		implementation = new DemsImplementation();
		Endpoint endpoint = Endpoint.publish("http://localhost:8081/que", implementation);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date(); 

		// get reference to rootpoa &amp; activate

		
		

			eventList.put("Conference", "a");
			eventList.put("TradeShow", "b");
			eventList.put("Seminar", "c");

			System.out.println("Quebec Server ready and waiting ...");

			 DatagramSocket MSocket = null;
	            try {
	                MSocket = new DatagramSocket(6001);
	                // create socket at agreed port
	                byte[] buffer = new byte[1000];
	 
	                System.out.println("Quebec UDP Server started");
	                while (true) {
	                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
	                MSocket.receive(request);
	                Quebec m = new Quebec();
	            
	                String fullid = new String(request.getData());
	                if(fullid.substring(0, 10).equalsIgnoreCase("checkCount")){
	                    int count=m.getOccurances(fullid.substring(10, 18));
	                    String mcount=String.valueOf(count);
	                    byte[] msg = mcount.getBytes();
	                    DatagramPacket reply = new DatagramPacket(msg, msg.length,
	                            request.getAddress(), request.getPort());
	                    MSocket.send(reply);
	                }
	                if(fullid.substring(0, 7).equalsIgnoreCase("Userdat")){
						String customerID=(fullid.substring(7, 15));
						String tempo = m.getUserData(customerID);
						byte[] msg = tempo.getBytes();
						DatagramPacket reply = new DatagramPacket(msg, msg.length,
								request.getAddress(), request.getPort());
						MSocket.send(reply);
					}
	                String var = fullid.substring(0, 1);
	                String var2 = fullid.substring(1, 8);
	                
	                if (var.equalsIgnoreCase("a")  ) {
	                    if(var2.equalsIgnoreCase("display")){
	                    String done = m.display(var);
	                    byte[] msg = done.getBytes();
	                    DatagramPacket reply = new DatagramPacket(msg, msg.length,
	                            request.getAddress(), request.getPort());
	                    MSocket.send(reply);
	                    }
	                    else if(var2.equalsIgnoreCase("booked ")){
	                        String customerID = fullid.substring( 8,16);
	                        String eventID = fullid.substring(16, 26);
	                        if (m.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
	                                "Available ")) {
	                            String r = m.bookedEvent(var,eventID, customerID);
	                            byte[] msg = r.getBytes();
	                            DatagramPacket reply = new DatagramPacket(msg, msg.length,
	                                    request.getAddress(), request.getPort());
	                            MSocket.send(reply);
	                        } else {
	                            String r = "No such event is available";
	                            byte[] msg = r.getBytes();
	                            DatagramPacket reply = new DatagramPacket(msg, msg.length,
	                                    request.getAddress(), request.getPort());
	                            MSocket.send(reply);
	                            
	                        }
	                    }else if(var2.equalsIgnoreCase("cancel ")){
							String customerID = fullid.substring( 8,16);
							String eventID = fullid.substring(16, 26);
							if (m.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
									"available ")) {
								if (m.checkUserBooking(eventID, customerID)) {
									String c = m.canceledEvent(var,eventID, customerID);
									byte[] msg = c.getBytes();
									DatagramPacket reply = new DatagramPacket(msg, msg.length,
											request.getAddress(), request.getPort());
									MSocket.send(reply);
								} else{
									String c = "EventId not registered for customerId";
									byte[] msg = c.getBytes();
									DatagramPacket reply = new DatagramPacket(msg, msg.length,
											request.getAddress(), request.getPort());
									MSocket.send(reply);
								}
									
							} else {
								
								String c = "No such eventid is available in this eventType";
								byte[] msg = c.getBytes();
								DatagramPacket reply = new DatagramPacket(msg, msg.length,
										request.getAddress(), request.getPort());
								MSocket.send(reply);
							}
						}
	                
	                } else if (var.equalsIgnoreCase("b")  ) {
	                    if(var2.equalsIgnoreCase("display")){
	                        String done = m.display(var);
	                        byte[] msg = done.getBytes();
	                        DatagramPacket reply = new DatagramPacket(msg, msg.length,
	                                request.getAddress(), request.getPort());
	                        MSocket.send(reply);
	                        }
	                    else if(var2.equalsIgnoreCase("booked ")){
	                        String customerID = fullid.substring( 8,16);
	                        String eventID = fullid.substring(16, 26);
	                        if (m.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
	                                "Available ")) {
	                            String r = m.bookedEvent(var,eventID, customerID);
	                            byte[] msg = r.getBytes();
	                            DatagramPacket reply = new DatagramPacket(msg, msg.length,
	                                    request.getAddress(), request.getPort());
	                            MSocket.send(reply);
	                        } else {
	                            String r = "No such event is available";
	                            byte[] msg = r.getBytes();
	                            DatagramPacket reply = new DatagramPacket(msg, msg.length,
	                                    request.getAddress(), request.getPort());
	                            MSocket.send(reply);
	                            
	                        }
	                    }else if(var2.equalsIgnoreCase("cancel ")){
							String customerID = fullid.substring( 8,16);
							String eventID = fullid.substring(16, 26);
							if (m.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
									"available ")) {
								if (m.checkUserBooking(eventID, customerID)) {
									String c = m.canceledEvent(var,eventID, customerID);
									byte[] msg = c.getBytes();
									DatagramPacket reply = new DatagramPacket(msg, msg.length,
											request.getAddress(), request.getPort());
									MSocket.send(reply);
								} else{
									String c = "EventId not registered for customerId";
									byte[] msg = c.getBytes();
									DatagramPacket reply = new DatagramPacket(msg, msg.length,
											request.getAddress(), request.getPort());
									MSocket.send(reply);
								}
									
							} else {
								
								String c = "No such eventid is available in this eventType";
								byte[] msg = c.getBytes();
								DatagramPacket reply = new DatagramPacket(msg, msg.length,
										request.getAddress(), request.getPort());
								MSocket.send(reply);
							}
						}
	                } else if (var.equalsIgnoreCase("c") ) {
	                    if(var2.equalsIgnoreCase("display")){
	                        String done = m.display(var);
	                        byte[] msg = done.getBytes();
	                        DatagramPacket reply = new DatagramPacket(msg, msg.length,
	                                request.getAddress(), request.getPort());
	                        MSocket.send(reply);
	                        }
	                    else if(var2.equalsIgnoreCase("booked ")){
	                        String customerID = fullid.substring( 8,16);
	                        String eventID = fullid.substring(16, 26);
	                        if (m.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
	                                "Available ")) {
	                            String r = m.bookedEvent(var,eventID, customerID);
	                            byte[] msg = r.getBytes();
	                            DatagramPacket reply = new DatagramPacket(msg, msg.length,
	                                    request.getAddress(), request.getPort());
	                            MSocket.send(reply);
	                        } else {
	                            String r = "No such event is available";
	                            byte[] msg = r.getBytes();
	                            DatagramPacket reply = new DatagramPacket(msg, msg.length,
	                                    request.getAddress(), request.getPort());
	                            MSocket.send(reply);
	                            
	                        }
	                    }else if(var2.equalsIgnoreCase("cancel ")){
							String customerID = fullid.substring( 8,16);
							String eventID = fullid.substring(16, 26);
							if (m.checkAvailabilityOfEvent(var, eventID).equalsIgnoreCase(
									"available ")) {
								if (m.checkUserBooking(eventID, customerID)) {
									String c = m.canceledEvent(var,eventID, customerID);
									byte[] msg = c.getBytes();
									DatagramPacket reply = new DatagramPacket(msg, msg.length,
											request.getAddress(), request.getPort());
									MSocket.send(reply);
								} else{
									String c = "EventId not registered for customerId";
									byte[] msg = c.getBytes();
									DatagramPacket reply = new DatagramPacket(msg, msg.length,
											request.getAddress(), request.getPort());
									MSocket.send(reply);
								}
									
							} else {
								
								String c = "No such eventid is available in this eventType";
								byte[] msg = c.getBytes();
								DatagramPacket reply = new DatagramPacket(msg, msg.length,
										request.getAddress(), request.getPort());
								MSocket.send(reply);
							}
						}
	                } 
	                
	                }
	 
	            } catch (SocketException e) {
	                System.out.println("Socket: " + e.getMessage());
	            } catch (IOException e) {
	                System.out.println("IO: " + e.getMessage());
	            } finally {
	                if (MSocket != null)
	                    MSocket.close();
	            }
			

		

	}

	public synchronized String getHashMap(String eventType) {
		// it sends a b or c depending on input
		String value = eventList.get(eventType);

		return value;
	}

	public synchronized String addHashMap(String var, String key, int Value) {
		if (var == "a") {
			// var=eventType sub_hashmap , key=eventID Value=booking Capacity
			if (a.get(key) != null) {

				int val = a.get(key);
				a.replace(key, val + Value);
				return ("Value updated for " + key + "to " + val);
			} else {
				a.put(key, Value);
				return ("Added Successfully " + key + "to " + a.get(key));

			}
		} else if (var == "b") {
			if (b.get(key) != null) {

				int val = b.get(key);
				b.replace(key, val + Value);
				return ("Value updated for " + key + "to " + val);
			} else {
				b.put(key, Value);
				return ("Added Successfully " + key + "to " + b.get(key));
			}
		} else if (var == "c") {
			if (c.get(key) != null) {

				int val = c.get(key);
				c.replace(key, val + Value);
				return ("Value updated for " + key + "to " + val);
			} else {
				c.put(key, Value);
				return ("Added Successfully " + key + "to " + c.get(key));
			}
		} else
			return ("Not Successfull ");

	}

	public synchronized String removeHashMap(String var, String key) {
		//kye=event id
		if (var == "a") {
			if (a.get(key) != null) {
				String key1=new String(key);
				StringBuffer remover=new StringBuffer("Removed "); 
				if (Muser1.containsKey(key) ){
					remover.append("Booking found for customer "+Muser1.get(key));
					char[] c = key.toCharArray();
					String str=Character.toString(c[4]);
					String str2=Character.toString(c[5]);
					String dat = str+str2;
					
					int i = Integer.parseInt(String.valueOf(dat));
					int counter=0;
					for (; i < 30; i++) {
						char[] c1 = key1.toCharArray();
										if(c1[3]=='M'){
											key1 = key.substring(0, 3)+"A" + key1.substring(4);
											if (a.containsKey(key1) && a.get(key1) != 0 ){
												Muser1.put(key1, Muser1.get(key));
												Muser1.remove(key);
												int Value=a.get(key1);
												a.replace(key1, Value-1);
												remover.append(". "+"Booking got changed to "+key1);
												counter++;
												break;
												}
											} c1 = key1.toCharArray();
										if(c1[3]=='A'){
											key1 = key.substring(0, 3)+"E" + key1.substring(4);
											if (a.containsKey(key1) && a.get(key1) != 0 ){
												Muser1.put(key1, Muser1.get(key));
												Muser1.remove(key);
												int Value=a.get(key1);
												a.replace(key1, Value-1);
												remover.append(". "+"Booking got changed to "+key1);
												counter++;
												break;
											}c1 = key1.toCharArray();
										}
										if (i+1 < 10)
													key1 = key1.substring(0, 4) + "0"+ Integer.toString(i+1) + key1.substring(6);
										else
													key1 = key1.substring(0, 4) + Integer.toString(i+1)+ key1.substring(6);
										c1 = key1.toCharArray();
										if(c1[3]=='E'){
													key1 = key.substring(0, 3)+"M" + key1.substring(4);
													if (a.containsKey(key1) && a.get(key1) != 0 ){
														Muser1.put(key1, Muser1.get(key));
														Muser1.remove(key);
														int Value=a.get(key1);
														a.replace(key1, Value-1);
														remover.append(". "+"Booking got changed to "+key1);
														counter++;
														break;
													} }c1 = key1.toCharArray();
					}
					if(counter==0){
						Muser1.remove(key);
						remover.append(". "+"All Booking got cancelled for "+key);
					}
				}
				a.remove(key);
				return(key + " ." +remover.toString());
			} else {

				return("No record");
			}
		} else if (var == "b") {
			if (b.get(key) != null) {
				String key1=new String(key);
				StringBuffer remover=new StringBuffer("Removed "); 
				if (Muser1.containsKey(key) ){
					remover.append("Booking found for customer "+Muser1.get(key));
					char[] c = key.toCharArray();
					String str=Character.toString(c[4]);
					String str2=Character.toString(c[5]);
					String dat = str+str2;
					
					int i = Integer.parseInt(String.valueOf(dat));
					int counter=0;
					for (; i < 30; i++) {
						char[] c1 = key1.toCharArray();
										if(c1[3]=='M'){
											key1 = key.substring(0, 3)+"A" + key1.substring(4);
											if (b.containsKey(key1) && b.get(key1) != 0 ){
												Muser1.put(key1, Muser1.get(key));
												Muser1.remove(key);
												int Value=b.get(key1);
												b.replace(key1, Value-1);
												remover.append(". "+"Booking got changed to "+key1);
												counter++;
												break;
												}
											} c1 = key1.toCharArray();
										if(c1[3]=='A'){
											key1 = key.substring(0, 3)+"E" + key1.substring(4);
											if (b.containsKey(key1) && b.get(key1) != 0 ){
												Muser1.put(key1, Muser1.get(key));
												Muser1.remove(key);
												int Value=b.get(key1);
												b.replace(key1, Value-1);
												remover.append(". "+"Booking got changed to "+key1);
												counter++;
												break;
											}c1 = key1.toCharArray();
										}
										if (i+1 < 10)
													key1 = key1.substring(0, 4) + "0"+ Integer.toString(i+1) + key1.substring(6);
										else
													key1 = key1.substring(0, 4) + Integer.toString(i+1)+ key1.substring(6);
										c1 = key1.toCharArray();
										if(c1[3]=='E'){
													key1 = key.substring(0, 3)+"M" + key1.substring(4);
													if (b.containsKey(key1) && b.get(key1) != 0 ){
														Muser1.put(key1, Muser1.get(key));
														Muser1.remove(key);
														int Value=b.get(key1);
														b.replace(key1, Value-1);
														remover.append(". "+"Booking got changed to "+key1);
														counter++;
														break;
													} }c1 = key1.toCharArray();
					}
					if(counter==0){
						Muser1.remove(key);
						remover.append(". "+"All Booking got cancelled for "+key);
					}
				}
				b.remove(key);
				return(key + " ." +remover.toString());
			} else {

				return("No record");
			}
		} else if (var == "c") {
			if (c.get(key) != null) {
				String key1=new String(key);
				StringBuffer remover=new StringBuffer("Removed "); 
				if (Muser1.containsKey(key) ){
					remover.append("Booking found for customer "+Muser1.get(key));
					char[] ch = key.toCharArray();
					String str=Character.toString(ch[4]);
					String str2=Character.toString(ch[5]);
					String dat = str+str2;
					
					int i = Integer.parseInt(String.valueOf(dat));
					int counter=0;
					for (; i < 30; i++) {
						char[] c1 = key1.toCharArray();
										if(c1[3]=='M'){
											key1 = key.substring(0, 3)+"A" + key1.substring(4);
											if (c.containsKey(key1) && c.get(key1) != 0 ){
												Muser1.put(key1, Muser1.get(key));
												Muser1.remove(key);
												int Value=c.get(key1);
												c.replace(key1, Value-1);
												remover.append(". "+"Booking got changed to "+key1);
												counter++;
												break;
												}
											} c1 = key1.toCharArray();
										if(c1[3]=='A'){
											key1 = key.substring(0, 3)+"E" + key1.substring(4);
											if (c.containsKey(key1) && c.get(key1) != 0 ){
												Muser1.put(key1, Muser1.get(key));
												Muser1.remove(key);
												int Value=c.get(key1);
												c.replace(key1, Value-1);
												remover.append(". "+"Booking got changed to "+key1);
												counter++;
												break;
											}c1 = key1.toCharArray();
										}
										if (i+1 < 10)
													key1 = key1.substring(0, 4) + "0"+ Integer.toString(i+1) + key1.substring(6);
										else
													key1 = key1.substring(0, 4) + Integer.toString(i+1)+ key1.substring(6);
										c1 = key1.toCharArray();
										if(c1[3]=='E'){
													key1 = key.substring(0, 3)+"M" + key1.substring(4);
													if (c.containsKey(key1) && c.get(key1) != 0 ){
														Muser1.put(key1, Muser1.get(key));
														Muser1.remove(key);
														int Value=c.get(key1);
														c.replace(key1, Value-1);
														remover.append(". "+"Booking got changed to "+key1);
														counter++;
														break;
													} }c1 = key1.toCharArray();
					}
					if(counter==0){
						Muser1.remove(key);
						remover.append(". "+"All Booking got cancelled for "+key);
					}
				}
				c.remove(key);
				return(key + " ." +remover.toString());
			} else {

				return("No record");
			}
		}
		return null;
		
	}

	public synchronized String display(String var) {
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		String value = var;
		System.out.println("List for event type ");
		String ss = " ";
		if (value.equalsIgnoreCase("a")) {
			a.entrySet().forEach(entry -> {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
				// ss=ss+entry.getKey();
					temp.put(entry.getKey(), entry.getValue());
				});
			
			
		} else if (value.equalsIgnoreCase("b")) {
			b.entrySet().forEach(entry -> {
				temp.put(entry.getKey(), entry.getValue());
			});
			
		} else if (value.equalsIgnoreCase("c")) {
			c.entrySet().forEach(entry -> {
				temp.put(entry.getKey(), entry.getValue());
			});
			
		}
		
		StringBuffer str=new StringBuffer("");
		temp.entrySet().forEach(entry -> {
			
			str.append(entry.getKey()+" "+ entry.getValue() +"\n");
		});
		return str.toString();

	}

	public synchronized String checkAvailabilityOfEvent(String var, String key) {
		// key is event id

		if (var.equalsIgnoreCase("a")) {
			if (a.containsKey(key) && a.get(key) != 0) {
				return ("Available ");
			} else {

				return ("Not");
			}
		} else if (var.equalsIgnoreCase("b")) {
			if (b.containsKey(key) && b.get(key) != 0) {
				return ("Available ");
			} else {

				return ("Not");
			}
		} else if (var.equalsIgnoreCase("c")) {
			if (c.containsKey(key) && c.get(key) != 0) {
				return ("Available ");
			} else {

				return ("Not");
			}
		}
		return null;
	}

	public synchronized String bookedEvent(String var,String eventID, String customerID) {
		// TODO Auto-generated method stub
		char ch[]=customerID.toCharArray();
		char ch1[]={ ch[0], ch[1], ch[2] };
		String server=new String(ch1);
		
		ArrayList<String> users = new ArrayList<String>();
		if(Muser1.containsKey(eventID)){
			users=Muser1.get(eventID);
			users.add(customerID);
			Muser1.put(eventID, users);
		}else{
			users.add(customerID);
			Muser1.put(eventID, users);
		}
		if(var.equalsIgnoreCase("a")){
			int Value=a.get(eventID);
			a.replace(eventID, Value-1);
		} else if(var.equalsIgnoreCase("b")){
			int Value=b.get(eventID);
			b.replace(eventID, Value-1);
		} else if(var.equalsIgnoreCase("c")){
			int Value=c.get(eventID);
			c.replace(eventID, Value-1);
		}
		
		String s = "booked event " + eventID + " for " + customerID;
		return s;
	}


	public synchronized String canceledEvent(String var,String eventID, String customerID) {
		// TODO Auto-generated method stub

		char ch[]=customerID.toCharArray();
		char ch1[]={ ch[0], ch[1], ch[2] };
		String server=new String(ch1);
		
		ArrayList<String> users = new ArrayList<String>();
		if(Muser1.containsKey(eventID)){
			users=Muser1.get(eventID);
			if(users.size()>1){
				users.remove(customerID);
				Muser1.put(eventID, users);
			}
			
			else if(users.size() == 1){
			Muser1.remove(eventID);
			}
		}
		
		if(var.equalsIgnoreCase("a")){
			int Value=a.get(eventID);
			a.replace(eventID, Value+1);
		} else if(var.equalsIgnoreCase("b")){
			int Value=b.get(eventID);
			b.replace(eventID, Value+1);
		} else if(var.equalsIgnoreCase("c")){
			int Value=c.get(eventID);
			c.replace(eventID, Value+1);
		}
		String s = "cancelled event " + eventID + " for " + customerID;
		return s;
	}

	public synchronized boolean checkUserBooking(String eventID,
			String customerID) {
		// TODO Auto-generated method stub
		//Muser1.put(eventID, customerID);
		if (Muser1.containsKey(eventID)
				&& Muser1.get(eventID).contains(customerID)) {
			return true;
		} else
			return false;
	}

	public synchronized String getUserData(String customerID) {
		HashMap<String, String> temp11 = new HashMap<String, String>();

		/*Montreal.Muser.entrySet().forEach(entry -> {
			if (customerID.equalsIgnoreCase(entry.getValue()))
				temp11.put(entry.getKey(), entry.getValue());
		});
		Quebec.Muser1.entrySet().forEach(entry -> {
			if (customerID.equalsIgnoreCase(entry.getValue()))
				temp11.put(entry.getKey(), entry.getValue());
		});
		Sherbrook.Muser2.entrySet().forEach(entry -> {
			if (customerID.equalsIgnoreCase(entry.getValue()))
				temp11.put(entry.getKey(), entry.getValue());
		});*/
		Muser1.entrySet().forEach(entry -> {
			if (entry.getValue().contains(customerID)){
				temp11.put(entry.getKey(), customerID);
			}
		});
		
		StringBuffer str = new StringBuffer(" ");
		temp11.entrySet().forEach(entry -> {
			str.append(entry.getKey() + " " + entry.getValue() + "\n");
		});
		return str.toString();

	}
	
	public synchronized int getOccurances(String customerID) {
        // TODO Auto-generated method stub
        int[] count = {0};
        Muser1.entrySet().forEach(entry -> {
        	if (entry.getValue().contains(customerID)){
				count[0]++;
			}
                
        });
        return count[0];
    }
	
	public synchronized String UDPConnect(int serverPort, String combinedId) {
		DatagramSocket aSocket = null;
		String str=new String();
		try {
			System.out.println("Quebec client started");
			aSocket = new DatagramSocket();
			byte[] message = combinedId.getBytes();

			InetAddress aHost = InetAddress.getByName("localhost");

			// int serverPort = this.;
			// DatagramPacket request =new DatagramPacket(m, args[0].length(),
			// aHost, serverPort);
			DatagramPacket request = new DatagramPacket(message,
					combinedId.length(), aHost, serverPort);

			aSocket.send(request);
			System.out.println("Request message sent via UDP");

			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

			aSocket.receive(reply);
			String str1=new String(reply.getData());
            str=str1.trim();
            
            return str;
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
		return str;
	}

}
