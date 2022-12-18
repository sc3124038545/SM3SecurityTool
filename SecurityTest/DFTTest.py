import math
import numpy
import sys
def dft_test(bits):
    n = len(bits)
    if (n % 2) == 1:       
        bits = bits[:-1]

    ts = list()        
    for bit in bits:
        ts.append((bit*2)-1)

    ts_np = numpy.array(ts)
    fs = numpy.fft.fft(ts_np) 
   
    mags = abs(fs)[:round(n/2)]

    T = math.sqrt(math.log(1.0/0.05)*n) 
    print("  T=%f" %T)
    N0 = 0.95*n/2.0
    print ("  N0 = %f" % N0)

    N1 = 0.0   
    for mag in mags:
        if mag < T:
            N1 += 1.0
    print ("  N1 = %f" % N1)
    d = (N1 - N0)/math.sqrt((n*0.95*0.05)/4) 
    print('  d=',d)
    p = math.erfc(abs(d)/math.sqrt(2))

    success = (p >= 0.01)
    return (success,p,None)

bits=[1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,
          0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,
          0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,
          0,1,1,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,
          0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0] 


if __name__ == "__main__":
    bits = list(map(int, sys.argv[1]))
    s1,s2,s3= dft_test(bits)
#    print(s1)
    print("接受水平p为： %s" %s2)

