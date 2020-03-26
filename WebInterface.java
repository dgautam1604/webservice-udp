package webservice;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)

public interface WebInterface 
{

    String addEvent (String eventID,String eventType,int bookingCapacity,String serv);
	String removeEvent (String eventID,String eventType,String serv);
	String listEventAvailability (String eventType,String serv);
    String bookEvent (String customerID,String eventID,String eventType,String serv);
	String getBookingSchedule (String customerID,String serv) ;
	String cancelEvent (String customerID,String eventID,String eventType,String serv) ;
    String swapEvent (String  customerID,String newEventID,String newEventType,String oldEventID, String oldEventType,String serv);
   

}
