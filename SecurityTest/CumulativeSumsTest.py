import math
import sys
#from scipy.special import gamma, gammainc, gammaincc
from gamma_functions import *
#import scipy.stats

def normcdf(n):
    return 0.5 * math.erfc(-n * math.sqrt(0.5))

def p_value(n,z):
    sum_a = 0.0
    startk = int(math.floor((((float(-n)/z)+1.0)/4.0)))
    endk   = int(math.floor((((float(n)/z)-1.0)/4.0)))
    for k in range(startk,endk+1):
        c = (((4.0*k)+1.0)*z)/math.sqrt(n)
        d = normcdf(c)
        c = (((4.0*k)-1.0)*z)/math.sqrt(n)
        e = normcdf(c)
        sum_a = sum_a + d - e

    sum_b = 0.0
    startk = int(math.floor((((float(-n)/z)-3.0)/4.0)))
    endk   = int(math.floor((((float(n)/z)-1.0)/4.0)))
    for k in range(startk,endk+1):
        c = (((4.0*k)+3.0)*z)/math.sqrt(n)
        d = normcdf(c)
        c = (((4.0*k)+1.0)*z)/math.sqrt(n)
        e = normcdf(c)
        sum_b = sum_b + d - e 

    p = 1.0 - sum_a + sum_b
    return p
    
def cumulative_sums_test(bits):
    n = len(bits)
    # Step 1
    x = list()        
    for bit in bits:
        x.append((bit*2)-1)
        
    # Steps 2 and 3 
    pos = 0
    forward_max = 0
    for e in x:
        pos = pos+e
        if abs(pos) > forward_max:
            forward_max = abs(pos)
    pos = 0
    backward_max = 0
    for e in reversed(x):
        pos = pos+e
        if abs(pos) > backward_max:
            backward_max = abs(pos)
            
    print('  前向模式最大s=',forward_max) 
    print('  后向模式最大s=',backward_max)
    # Step 4
    p_forward  = p_value(n, forward_max)
    p_backward = p_value(n,backward_max)
    
    success = ((p_forward >= 0.01) and (p_backward >= 0.01))
    plist = [p_forward, p_backward]

    return (success, None, plist)

bits=[1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,
          0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,
          0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,
          0,1,1,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,
          0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0] 

if __name__ == "__main__":
     
    bits = [1,1,0,0,1,0,0,1,0,0,0,0,1,1,1,1,1,1,0,1,
            1,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,
            0,1,1,0,1,0,0,0,1,1,0,0,0,0,1,0,0,0,1,1,
            0,1,0,0,1,1,0,0,0,1,0,0,1,1,0,0,0,1,1,0,
            0,1,1,0,0,0,1,0,1,0,0,0,1,0,1,1,1,0,0,0]
    bits = list(map(int, sys.argv[1]))
    success, _, plist = cumulative_sums_test(bits)

#    print ("success =",success)
    print ("plist = ",plist) 
