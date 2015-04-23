package coverage.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coverage.web.GraphCoverage;

import org.apache.commons.io.FileUtils;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.easymock.EasyMock.*;

public class GCTest_edgePair_stateInvariant {
    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage  FinalStateTP newGraph Initialized logic logic newGraph Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  FinalState0  */ 
    public void test0() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR newGraph Initialized minimalMUMCUT minimalMUMCUT newGraph Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  FinalState0  */ 
    public void test1() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of minimalMUMCUTMapping for the element minimalMUMCUT ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Minimal-MUMCUT Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("MinimalMUMCUTCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for minimalMUMCUTMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage edgePairCoverage EdgePairCoverage edgeCoverage EdgeCoverage edgePairCoverage EdgePairCoverage edgeCoverage EdgeCoverage primePathCoverage PrimePathCoverage  FinalStateTP logic logic  FinalState0  */ 
    public void test2() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage  FinalStateTP dataFlow dataFlow  FinalState0  */ 
    public void test3() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of dataFlowMapping for the element dataFlow ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Data Flow Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("DFGraphCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for dataFlowMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage edgePairCoverage EdgePairCoverage edgeCoverage EdgeCoverage nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage edgeCoverage EdgeCoverage edgePairCoverage EdgePairCoverage edgeCoverage EdgeCoverage primePathCoverage PrimePathCoverage edgePairCoverage EdgePairCoverage primePathCoverage PrimePathCoverage edgeCoverage EdgeCoverage primePathCoverage PrimePathCoverage edgePairCoverage EdgePairCoverage primePathCoverage PrimePathCoverage  FinalStateTP  FinalState0  */ 
    public void test4() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage nodeCoverage NodeCoverage edgePairCoverage EdgePairCoverage nodeCoverage NodeCoverage edgeCoverage EdgeCoverage edgePairCoverage EdgePairCoverage primePathCoverage PrimePathCoverage nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage nodeCoverage NodeCoverage edgeCoverage EdgeCoverage edgePairCoverage EdgePairCoverage nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage edgePairCoverage EdgePairCoverage edgeCoverage EdgeCoverage nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage edgeCoverage EdgeCoverage nodeCoverage NodeCoverage edgePairCoverage EdgePairCoverage primePathCoverage PrimePathCoverage edgePairCoverage EdgePairCoverage nodeCoverage NodeCoverage edgePairCoverage EdgePairCoverage primePathCoverage PrimePathCoverage edgeCoverage EdgeCoverage edgePairCoverage EdgePairCoverage primePathCoverage PrimePathCoverage nodeCoverage NodeCoverage edgePairCoverage EdgePairCoverage nodeCoverage NodeCoverage edgePairCoverage EdgePairCoverage edgeCoverage EdgeCoverage primePathCoverage PrimePathCoverage  FinalStateTP  FinalState0  */ 
    public void test5() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage  FinalStateTP  InitialTR node Nodes primePath PrimePaths edgePair EdgePairs simplePath SimplePaths node Nodes simplePath SimplePaths edge Edges edgePair EdgePairs primePath PrimePaths  FinalStateTR  dataFlow  FinalState0  */ 
    public void test6() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths node Nodes edgePair EdgePairs simplePath SimplePaths edgePair EdgePairs edge Edges primePath PrimePaths node Nodes primePath PrimePaths node Nodes edge Edges primePath PrimePaths simplePath SimplePaths edgePair EdgePairs node Nodes edgePair EdgePairs simplePath SimplePaths node Nodes primePath PrimePaths simplePath SimplePaths primePath PrimePaths simplePath SimplePaths edge Edges primePath PrimePaths simplePath SimplePaths edgePair EdgePairs simplePath SimplePaths primePath PrimePaths edge Edges primePath PrimePaths edgePair EdgePairs edge Edges edgePair EdgePairs primePath PrimePaths  FinalStateTR newGraph Initialized  InitialTR node Nodes primePath PrimePaths node Nodes primePath PrimePaths edgePair EdgePairs primePath PrimePaths simplePath SimplePaths node Nodes edge Edges edgePair EdgePairs simplePath SimplePaths edge Edges simplePath SimplePaths edge Edges node Nodes edgePair EdgePairs edge Edges simplePath SimplePaths primePath PrimePaths edge Edges primePath PrimePaths  FinalStateTR newGraph Initialized dataFlow dataFlow newGraph Initialized minimalMUMCUT minimalMUMCUT newGraph Initialized logic logic newGraph Initialized logic logic  FinalState0  */ 
    public void test7() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of dataFlowMapping for the element dataFlow ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Data Flow Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("DFGraphCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for dataFlowMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of minimalMUMCUTMapping for the element minimalMUMCUT ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Minimal-MUMCUT Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("MinimalMUMCUTCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for minimalMUMCUTMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage  FinalStateTP newGraph Initialized  InitialTR node Nodes edgePair EdgePairs primePath PrimePaths simplePath SimplePaths edgePair EdgePairs primePath PrimePaths edge Edges primePath PrimePaths edge Edges edgePair EdgePairs edge Edges edgePair EdgePairs edge Edges node Nodes edge Edges node Nodes edgePair EdgePairs node Nodes edge Edges node Nodes edgePair EdgePairs simplePath SimplePaths edgePair EdgePairs simplePath SimplePaths edge Edges node Nodes simplePath SimplePaths node Nodes edgePair EdgePairs primePath PrimePaths edgePair EdgePairs node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage edgeCoverage EdgeCoverage primePathCoverage PrimePathCoverage edgeCoverage EdgeCoverage nodeCoverage NodeCoverage edgeCoverage EdgeCoverage nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage edgePairCoverage EdgePairCoverage nodeCoverage NodeCoverage edgePairCoverage EdgePairCoverage primePathCoverage PrimePathCoverage  FinalStateTP newGraph Initialized dataFlow dataFlow newGraph Initialized  InitialTR node Nodes simplePath SimplePaths primePath PrimePaths simplePath SimplePaths primePath PrimePaths  FinalStateTR  logic  FinalState0  */ 
    public void test8() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgePairCoverageMapping for the element edgePairCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge-Pair Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of dataFlowMapping for the element dataFlow ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Data Flow Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("DFGraphCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for dataFlowMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
    }

    @Test
    /* The test for the path Initial0 initialize Initialized dataFlow dataFlow  FinalState0  */ 
    public void test9() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of dataFlowMapping for the element dataFlow ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Data Flow Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("DFGraphCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for dataFlowMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized logic logic  FinalState0  */ 
    public void test10() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized minimalMUMCUT minimalMUMCUT  FinalState0  */ 
    public void test11() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of minimalMUMCUTMapping for the element minimalMUMCUT ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Minimal-MUMCUT Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("MinimalMUMCUTCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for minimalMUMCUTMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage  FinalStateTP minimalMUMCUT minimalMUMCUT  FinalState0  */ 
    public void test12() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of minimalMUMCUTMapping for the element minimalMUMCUT ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Minimal-MUMCUT Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("MinimalMUMCUTCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for minimalMUMCUTMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR newGraph Initialized logic logic newGraph Initialized minimalMUMCUT minimalMUMCUT newGraph Initialized minimalMUMCUT minimalMUMCUT  FinalState0  */ 
    public void test13() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of minimalMUMCUTMapping for the element minimalMUMCUT ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Minimal-MUMCUT Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("MinimalMUMCUTCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for minimalMUMCUTMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of minimalMUMCUTMapping for the element minimalMUMCUT ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Minimal-MUMCUT Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("MinimalMUMCUTCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for minimalMUMCUTMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage  FinalStateTP newGraph Initialized minimalMUMCUT minimalMUMCUT newGraph Initialized dataFlow dataFlow newGraph Initialized logic logic newGraph Initialized dataFlow dataFlow newGraph Initialized dataFlow dataFlow newGraph Initialized logic logic newGraph Initialized  InitialTR node Nodes primePath PrimePaths simplePath SimplePaths edgePair EdgePairs simplePath SimplePaths primePath PrimePaths node Nodes simplePath SimplePaths node Nodes simplePath SimplePaths edge Edges edgePair EdgePairs node Nodes edge Edges simplePath SimplePaths edgePair EdgePairs edge Edges primePath PrimePaths edge Edges simplePath SimplePaths edge Edges simplePath SimplePaths node Nodes primePath PrimePaths edge Edges node Nodes simplePath SimplePaths edgePair EdgePairs edge Edges node Nodes primePath PrimePaths  FinalStateTR  minimalMUMCUT  FinalState0  */ 
    public void test14() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of minimalMUMCUTMapping for the element minimalMUMCUT ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Minimal-MUMCUT Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("MinimalMUMCUTCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for minimalMUMCUTMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of dataFlowMapping for the element dataFlow ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Data Flow Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("DFGraphCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for dataFlowMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of dataFlowMapping for the element dataFlow ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Data Flow Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("DFGraphCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for dataFlowMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of dataFlowMapping for the element dataFlow ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Data Flow Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("DFGraphCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for dataFlowMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes edge Edges primePath PrimePaths edgePair EdgePairs node Nodes simplePath SimplePaths primePath PrimePaths edgePair EdgePairs primePath PrimePaths node Nodes primePath PrimePaths  FinalStateTR  FinalState0  */ 
    public void test15() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of edgeMapping for the element edge ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edges");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        
        e1.printStackTrace();
        }
        
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edges"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of simplePathMapping for the element simplePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Simple Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for simplePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Simple Paths"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of edgePairMapping for the element edgePair ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge-Pair");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgePairMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Edge-Pairs"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage edgeCoverage EdgeCoverage primePathCoverage PrimePathCoverage nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage  FinalStateTP  FinalState0  */ 
    public void test16() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of edgeCoverageMapping for the element edgeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Edge Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for edgeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Edge Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage  FinalStateTP dataFlow dataFlow newGraph Initialized logic logic  FinalState0  */ 
    public void test17() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of dataFlowMapping for the element dataFlow ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Data Flow Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("DFGraphCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for dataFlowMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage  FinalStateTP logic logic newGraph Initialized logic logic  FinalState0  */ 
    public void test18() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  InitialTP nodeCoverage NodeCoverage primePathCoverage PrimePathCoverage  FinalStateTP minimalMUMCUT minimalMUMCUT newGraph Initialized logic logic  FinalState0  */ 
    public void test19() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of nodeCoverageMapping for the element nodeCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Node Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Node Coverage"));
        /*** test code of primePathCoverageMapping for the element primePathCoverage ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Path Coverage");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathCoverageMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("test paths are needed for Prime Path Coverage"));
        /*** test code of minimalMUMCUTMapping for the element minimalMUMCUT ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Minimal-MUMCUT Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("MinimalMUMCUTCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for minimalMUMCUTMapping***/
        

        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  dataFlow newGraph Initialized logic logic  FinalState0  */ 
    public void test20() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  logic newGraph Initialized logic logic  FinalState0  */ 
    public void test21() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

    }

    @Test
    /* The test for the path Initial0 initialize Initialized  InitialTR node Nodes primePath PrimePaths  FinalStateTR  minimalMUMCUT newGraph Initialized logic logic  FinalState0  */ 
    public void test22() throws IOException {
        /*** test code of initializeMapping for the element initialize ***/
        GraphCoverage gc = new GraphCoverage();
        File file = new File("test.html");
        try {
        file.createNewFile();
        } catch (IOException e2) {
        e2.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        /*** test oracle level TO1  for initializeMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of nodeMapping for the element node ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Nodes");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for nodeMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Nodes"));
        /*** test code of primePathMapping for the element primePath ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Prime Paths");
        expect(request.getParameter("initialNode")).andReturn("1");
        expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
        expect(request.getParameter("endNode")).andReturn("7");
        expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
        expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
        expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
        
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for primePathMapping***/
        

        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        assertEquals(true, FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("requirements are needed for Prime Paths"));
        /*** test code of newGraphMapping for the element newGraph ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("New Graph");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for newGraphMapping***/
        

        assertEquals(true, !FileUtils.readFileToString(new File("test.html"), "UTF-8").contains("tableResult"));
        /*** test code of logicMapping for the element logic ***/
        try {
        writer = new PrintWriter(file);
        } catch (FileNotFoundException e2) {
        e2.printStackTrace();
        }
        request = createStrictMock(HttpServletRequest.class);
        response = createStrictMock(HttpServletResponse.class);
        
        expect(request.getParameter("action")).andReturn("Logic Coverage");
        response.setContentType("text/html");
        try {
        expect(response.getWriter()).andReturn(writer);
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        try {
        response.sendRedirect("LogicCoverage");
        } catch (IOException e1) {
        e1.printStackTrace();
        }
        
        replay(request);
        replay(response);
        
        try {
        gc.doPost(request, response);
        } catch (ServletException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        verify(request);
        verify(response);
        writer.flush();
        /*** test oracle level TO1  for logicMapping***/
        

    }

}
