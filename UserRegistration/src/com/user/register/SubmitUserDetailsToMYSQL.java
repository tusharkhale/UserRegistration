package com.user.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class SubmitUserDetailsToMYSQL
 */
@WebServlet("/SubmitUserDetailsToMYSQL")
public class SubmitUserDetailsToMYSQL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// This method is not needed as of now... could be used in some other form
		// later....
		System.out.println("Hello, This is the doGet method");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Read the entries from the form...
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String age = request.getParameter("age");
		String companyname = request.getParameter("companyname");
		String designation = request.getParameter("designation");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String contactisd = request.getParameter("isdcode");
		String contact = request.getParameter("contact");
		String faxisd = request.getParameter("isdcodefax");
		String fax = request.getParameter("fax");
		String email = request.getParameter("email");

		if (faxisd == "") {
			faxisd = "NULL";
		}
		if (fax == "") {
			fax = "NULL";
		}

		// do some processing here...
		try {
			String sql = "INSERT INTO user_details (firstname,lastname,age,companyname,designation,country,state,city,isdcodecontact,contactno,isdcodefax,faxno,emailid) VALUES ('"
					+ firstname + "','" + lastname + "'," + age + ",'" + companyname + "','" + designation + "','"
					+ country + "','" + state + "','" + city + "'," + contactisd + "," + contact + "," + faxisd + ","
					+ fax + ",'" + email + "')";
			System.out.println(sql);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tushar_test", "tushar",
					"1942gonzopera$");
			PreparedStatement prep = (PreparedStatement) con.prepareStatement(sql);
			prep.executeUpdate();

			// get response writer
			PrintWriter writer = response.getWriter();

			// build HTML code
			String htmlRespone = "<html>";
			htmlRespone += "<h2>User Details Submitted</h2>";
			htmlRespone += "</html>";

			// return response
			writer.println(htmlRespone);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
