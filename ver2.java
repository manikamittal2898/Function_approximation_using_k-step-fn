import java.io.*;
import java.util.*;
public class ver2{
public static double D[][];
public static double A[][];
public static double H[][];
public static void main(String args[]) throws IOException{
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
int t,i,j;
t = Integer.parseInt(br.readLine());
int n[] = new int[t];
int k[] = new int[t];
String s[]= new String[t];
for (i = 0; i < t; i++) {
    StringTokenizer s1 = new StringTokenizer(br.readLine());
    
    n[i] = Integer.parseInt(s1.nextToken());
    k[i] = Integer.parseInt(s1.nextToken());
    s[i]= br.readLine();
 }
 for(i=0;i<t;i++){
    int h[] = new int[n[i]];
    StringTokenizer s2 = new StringTokenizer(s[i]);
    for (j = 0; j < n[i]; ++j) {
        h[j] = Integer.parseInt(s2.nextToken());
    }
    double g[]=findg( k[i], n[i],h);
    D= new double[n[i]][n[i]];
    A= new double[n[i]][n[i]];
    H= new double[n[i]][n[i]];
    int c=1;
    int m,q;
    for(m=0;m<n[i];m++){
        for(q=0;q<n[i];q++){
            A[m][q]=findmean(m,q,h);
            System.out.print(A[m][q]);
        }
        System.out.println();
    }
    for(m=0;m<n[i];m++){
        for(q=0;q<n[i];q++){
            if((q+m)<n[i]){
            H[q][m]=Math.pow(h[q+m],2);
            if(m>0){
                H[q][m]+=H[q][m-1];
            }
            //System.out.print(H[q][m]+"  ");
        }
        }
       // System.out.println();
    }
    for(m=0;m<n[i];m++){
        for(q=0;q<n[i];q++){
            
            System.out.print(H[m][q]+"  ");
        }
        System.out.println();
    }
    for(m=0;m<n[i];m++){
        for(q=0;q<n[i];q++){
            D[m][q]=Math.pow(H[m][q],2)/(q-m+1)-Math.pow(A[m][q],2);
            System.out.print(D[m][q]+"  ");
        }
        System.out.println();
    }
   
    for(m=1;m<g.length;m++){
        if(g[m]!=g[m-1])
          c++;
    }
    double error= findlsquare(g,h);
    System.out.println(c+"    "+error);
    m=0;
    System.out.println((m+1)+"    "+g[0]);
    for(m=1;m<g.length;m++){
     if(g[m]!=g[m-1])
        System.out.println((m+1)+"    "+g[m]);
    }
 }
}
public static double findd(int h[], int i, int j){
    double d=0.0;
    double g[]= new double[j];
    int m;
  
        
            double a= findmean(i,j-1,h);
            for(m=i;m<j;m++){
                d+=Math.pow(h[m]-a,2);

            }

    return d;
}

public static double[] findg(int i, int j,int h[] ){
    int m;
    double g[]= new double[j];
    
    if(i==1){
        g[0]=findmean(0,j-1,h);
        for(m=1;m<j;m++){
            g[m]=g[0];
        }
    }
    else{
        int t= findt(i,j,h);
        for(m=0;m<j;m++){
            if(m<t)
                g[m]= findg(i-1,t,h)[m];
            else
                g[m]= findmean(t,j-1,h);

        }
    }
    return g;
}
public static double findmean( int i, int j, int h[]){
    int m;
    double s=0.0;
    int c=0;
    for(m=i;m<=j;m++){
        s+=h[m];
        c++;
    }
    return s/c;
}
public static double findlsquare( double g[], int h[]){
    int i;
    double total=0;
    for(i=0;i<g.length;i++){
        total+=Math.pow(g[i]-h[i],2);
    }
    return total;
}
public static int findt(int i, int j, int h[]){ //if j is last number, it's index is j-1
    double min=Math.pow(10,9);
    int s=0;
    double total;
    double g[];
    int t=0;
    for(s=i-1;s<=(j-1);s++){
        g= findg(i-1,s,h);
        total= findd(h,s,j)+findlsquare(g,h);
    
        if(total<min){
            min=total;
            t=s;
        }
    }
      return t;     
    
}

}
