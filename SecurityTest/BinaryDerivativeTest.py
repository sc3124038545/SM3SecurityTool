import math
import sys
from gamma_functions import *
import random

def bitsxor(bitsli):
    bitresult=list()
    for i in range(len(bitsli)):
        if i+1<len(bitsli):
            bitresult.append(bitsli[i]^bitsli[i+1])
    return bitresult

def binary_derivative_test(bits):
    n=len(bits)
    k=3 #k=7
    for j in range(k):
        bits=bitsxor(bits)
    
    s=0.0
    for x in range(n-k):
        s+=2*bits[x]-1
        
    chisq=abs(s)/math.sqrt(n-k)
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
    s1,s2,s3= binary_derivative_test(bits)
#    print(s1)
    print("接受水平p为： %s" %s2)