package com.example.exprweb2006;

import com.example.exprweb2006.beans.ExpressionBean;
import com.example.exprweb2006.db.DBConnection;
import com.example.exprweb2006.service.ExpressionService;
import com.example.exprweb2006.tables.Expression;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

@WebServlet(name = "helloServlet", value = {"/calculate", "/edit", "/finish_edit"})
public class HelloServlet extends HttpServlet {

    private ExpressionService es;

    @Resource(name = "jdbc/expressions")
    private DataSource ds;

    public void init() {
        es = new ExpressionService();
        try {
            //Context ctx = new InitialContext();
//            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/chinook");
            DBConnection.getInstance().initConnection(ds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getRequestURI().contains("/edit")) {
            processEdit(request, response);
        } else {
            showAll(request, response);
        }
    }

    private void showAll(HttpServletRequest request, HttpServletResponse response) {
        ExpressionBean bean = new ExpressionBean();
        try {
            List<Expression> expressions = es.findAll();
            bean.setExpressions(expressions);
            request.setAttribute("expressionBean", bean);
            request.getRequestDispatcher("/view.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void processEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExpressionBean bean = new ExpressionBean();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Optional<Expression> e = es.findById(id);
            if (e.isPresent()) {
                bean.setEdited(e.get());
                request.setAttribute("expressionBean", bean);
                request.getRequestDispatcher("/edit.jsp").forward(request, response);
            } else {
                response.sendRedirect("calculate");
            }
        } catch (SQLException ex) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains("/finish_edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String ex = request.getParameter("expr");
            double v = es.calculate(ex);
            es.updateById(id, ex, v);
            response.sendRedirect("calculate");
        } else {
            ExpressionBean bean = new ExpressionBean();
            try {
                String expression = request.getParameter("expression");
                bean.setCurrent(expression);
                double v = es.calculate(expression);
                es.saveToDB(expression, v);
                bean.setMessage("Result = " + v);
            } catch (Exception e) {
                bean.setMessage("error in expression");
            }
            request.setAttribute("expressionBean", bean);
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}