import java.util.*;

public class Main{

    public static void main(String[] args)
    {
        Scanner numerosMatriz = new Scanner(System.in);
        int N, M, R1, R2;
       
        System.out.println("Ingrese numero de filas (M) y columnas (N) separadas por un espacio (Estos deben de ser valores entre 1 y 1000):");
       
        M = numerosMatriz.nextInt();
        N = numerosMatriz.nextInt();

        if(M >= 1 && N >= 1 && M <= 1000 && N <= 1000)
        {
            int[][] matriz = new int[M][N];

            for(int i = 0; i < M; i++)
            {
                System.out.println("Ingrese TODOS los valores de la Fila [" + (i + 1) +"] separados por un espacio:");

                for(int j = 0; j < N; j++)
                {
                   
                    matriz[i][j] = numerosMatriz.nextInt();

                    while(matriz[i][j] < Math.pow(10,-8) || matriz[i][j] > Math.pow(10,8))
                    {
                        System.out.println("Ingrese nuevamente el valor para la posicion [" + i +"][" + j + "], excedió los limites: " );
                        matriz[i][j] = numerosMatriz.nextInt();

                    }
                }
            }

            System.out.println("Ingrese las filas que desea comparar separadas por un espacio: ");
            R1 = numerosMatriz.nextInt();
            R2 = numerosMatriz.nextInt();

            if( (R1 >= 1 && R1 <= M) && (R2 >= 1 && R2 <= M) )
            {
                for(int i=0; i< N ; i++)
                {
                    if(matriz[R1-1][i] % 2==0)
                    {
                        for(int l=0; l< N ; l++)
                        {
                            if(matriz[R2-1][l] % 2==0 && matriz[R2-1][l]==matriz[R1-1][i])
                            {
                                System.out.println("Posicion (" + (R1-1) + "," + l + ") --> " + matriz[R1-1][i] + "/ Posicion (" + (R2-1) + "," + l + ") --> " + matriz[R2-1][l]);
                            }
                        }
                    }
                }
            }
           
            else
            {
                System.out.println("No se econtró ningun par en las filas seleccionadas.");
            }
        }

        else
        {
            System.out.println("La operación no fue realizada, debe ingresar un numero entre 1 y 1000.");
        }
    }
}