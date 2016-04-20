package wdsr.exercise3.hr;

import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.SOAPFaultException;

import wdsr.exercise3.ws.EmployeeType;
import wdsr.exercise3.ws.HolidayRequest;
import wdsr.exercise3.ws.HolidayResponse;
import wdsr.exercise3.ws.HolidayType;
import wdsr.exercise3.ws.HumanResource;
import wdsr.exercise3.ws.HumanResourceService;

// TODO Complete this class to book holidays by issuing a request to Human Resource web service.
// In order to see definition of the Human Resource web service:
// 1. Run HolidayServerApp.
// 2. Go to http://localhost:8090/holidayService/?wsdl
public class HolidayClient {
	
	private HumanResource client;
	/**
	 * Creates this object
	 * @param wsdlLocation URL of the Human Resource web service WSDL
	 */
	public HolidayClient(URL wsdlLocation) {
		HumanResourceService hrs = new HumanResourceService(wsdlLocation);
		client = hrs.getHumanResourcePort();
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
		HolidayRequest holidayRequest = new HolidayRequest();
		EmployeeType employeeType = new EmployeeType();
		employeeType.setNumber(employeeId);
		employeeType.setFirstName(firstName);
		employeeType.setLastName(lastName);
		holidayRequest.setEmployee(employeeType);
		
		HolidayType holidayType = new HolidayType();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(startDate);
		XMLGregorianCalendar date1 = null;
		try {
			date1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			holidayType.setStartDate(date1);
			c.setTime(endDate);
			date1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			holidayType.setEndDate(date1);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		holidayRequest.setHoliday(holidayType);
		
		try{
			return client.holiday(holidayRequest).getRequestId();
		} catch (SOAPFaultException e){
			throw new ProcessingException();
		}
	}
}
