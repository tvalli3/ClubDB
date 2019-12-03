package servlets;

import business.Member;
import business.Purchase;
import business.PurchaseDB;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tom Valli
 */
public class ShowPurchasesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String URL = "/MemberScreen.jsp";
        String msg = "", mo="", dy="", yr="";
        List<Purchase> pur;
        Date pd = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        NumberFormat curr = NumberFormat.getCurrencyInstance();
                
        
        try {
            Member m = (Member) request.getSession().getAttribute("m");
            
            mo = request.getParameter("month");
            dy = request.getParameter("day");
            yr = request.getParameter("year");
            if (!mo.isEmpty() && !dy.isEmpty() && !yr.isEmpty()) {
                try {
                    pd = sdf.parse(mo + "-" + dy + "-" + yr);
                } catch (Exception e) {
                    pd = null;
                }
            }
            pur = PurchaseDB.getPurchases(m.getMemid());
            int numPurchs = 0;
            if (pur == null) {
                msg += "Purchases list returned empty<br>";
            } else {
                if (pd != null) {
                    double totalTerm = 0;
                    double owedTerm = 0, paidTerm = 0;
                    List<Purchase> purDate = new ArrayList<>();
                    for (Purchase p : pur) {                        
                        try {
                            if (p.getPurchasedt().after(pd)) {
                                purDate.add(p);
                                if (p.getTranstype().equalsIgnoreCase("D")) {
                                    owedTerm += p.getAmount();
                                } else if (p.getTranstype().equalsIgnoreCase("C")) {
                                    paidTerm += p.getAmount();
                                }
                                totalTerm = owedTerm - paidTerm;
                            }
                        } catch (Exception e) {
                            msg += "Error accessing purchases based on date: " +
                                    e.getMessage() + "<br>";
                        }
                    }
                    numPurchs = purDate.size();
                    msg += "Total Due (Selected Date Range): " + curr.format(totalTerm) + "<br>";
                    request.setAttribute("pur", purDate);
                } else if (pd == null) {
                    numPurchs = pur.size();
                    request.setAttribute("pur", pur);
                }
            }
            
            double owed = 0, paid = 0;
            double totalHist = 0;    
            for (Purchase p : pur) {
                if (p.getTranstype().equalsIgnoreCase("D")) {
                    owed += p.getAmount();
                } else if (p.getTranstype().equalsIgnoreCase("C")) {
                    paid += p.getAmount();
                }
            }
            totalHist += owed - paid;
            msg += "Total Due (All Time): " + curr.format(totalHist) + "<br>";
            msg += "Transactions = " + numPurchs + "<br>";
            URL = "/Purchases.jsp";
                
        } catch (Exception e) {
            msg += "Servlet error: " + e.getMessage() + "<br>";
        }
        request.setAttribute("msg", msg);
        RequestDispatcher disp = getServletContext().getRequestDispatcher(URL);
        disp.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
