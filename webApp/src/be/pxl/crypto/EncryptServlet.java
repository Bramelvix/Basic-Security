package be.pxl.crypto;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EncryptServlet
 */
@WebServlet("/encryptServlet")
public class EncryptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EncryptServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = request.getParameter("message");
		String outputName = request.getParameter("outputPicture");
		String pathToInputPicture = request.getParameter("inputPicture");
		File file = new File(pathToInputPicture);
		String directory = file.getParent();

		if (!outputName.endsWith(".png") && pathToInputPicture.endsWith(".png")) {
			if (outputName.contains(".")) {
				outputName = outputName.substring(0, outputName.indexOf('.')) + ".png";
			} else {
				outputName = outputName + ".png";
			}
		} else {
			if (!outputName.endsWith(".wav") && pathToInputPicture.endsWith(".wav")) {
				if (outputName.contains(".")) {
					outputName = outputName.substring(0, outputName.indexOf('.')) + ".wav";
				} else {
					outputName = outputName + ".wav";
				}
			}
		}
		String pathToOutputPicture = new File(directory, outputName).toString();
		Encryptor.encrypt(message, pathToInputPicture, pathToOutputPicture);

		request.setAttribute("outputFileName", outputName);
		request.getRequestDispatcher("okMessage.jsp").forward(request, response);

	}

}
