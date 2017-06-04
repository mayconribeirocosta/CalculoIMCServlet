package mvc;

import java.io.IOException;
import static java.lang.System.out;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/mvc/calculo-imc")
public class CalculoIMCController extends HttpServlet {
	
	@Override
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//Prepara para a execu��o.
		String alturaStr = request.getParameter("altura");
		alturaStr = alturaStr == null ? "0" : alturaStr;
		float altura = Float.parseFloat(alturaStr);
                String pesoStr = request.getParameter("peso");
		pesoStr = pesoStr == null ? "0" : pesoStr;
		float peso = Float.parseFloat(pesoStr);
		String imcStr = request.getParameter("imc");
                imcStr = imcStr == null ? "0" : imcStr;
                float imc = Float.parseFloat(imcStr);
                String condicaoStr = request.getParameter("condicao");
                condicaoStr = condicaoStr == null ? "0" : condicaoStr;
                String condicao = condicaoStr;
		//Executa "regras de neg�cio".
		CalculoIMCModel calculoImcModel = new CalculoIMCModel(altura,peso);
                
		Float calcularimc = calculoImcModel.calcularimc(imc);
                String condicaoIMC = calculoImcModel.condicaoIMC(condicao);
		//Passa a v�ri�vel para a p�gina JSP.
			if (peso <0 || altura <0 || calcularimc == 0){
                    condicaoIMC = "IMC inv�lido! Tente 				    novamente";
                    calcularimc = null;
                    
                }
			else if (peso ==0 && altura ==0){
				condicaoIMC = "O IMC ainda n�o foi 					calculado!";
				calcularimc = null;
			}
                else if (peso >0 || altura>0){
                    out.println(condicaoIMC);
                    out.println(calcularimc);
                }
		request.setAttribute("calcularimc", calcularimc);
                request.setAttribute("condicao", condicaoIMC);  
		//Chama p�gina JSP.
		request.getRequestDispatcher("CalculoIMCServlet.jsp").
			forward(request, response);
                
               	}
	
}