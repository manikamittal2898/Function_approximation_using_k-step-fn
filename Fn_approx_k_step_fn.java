//Manika Mittal
//1710110202
//Programming Assignment 2
//Function Approximation using k-step function


import java.io.*;
import java.util.*;

public class ver2 {
    public static double D[][];
    public static double A[][];
    public static double H[][];
    public static double G[][][];
    public static double Lsq[][];
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t, i, j;
        t = Integer.parseInt(br.readLine());
        int n[] = new int[t];
        int k[] = new int[t];
        String s[] = new String[t];
        for (i = 0; i < t; i++) {
            StringTokenizer s1 = new StringTokenizer(br.readLine());
            
            n[i] = Integer.parseInt(s1.nextToken());
            k[i] = Integer.parseInt(s1.nextToken());
            s[i] = br.readLine();
        }
        for (i = 0; i < t; i++) {
            int h[] = new int[n[i]];
            StringTokenizer s2 = new StringTokenizer(s[i]);
            for (j = 0; j < n[i]; ++j) {
                h[j] = Integer.parseInt(s2.nextToken());
            }
            
            D = new double[n[i]][n[i]];
            A = new double[n[i]][n[i]];
            H = new double[n[i]][n[i]];
            int c = 1;
            int m, q, r;
            int T;
            for (m = 0; m < n[i]; m++) {
                for (q = 0; q < n[i]; q++) {
                    A[m][q] = findmean(m, q, h);
                    
                }
                
            }
            for (m = 0; m < n[i]; m++) {
                for (q = 0; q < n[i]; q++) {
                    if ((q + m) < n[i]) {
                        H[q][m] = Math.pow(h[q + m], 2);
                        if (m > 0) {
                            H[q][m] += H[q][m - 1];
                        }
                        
                    }
                }
                
            }
            
            for (m = 0; m < n[i]; m++) {
                for (q = 0; q < n[i]; q++) {
                    if (q >= m)
                    D[m][q] = H[m][q - m] - (Math.pow(A[m][q], 2) * (q - m + 1));
                    
                }
                
            }
            G = new double[k[i]][n[i]][n[i]];
            Lsq = new double[k[i]][n[i]];
            Double total = 0.0;
            for (m = 0; m < k[i]; m++) {
                for (q = m; q < n[i]; q++) {
                    total = 0.0;
                    if (m == 0) {
                        for (r = 0; r <= q; r++) {
                            
                            G[m][q][r] = A[0][q];
                            total += Math.pow((G[m][q][r] - h[r]), 2);
                            
                        }
                        Lsq[m][q] = total;
                    } else {
                        T = findT(m, q, h, G);
                        for (r = 0; r <= q; r++) {
                            if (r < T)
                            G[m][q][r] = G[m - 1][T - 1][r];
                            else
                            G[m][q][r] = A[T][q];
                        }
                        for (r = 0; r <= q; r++) {
                            
                            total += Math.pow((G[m][q][r] - h[r]), 2);
                            
                        }
                        Lsq[m][q] = total;
                        
                    }
                }
            }
            
            for (m = 1; m < n[i]; m++) {
                if (G[k[i] - 1][n[i] - 1][m] != G[k[i] - 1][n[i] - 1][m - 1])
                c++;
            }
            double error = Lsq[k[i] - 1][n[i] - 1];
            System.out.println(c + "    " + error);
            m = 0;
            System.out.println((m + 1) + "    " + G[k[i] - 1][n[i] - 1][0]);
            for (m = 1; m < n[i]; m++) {
                if (G[k[i] - 1][n[i] - 1][m] != G[k[i] - 1][n[i] - 1][m - 1])
                System.out.println((m + 1) + "    " + G[k[i] - 1][n[i] - 1][m]);
            }
        }
    }
    
    public static int findT(int i, int j, int h[], double G[][][]) {
        double min = Math.pow(10, 9);
        int s = 0;
        double total = 0.0;
        double g[];
        int t = 0;
        
        for (s = i; s <= j; s++) {
            total = 0.0;
            g = G[i - 1][s - 1];
            
            total = D[s][j] + Lsq[i - 1][s - 1];
            if (total < min) {
                min = total;
                t = s;
            }
        }
        return t;
        
    }
    
    public static double findmean(int i, int j, int h[]) {
        int m;
        double s = 0.0;
        int c = 0;
        for (m = i; m <= j; m++) {
            s += h[m];
            c++;
        }
        return s / c;
    }
    
}
