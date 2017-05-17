package be.pxl.crypto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DecryptServlet
 */
@WebServlet("/decryptServlet")
public class DecryptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DecryptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathToOutputPicture = request.getParameter("encryptedPicture");
		String message = Decryptor.decrypt(pathToOutputPicture);
		
		boolean checkHash = Decryptor.checkIfHashIsCorrect(pathToOutputPicture);
		if(!checkHash){
			request.setAttribute("hash", "Warning: the hash of the received message did not match the hash of the sent message! The message might be corrupted.");
			request.setAttribute("message", message);
		} else{
			request.setAttribute("hash", "The hash of the received message matches the hash of the sent message. You can be sure the message has not been edited!");
			request.setAttribute("message", message);
		}
		
		request.getRequestDispatcher("decrypt.jsp").forward(request, response);
	}

}
