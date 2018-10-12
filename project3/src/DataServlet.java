import project2.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/DataServlet")
/*
 * David Black
 * Object-oriented MWF 8 am
 * Professor Mark Doderer
 * Project 3
 */
public class DataServlet extends HttpServlet 
{
	//Servlet variable declarations
	private static final long serialVersionUID = 1L;
    HttpSession session;
    ArrayList<String> compList;
    ArrayList<String> dList;
    Data data;
    /**
     * @see HttpServlet#HttpServlet()
     */
    /*
     * Constructor. Pulls company names and dates from Project 2, 
     * storing them in the class ArrayLists defined above. 
     */
    public DataServlet() 
    {
        super();
        data = new Data();
        compList = new ArrayList<String>(); 
        dList = new ArrayList<String>();

        for(int i = 0; i < data.getStockCount(); i++)
        	compList.add(data.getStockName(i));

        for(int i = 0; i < data.getWeekCount(); i++)
        	dList.add(data.getWeekDate(i));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /*
     * Get method. This is used for the username/password index.html form. Stores the
     * user input in two strings, then checks to see if they equal certain set values.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Username and password equal the user input from each html field.
		String userName = request.getParameter("username");
		String password = request.getParameter("pw");
		
		//If the username and password are correct, start a new session, then redirect to secondPage.
		if(userName.equals("dblack") && password.equals("123456"))
		{
			session = request.getSession();
			session.setAttribute("username", userName);
			response.sendRedirect("secondPage.jsp");
		}
		else
			response.sendRedirect("index.html");	//Else, reload the index page.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * Post method. This is used in the second page to collect the user's company and date
	 * selections. The data is then posted in the third page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Store user-selected data in two strings.
		String company = request.getParameter("company");
		String date = request.getParameter("date");
		
		//Iterator used later to locate a correct index value.
		Iterator<String> cIter = compList.iterator();
		
		//Set the variables as session attributes.
		session.setAttribute("company", company);
		session.setAttribute("date", date);
		
		//Initialize index variables. 
		int compIndex = 0;		//compIndex cannot be set the same way as dateIndex. Doesn't work.
		int dateIndex = dList.indexOf(date);
		
		//Iterate through the company name ArrayList, comparing each element to the user-selected
		//company. When found, store the index in compIndex.
		int i = 0;
		while(cIter.hasNext())
		{
			if(cIter.next().contains(company))
				compIndex = i;
			i++;
		}
		
		//Set session attributes for open, close, high, low, and percent price change using the indexes.
		session.setAttribute("open", data.getStocksWeek(compIndex, dateIndex).getOpen());
		session.setAttribute("close", data.getStocksWeek(compIndex, dateIndex).getClose());
		session.setAttribute("high", data.getStocksWeek(compIndex, dateIndex).getHigh());
		session.setAttribute("low", data.getStocksWeek(compIndex, dateIndex).getLow());
		session.setAttribute("percent", data.getStocksWeek(compIndex, dateIndex).getPerP());
		
		//Once the user has hit submit, redirect to the third page. If for some reason the variables
		//equal null, reload the second page.
		if(!company.equals(null) && !date.equals(null))
			response.sendRedirect("thirdPage.jsp");
		else
			response.sendRedirect("secondPage.jsp");
	}
}
