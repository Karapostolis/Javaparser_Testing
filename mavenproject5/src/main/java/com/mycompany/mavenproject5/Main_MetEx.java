/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject5;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import com.github.javaparser.utils.ProjectRoot;
import com.github.javaparser.utils.SourceRoot;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author akisg
 */
public class Main_MetEx {

    /**
     * @param args the command line arguments
     */
    
     private static class MethodNamePrinter2 extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration mc, Void arg) {
            
            try{
            System.out.println(mc.resolve().getQualifiedName());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return;
            }
        }
     
     }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        
        CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
        //combinedTypeSolver.add(new JavaParserTypeSolver(new File("C:\\Users\\mario\\Desktop\\mavenperser\\target\\lib\\sources")));
        combinedTypeSolver.add(new ReflectionTypeSolver());
        ParserConfiguration parserConfiguration = new ParserConfiguration().setSymbolResolver(new JavaSymbolSolver(combinedTypeSolver));
        ProjectRoot projectRoot = new SymbolSolverCollectionStrategy(parserConfiguration).collect(Path.of("C:\\Netbeans_Project\\Ptyxiaki\\target\\lib\\sources\\New_Com"));
        for (SourceRoot sourceRoot : projectRoot.getSourceRoots()) {
    try {
        sourceRoot.tryToParse();
        List<CompilationUnit> compilationUnits = sourceRoot.getCompilationUnits();
        for (int i=0;i<compilationUnits .size();i++){
        VoidVisitor<Void> methodNameVisitor = new MethodNamePrinter2();
        methodNameVisitor.visit(compilationUnits .get(i), null);
        }
        }
        catch (Exception e) {
        e.printStackTrace();
        return;
    }
        
        
    }
    
}
    
}
