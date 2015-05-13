/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OptArea;

import ga.PosVectorIndividual;

/**
 *
 * @author Eugenio
 */
public class OptAreaIndividual extends PosVectorIndividual <OptArea>{
    
    private int altura;
    private int largura;
    private int [][] tela;
    private int numPecas;
    
    public OptAreaIndividual(OptArea problem, int size, int altura, int largura, double prob1s) {
        super(problem, size, altura, largura, prob1s);
        
        this.altura = altura;
        this.largura = largura;
        this.tela = new int[altura][largura];
        this.numPecas = size;
        /*
        System.out.println("genoma:");
        for(int p = 0; p< genome.length; p+=3){
            System.out.println("x:" + genome[p] + " y:" + genome[p+1] + " r:" + genome[p+2]);
        }
        System.out.println("\n");
        */
    }

    public OptAreaIndividual(OptAreaIndividual original) {
        super(original);
    }
    
    @Override
    public double computeFitness() {
        fitness = 0.0;
        int[] min = new int[2];
        min[0] = 0;
        min[1] = 0;
        int max[] = new int[2];
        max[0] = 0;
        max[1] = 0;
        int sobreposicao = 0;
        int outOfBounds = 0;
        
        for (int i = 0; i < numPecas; i ++){
            //Peca peca = problem.getPeca(i);
            int posX = getGene(i*3);
            int posY = getGene(i*3+1);
            int[][] forma =  problem.getPeca(i).getForma(getGene(i*3+2));
            
            for(int x=0; x < forma.length; x++){
                for(int y= 0; y < forma[0].length; y++){
                    sobreposicao = (tela[posX+x][posY+y] != 0) ? sobreposicao+=1 : sobreposicao;
                    if(posX+x > tela.length || posY+y > tela[0].length){
                        outOfBounds+=1;
                    }else{
                        tela[posX+x][posY+y]= forma[x][y];
                    }
                }
            }
        }
        
        
        return fitness;
    }

    @Override
    public void swapGenes(PosVectorIndividual other, int g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PosVectorIndividual clone() {
        return new OptAreaIndividual(this);
    }
    
    
    public String ToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("genoma: " + genome);
        sb.append("\n");
        return sb.toString();
    } 
}
