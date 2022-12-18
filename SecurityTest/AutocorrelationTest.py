import math
import sys
from gamma_functions import *
import random

def xhmove(bitsli,k):
    return bitsli[k:]+bitsli[:k]

def autocorrelation_test(bits):
    n=len(bits)
    d=1#1,2,8,16
    bits=xhmove(bits, d)
    ad=0.0
    for i in range(n-d-1):
        ad+=bits[i]^bits[i+d]
        
    chisq=2*(ad-((n-d)/2))/math.sqrt(n-d)
    print("  chisquare = ",chisq)
    
    p=math.erfc(abs(chisq)/math.sqrt(2))
    
    success = (p >= 0.1)
    return (success,p,None)


bits=[1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,
          0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,
          0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,
          0,1,1,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,
          0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0] 

if __name__ == "__main__":
    bits = list(map(int, sys.argv[1]))
    s1,s2,s3= autocorrelation_test(bits)
#    print(s1)
    print("接受水平p为： %s" %s2)