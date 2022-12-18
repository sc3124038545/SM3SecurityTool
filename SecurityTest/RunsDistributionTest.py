import math
import sys
from fractions import Fraction
from gamma_functions import *

def runs_distribution_test(bits):
    n = len(bits)
    ei=list()
    for j in range(1,n+1):
        ei.append((n-j+3)/(2**(j+2)))
    
    k=0;
    for ee in ei:
        if ee>=5:
            k=k+1
    
    i=0
    b=[0]*n
    g=[0]*n
    while(i<n-1):
        ilen=1
        while(bits[i]==bits[i+1]):
            i=i+1
            if i==n-1:break
            ilen=ilen+1
        if bits[i]==1:
            b[ilen]=b[ilen]+1 
        if bits[i]==0:
            g[ilen]=g[ilen]+1 
        i=i+1
    
    a1=0.0
    a2=0.0            
    for j in range(1,k+1):
        a1+=(b[j]-ei[j])**2/ei[j]
        a2+=(g[j]-ei[j])**2/ei[j]
    
    chisq=a1+a2
    print("  chisquare = ", chisq)
    p = gammaincc((k-1), chisq/2.0)
    
    success = ( p >= 0.01)
    return (success,p,None)
     
        
        
bits=[1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,
          0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,
          0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,
          0,1,1,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,
          0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0] 
if __name__ == "__main__":
    bits = list(map(int, sys.argv[1]))
    s1,s2,s3=runs_distribution_test(bits)
    
    print("接受水平p为： %s" %s2)