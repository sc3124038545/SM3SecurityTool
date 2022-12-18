import math
import sys

def pattern2int(pattern):
    l = len(pattern)
    n = 0
    for bit in (pattern):
        n = (n << 1) + bit
    return n          
         
def maurers_universal_test(bits,patternlen=None, initblocks=None):
    n = len(bits)

    # Step 1
    if patternlen != None:
        L = patternlen  
    else: 
        ns = [904960,2068480,4654080,10342400,
              22753280,49643520,107560960,
              231669760,496435200,1059061760]
        L = 6
        if n < 387840:
            print ("Error. Need at least 387840 bits. Got %d." % n)
            exit()
        for threshold in ns:
            if n >= threshold:
                L += 1 

    # Step 2 
    nblocks = int(math.floor(n/L))
    if initblocks != None:
        Q = initblocks
    else:
        Q = 10*(2**L)
    K = nblocks - Q
    
    # Step 3 
    nsymbols = (2**L)
    T=[0 for x in range(nsymbols)] 
    for i in range(Q):             
        pattern = bits[i*L:(i+1)*L] 
        idx = pattern2int(pattern)
        T[idx]=i+1     
                        
    # Step 4 
    sum = 0.0
    for i in range(Q,nblocks):
        pattern = bits[i*L:(i+1)*L]
        j = pattern2int(pattern)
        dist = i+1-T[j]
        T[j] = i+1
        sum = sum + math.log(dist,2)
    print ("  sum =", sum)
    
    # Step 5 
    fn = sum/K
    print ("  fn =",fn)
       
    # Step 6 
    ev_table =  [0,0.73264948,1.5374383,2.40160681,3.31122472,
                 4.25342659,5.2177052,6.1962507,7.1836656,
                 8.1764248,9.1723243,10.170032,11.168765,
                 12.168070,13.167693,14.167488,15.167379]
    var_table = [0,0.690,1.338,1.901,2.358,2.705,2.954,3.125,
                 3.238,3.311,3.356,3.384,3.401,3.410,3.416,
                 3.419,3.421]
                 
    mag = abs((fn - ev_table[L])/((math.sqrt(var_table[L]))*math.sqrt(2)))
    P = math.erfc(mag)
    vv=(fn - ev_table[L])/math.sqrt(var_table[L])
    print('  V =',vv)
    success = (P >= 0.01)
    return (success, P, None)
    

if __name__ == "__main__":
    #bits = [0,1,0,1,1,0,1,0,0,1,1,1,0,1,0,1,0,1,1,1]
    bits=[1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,
          0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,
          0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,
          0,1,1,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,1,
          0,0,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1,1,0,0,0] 
    bits = list(map(int, sys.argv[1]))
    success, p, _ = maurers_universal_test(bits, patternlen=2, initblocks=4)
    
#    print ("success =",success)
    print ("接受水平p为：",p)
