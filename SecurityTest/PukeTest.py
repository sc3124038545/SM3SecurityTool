import math
import sys
from gamma_functions import *
import random
from multiprocessing.sharedctypes import template

def puke_test(bits):
    templates = [None for x in range(2)]
    templates[0] = [[0,0,0,0],[0,0,0,1],[0,0,1,0],[0,0,1,1],
                    [0,1,0,0],[0,1,0,1],[0,1,1,0],[0,1,1,1],
                    [1,0,0,0],[1,0,0,1],[1,0,1,0],[1,0,1,1],
                    [1,1,0,0],[1,1,0,1],[1,1,1,0],[1,1,1,1]]
    
    n = len(bits)
    m = 4  
    N = int(math.floor(len(bits)/m))
    n = N*m
    
    blocks = list() 
    for i in range(N):
        blocks.append(bits[i*m:(i+1)*m])
    W=[0]*(2**m) 
    for block in blocks:
        for i in range(len(templates[0])):
            if block==templates[0][i]:
                W[i]+=1
    sumf=0.0
    for j in range(2**m):
        sumf+=W[j]**2
    chisq=(2**m/N)*sumf-N
    
    print(' chisquare = ',chisq)

    p = gammaincc((2**m-1)/2.0, chisq/2.0) 

    success = ( p >= 0.01)
    return (success,p,None)


bits=[1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,
          0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,
          0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,
          0,1,1,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,
          0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0] 

if __name__ == "__main__":
    bits = list(map(int, sys.argv[1]))
    s1,s2,s3= puke_test(bits)
#    print(s1)
    print("接受水平p为： %s" %s2)
    