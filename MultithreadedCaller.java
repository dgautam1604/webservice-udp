package client;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import webservice.WebInterface;



public class MultithreadedCaller {
	public static Service mtlService;
	public static Service queService;
	public static Service sheService;
	static WebInterface dem;
	static WebInterface dem1;
	static WebInterface dem2;

	public static void main(String[] args) throws MalformedURLException {
		

			
			String customerID="MTLC0001";

			String customerID1="QUEC0002";

			String customerID2="SHEC0003";
			String eventID="MTLM010120";
			String eventType="Conference";
			
			URL MTLURL = new URL("http://localhost:8080/mtl?wsdl");
			QName MTLQName = new QName("http://webimpl/", "DemsImplementationService");
			mtlService = Service.create(MTLURL, MTLQName);
			
			URL QUEURL = new URL("http://localhost:8081/que?wsdl");
			QName QUEQName = new QName("http://webimpl/", "DemsImplementationService");
			queService = Service.create(QUEURL, QUEQName);
			
			URL SHEURL = new URL("http://localhost:8082/she?wsdl");
			QName SHEQName = new QName("http://webimpl/", "DemsImplementationService");
			sheService = Service.create(SHEURL, SHEQName);
			
			
				
			
			new Thread() {
				@Override 
				public void run() {
					try {
						dem = mtlService.getPort(WebInterface.class);
						String add=dem.bookEvent(customerID, eventID, eventType,"MTL");
						System.out.println(add);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
			new Thread() {
				@Override 
				public void run() {
					try {
						dem1 = queService.getPort(WebInterface.class);
						String add=dem1.bookEvent(customerID1, eventID, eventType,"QUE");
						System.out.println(add);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
			
			new Thread() {
				@Override 
				public void run() {
					try {
						dem2 = sheService.getPort(WebInterface.class);
						String add=dem2.bookEvent(customerID2, eventID, eventType,"SHE");
						System.out.println(add);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}.start();
		
		

	}
	
}

