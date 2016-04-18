package wdsr.exercise3.hr;

import java.net.URL;
import java.util.Date;

import javax.xml.ws.WebServiceClient;

// TODO Complete this class to book holidays by issuing a request to Human Resource web service.
// In order to see definition of the Human Resource web service:
// 1. Run HolidayServerApp.
// 2. Go to http://localhost:8090/holidayService/?wsdl
@WebServiceClient(name = "HolidayServerApp", 
				targetNamespace = "http://localhost:8090/holidayService", 
				wsdlLocation = "http://localhost:8090/holidayService/?wsdl")
public class HolidayClient {
	/**
	 * Creates this object
	 * @param wsdlLocation URL of the Human Resource web service WSDL
	 */
	public HolidayClient(URL wsdlLocation) {
		// TODO
	}
	
	/**
	 * Sends a holiday request to the HumanResourceService.
	 * @param employeeId Employee ID
	 * @param firstName First name of employee
	 * @param lastName Last name of employee
	 * @param startDate First day of the requested holiday
	 * @param endDate Last day of the requested holiday
	 * @return Identifier of the request, if accepted.
	 * @throws ProcessingException if request processing fails.
	 */
	public int bookHoliday(int employeeId, String firstName, String lastName, Date startDate, Date endDate) throws ProcessingException {
		// TODO
		return 0;
	}
}
